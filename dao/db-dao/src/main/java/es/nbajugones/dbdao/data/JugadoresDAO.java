package es.nbajugones.dbdao.data;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.exception.dbdao.DaoException;

public class JugadoresDAO extends GenericDAOImpl<Jugadores> {

	@SuppressWarnings("unchecked")
	public List<Jugadores> getPlantilla(List<Integer> jugadores) throws DaoException{
		Query query = entityManager.createNamedQuery("Jugadores.getPlantilla");
        query = query.setParameter("plantilla", jugadores);
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Jugadores> getTop5FA(String pos){
		String sqlQuery="SELECT j.id_jugador as idJugador, j.CORTADO_POR as cortadoPor, j.jugador, j.promedio,"
        		+ "j.jugados,j.minutos,j.obs,j.posicion,j.puntos FROM JUGADORES j WHERE activo=1 "
                + " and posicion like :position"
                + " and id_jugador not in (select id_jugador from plantillas)"
                + "order by Puntos DESC, jugados desc LIMIT 5";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setParameter("position", "%"+pos+"%");
		query.setResultTransformer(Transformers.aliasToBean(Jugadores.class));
		return (List<Jugadores>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Jugadores> getAllFA(){
        String sqlQuery="SELECT j.id_jugador as idJugador, j.CORTADO_POR as cortadoPor, j.jugador, j.promedio,"
        		+ "j.jugados,j.minutos,j.obs,j.posicion,j.puntos FROM JUGADORES j WHERE activo=1 "
                + " and id_jugador not in (select id_jugador from plantillas) order by Puntos DESC, jugados desc";
        SQLQuery query = getSQLQuery(sqlQuery);
        query.setResultTransformer(Transformers.aliasToBean(Jugadores.class));
        return (List<Jugadores>) query.list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Jugadores> getAll(){
        String sqlQuery="SELECT j.id_jugador as idJugador, j.AÑOS as years, j.CORTADO_POR as cortadoPor, j.jugador, "
        		+ "j.jugados,j.minutos,j.obs,j.posicion,j.puntos,j.salario, j.promedio, e.nombre as equipo FROM JUGADORES j"
                + " left join (select p.id_jugador, e1.nombre from plantillas p inner join equipos e1 on p.id_equipo=e1.id_equipo) e "
                + "on j.id_jugador=e.id_jugador WHERE activo=1 order by Puntos DESC, jugados desc";
        SQLQuery query = getSQLQuery(sqlQuery);
        query.setResultTransformer(Transformers.aliasToBean(Jugadores.class));
        return (List<Jugadores>) query.list();
    }
	
}
