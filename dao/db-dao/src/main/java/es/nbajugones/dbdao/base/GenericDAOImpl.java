package es.nbajugones.dbdao.base;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.GenericTypeResolver;

import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.interfaces.dbdao.GenericDAO;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
	
	/**
     * For logging execution information.
     */
    private static final Logger LOG = Logger.getLogger(GenericDAOImpl.class);

    /**
     * Entity manager from Spring.
     */
    @PersistenceContext
    protected EntityManager entityManager;

   

    
	public List<T> getByCriteria(SearchCriteria searchCriteria, List<String> aliases) throws DaoException
    {
        try
        {
            Session session = (Session) entityManager.getDelegate();

            Criteria criteria = getCriteria(searchCriteria, aliases, session);

            // this is a workaround for max results
            // todo: how to do using max_results and distinction in one query?
            @SuppressWarnings("unchecked")
			List<T> allResults = criteria.list();
            if (searchCriteria.getBatchSize() != null && searchCriteria.getStartIndex() != null)
            {
                return allResults.subList(searchCriteria.getStartIndex(), Math.min(allResults.size(), searchCriteria.getStartIndex() + searchCriteria.getBatchSize()));
            }
            else
            {
                return allResults;
            }

        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        	LOG.error(ex);
            throw new DaoException(ex);
        }

    }

	/**
	 * Creates a Criteria object with the parameters provided for queries.
	 * @param clazz Class for searching
	 * @param searchCriteria The search criteria
	 * @param aliases Aliases for tables
	 * @param session The DAO session
	 * @return Criteria An object containing the search criteria
	 */
	private Criteria getCriteria(SearchCriteria searchCriteria, List<String> aliases, Session session)
	{
		Class<T> clazz = getGenericsClass();
		// create criteria
		Criteria criteria = session.createCriteria(clazz);
		
		// add aliases for joined tables
		for (String alias : aliases)
		{
		    criteria = criteria.createAlias(alias, alias);
		}

		// Filtering
		for (SearchCriteria.FilterCriterion filterCriterion : searchCriteria.getFilterCriteria())
		{
		    switch (filterCriterion.getType())
		    {
		        case LIKE:

		            criteria.add(Restrictions.ilike(filterCriterion.getColumnName(), filterCriterion.getValue()));
		            break;

		        case EQUALS:

		            if (filterCriterion.getValue() != null)
		            {
		                if (filterCriterion.getValue() instanceof List)
		                {
		                    criteria.add(Restrictions.in(filterCriterion.getColumnName(), ((List) filterCriterion.getValue()).toArray()));
		                }
		                else
		                {
		                    criteria.add(Restrictions.eq(filterCriterion.getColumnName(), filterCriterion.getValue()));
		                }
		            }
		            else
		            {
		                criteria.add(Restrictions.isNull(filterCriterion.getColumnName()));
		            }
		            break;
                case NOT_EQUALS:
                    if (filterCriterion.getValue() != null)
                    {
                        criteria.add(Restrictions.ne(filterCriterion.getColumnName(), filterCriterion.getValue()));
                    }
                    else
                    {
                        criteria.add(Restrictions.isNotNull(filterCriterion.getColumnName()));
                    }
                    break;
                case LESS:
                    criteria.add(Restrictions.le(filterCriterion.getColumnName(), filterCriterion.getValue()));
		            break;
		        case MORE:
                    criteria.add(Restrictions.ge(filterCriterion.getColumnName(), filterCriterion.getValue()));
                    break;
		        default:
		            break;
		    }
		}

		// OR clauses
		for (List<SearchCriteria.FilterCriterion> orClause : searchCriteria.getOrClauses())
		{
		    Disjunction disjunction = Restrictions.disjunction();
		    for (SearchCriteria.FilterCriterion filterCriterion : orClause)
		    {
		        switch (filterCriterion.getType())
		        {
		            case LIKE:

		                disjunction.add(
		                    Restrictions.ilike(filterCriterion.getColumnName(), filterCriterion.getValue()));
		                break;

		            case EQUALS:

		                if (filterCriterion.getValue() != null)
		                {
		                    disjunction.add(
		                        Restrictions.eq(filterCriterion.getColumnName(), filterCriterion.getValue()));
		                }
		                else
		                {
		                    disjunction.add(Restrictions.isNull(filterCriterion.getColumnName()));
		                }
		                break;
                    case NOT_EQUALS:
                        if (filterCriterion.getValue() != null)
                        {
                            disjunction.add(
                                Restrictions.ne(filterCriterion.getColumnName(), filterCriterion.getValue()));
                        }
                        else
                        {
                            disjunction.add(Restrictions.isNotNull(filterCriterion.getColumnName()));
                        }
                        break;
                    case LESS:
                        disjunction.add(Restrictions.le(filterCriterion.getColumnName(), filterCriterion.getValue()));
                        break;
	                case MORE:
	                    disjunction.add(Restrictions.ge(filterCriterion.getColumnName(), filterCriterion.getValue()));
	                    break; 
	                case BETWEEN:
	                	disjunction.add(Restrictions.between(filterCriterion.getColumnName(), filterCriterion.getValue(), filterCriterion.getValue2()));
		            default:
		                break;
		        }
		    }
		    criteria.add(disjunction);
		}

		// Ordering
		for (SearchCriteria.OrderByCriterion orderByCriterion : searchCriteria.getOrderByCriteria())
		{
		    switch (orderByCriterion.getDirection())
		    {
		        case ASC:

		            criteria.addOrder(Order.asc(orderByCriterion.getColumn()).ignoreCase());
		            break;

		        case DESC:

		            criteria.addOrder(Order.desc(orderByCriterion.getColumn()).ignoreCase());
		            break;

		        default:
		            break;
		    }
		}

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

    /* (non-Javadoc)
	 * @see com.planfisheye.dbdao.dao.base.GenericDAO#getByCriteria(com.planfisheye.dto.search.SearchCriteria)
	 */
    public List<T> getByCriteria(SearchCriteria searchCriteria) throws DaoException
    {
        return getByCriteria(searchCriteria, new ArrayList<String>());
    }

    /* (non-Javadoc)
	 * @see com.planfisheye.dbdao.dao.base.GenericDAO#saveOrUpdateEntity(T, java.lang.Object)
	 */
    public T saveOrUpdateEntity(T entity, Object entityId) throws DaoException
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Creating/updating entity of class: " + entity.getClass() + " and entityId: " + entityId);
        }
        T managedEntity;

        try
        {
            if (null != entityId)
            {
                managedEntity = (T) entityManager.merge(entity);
            }
            else
            {
                managedEntity = entity;
            }

            entityManager.persist(managedEntity);
            entityManager.flush();
            return managedEntity;
        }
        catch (Exception ex)
        {        	
        	ex.printStackTrace();
            LOG.error("Exception saving or updating entity", ex);
            throw new DaoException(ex);
        }
    }

    /**
     * Removes provided entity from database.
     *
     * @param entityClass entity class
     * @param entityId    id
     * @throws DaoException dao exception
     */
    public void removeEntity(Object entityId) throws DaoException
    {
        try
        {
        	Class<T> entityClass = getGenericsClass();
            if (LOG.isDebugEnabled())
            {
                LOG.debug("Removing entity of class: " + entityClass + " and entityId: " + entityId);
            }
			T entity = (T) entityManager.find(entityClass, entityId);
            if (entity == null)
            {
                LOG.warn("Couldn't find entity to delete of class: " + entityClass + " and entityId: " + entityId);
            }
            entityManager.remove(entity);
            entityManager.flush();
        }
        catch (Exception ex)
        {
        	LOG.error(ex);
            throw new DaoException(ex);
        }
    }
    
	/* (non-Javadoc)
	 * @see com.planfisheye.dbdao.dao.base.GenericDAO#getById(java.lang.Object)
	 */
	public T getById(Object entityId){
    	
    	return (T) entityManager.find(getGenericsClass(), entityId);
    }

    /* (non-Javadoc)
	 * @see com.planfisheye.dbdao.dao.base.GenericDAO#countByCriteria(com.planfisheye.dto.search.SearchCriteria, java.util.List)
	 */
    public int countByCriteria(SearchCriteria searchCriteria, List<String> aliases) throws DaoException
    {
        try
        {
            Session session = (Session) entityManager.getDelegate();

            Criteria criteria = getCriteria(searchCriteria, aliases, session);

            return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        }
        catch (Exception ex)
        {
        	LOG.error(ex);
            throw new DaoException(ex);
        }

    }

    /* (non-Javadoc)
	 * @see com.planfisheye.dbdao.dao.base.GenericDAO#countByCriteria(com.planfisheye.dto.search.SearchCriteria)
	 */
    public int countByCriteria(SearchCriteria searchCriteria) throws DaoException
    {
        try
        {
            Session session = (Session) entityManager.getDelegate();

            Criteria criteria = getCriteria(searchCriteria, new ArrayList<String>(), session);
            Object result = criteria.setProjection(Projections.rowCount()).uniqueResult();            
            if (null == result) 
            {
                return 0;
            }
            else
            {
                return ((Number) result).intValue();
            }
        }
        catch (Exception ex)
        {
        	LOG.error(ex);
            throw new DaoException(ex);
        }

    }
    
    /**
     * Returns the hibernate version of the query.
     * 
     * @param query the query to execute
     * @return the hibernate version of the query.
     */
    protected SQLQuery getSQLQuery(String query)
    {
        Session session = (Session) entityManager.getDelegate();
        return session.createSQLQuery(query);
    }
    
    @SuppressWarnings("unchecked")
	private Class<T> getGenericsClass(){
    	return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(),GenericDAOImpl.class);
    }
}
