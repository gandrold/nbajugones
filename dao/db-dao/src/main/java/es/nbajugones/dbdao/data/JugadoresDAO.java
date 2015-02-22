package es.nbajugones.dbdao.data;

import java.util.List;

import javax.persistence.Query;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.exception.dbdao.DaoException;

public class JugadoresDAO extends GenericDAOImpl<Jugadores> {

	public List<Jugadores> getPlantilla(List<Integer> jugadores) throws DaoException{
		Query query = entityManager.createNamedQuery("Jugadores.getPlantilla");
        query = query.setParameter("plantilla", jugadores);
		return query.getResultList();
		
	}
	
}
