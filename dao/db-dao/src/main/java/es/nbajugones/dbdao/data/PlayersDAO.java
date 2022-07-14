/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dbdao.data;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Players;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;
import java.util.List;

/**
 *
 * @author iblanco
 */
public class PlayersDAO extends GenericDAOImpl<Players> {

	public Players getPlayer(String firstName, String lastName, String displayName, String position) throws DaoException {
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("displayname", displayName, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		List<Players> results = getByCriteria(criteria);
		Players p = null;
		if (!results.isEmpty()) {
			p = results.get(0);
			setPosition(p, position);
			saveOrUpdateEntity(p, p.getId());
		}
		if (p == null && firstName != null && lastName !=null) {
			criteria = new SearchCriteria();
			criteria.addFilter("firstname", firstName.substring(0, Math.min(3, firstName.length())), SearchCriteria.FilterCriterion.FilterType.LIKE);
			criteria.addFilter("lastname", lastName.substring(0, Math.min(3, lastName.length())), SearchCriteria.FilterCriterion.FilterType.LIKE);
			results = getByCriteria(criteria);
			if (!results.isEmpty()) {
				p = results.get(0);
				setPosition(p, position);
				saveOrUpdateEntity(p, p.getId());
			}
			if (p == null) {
				p = new Players();
				p.setDisplayname(displayName);
				p.setFirstname(firstName);
				p.setLastname(lastName);
				setPosition(p, position);
				System.out.println("New player!!");
				p = saveOrUpdateEntity(p, null);
			}
		}
		return p;
	}

	private void setPosition(Players p, String position) {
		if ("PG".equals(position) || "SG".equals(position)) {
			p.setCanPlayG(1);
		}
		if ("SF".equals(position) || "PF".equals(position)) {
			p.setCanPlayF(1);
		}
		if ("C".equals(position)) {
			p.setCanPlayC(1);
		}

	}

}
