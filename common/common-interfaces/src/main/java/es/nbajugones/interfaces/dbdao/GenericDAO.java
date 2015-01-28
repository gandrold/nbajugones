package es.nbajugones.interfaces.dbdao;

import java.util.List;

import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;


public interface GenericDAO<T> {

	/**
	 * Gets records by criteria with aliases.
	 *
	 * @param clazz          Class to search
	 * @param searchCriteria Search criteria
	 * @param aliases        aliases for joined tables
	 * @return List of results
	 * @throws DaoException dao exception
	 */

	public abstract List<T> getByCriteria(SearchCriteria searchCriteria,
			List<String> aliases) throws DaoException;

	/**
	 * Gets records by criteria.
	 *
	 * @param clazz          Class to search
	 * @param searchCriteria Search criteria
	 * @return List of results
	 * @throws DaoException dao exception
	 */
	public abstract List<T> getByCriteria(SearchCriteria searchCriteria)
			throws DaoException;

	/**
	 * Save new entity or update entity when entityId is provided.
	 *
	 * @param entity   entity
	 * @param entityId entity primary key
	 * @return managed entity
	 * @throws DaoException DAO Exception
	 */
	public abstract T saveOrUpdateEntity(T entity, Object entityId)
			throws DaoException;

	public abstract T getById(Object entityId);

	/**
	 * Counts records by criteria with aliases.
	 *
	 * @param clazz          Class to search
	 * @param searchCriteria Search criteria
	 * @param aliases        aliases for joined tables
	 * @return Number of results
	 * @throws DaoException dao exception
	 */
	public abstract int countByCriteria(SearchCriteria searchCriteria,
			List<String> aliases) throws DaoException;

	/**
	 * Counts records by criteria with aliases.
	 *
	 * @param clazz          Class to search
	 * @param searchCriteria Search criteria
	 * @return Number of results
	 * @throws DaoException dao exception
	 */
	public abstract int countByCriteria(SearchCriteria searchCriteria)
			throws DaoException;
	
	public void removeEntity(Object entityId) throws DaoException;

}