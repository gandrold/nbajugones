/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dbdao.data;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Season;
import es.nbajugones.exception.dbdao.DaoException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

/**
 *
 * @author iblanco
 */
public class SeasonDAO extends GenericDAOImpl<Season>{

	@SuppressWarnings("unchecked")
	public List<Season> getSeasons() throws DaoException{
		Query query = entityManager.createNamedQuery("Season.findAll");
		return query.getResultList();
	}


	public Season getCurrentSeason(String d) throws DaoException{
		String sqlQuery="SELECT * from season where start <="+d+" and "+d+"<= end";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(Season.class));
		List<Season> results = query.list();
		if (!results.isEmpty()){
			return results.get(0);
		} else {
			results = getSeasons();
			Collections.sort(results, new Comparator<Season>(){
				public int compare(Season o1, Season o2) {
					return o2.getSeason().compareTo(o1.getSeason());
				}
			});
			return results.get(0);
		}
	}

	public Season getFirstUnfinishedSeason(){
		String sqlQuery="SELECT * from season where lastprocessed is null or lastprocessed < end";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(Season.class));
		List<Season> results = query.list();
		if (!results.isEmpty()){
			return results.get(0);
		}
		return null;
	}

	public Season getSeason(String d){
		String sqlQuery="SELECT * from season where start <="+d+" and "+d+"<= lastprocessed";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(Season.class));
		List<Season> results = query.list();
		if (!results.isEmpty()){
			return results.get(0);
		}
		return null;
	}

}
