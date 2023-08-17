package es.nbajugones.dbdao.data;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.EvaluacionDTO;
import es.nbajugones.dto.KeyValue;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Plantilla;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;

public class EquipoDAO extends GenericDAOImpl<Equipo> {

	@Autowired
	JugadoresDAO jugadoresDAO;

	public Equipo getByNombre(String nombre) throws DaoException {
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("nombre", nombre, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		List<Equipo> results = getByCriteria(criteria);
		if (results.isEmpty()) {
			String ciudad = nombre.substring(0, nombre.lastIndexOf(" "));
			String sqlQuery = "SELECT id_equipo as idEquipo from equipos where nombre like '" + ciudad + "%'";
			SQLQuery query = getSQLQuery(sqlQuery);
			query.setResultTransformer(Transformers.aliasToBean(Equipo.class));
			List<Equipo> r = query.list();
			return r.get(0);
		} else {
			return results.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	public List<KeyValue> getEquipos() throws DaoException {
		String sqlQuery = "SELECT e.id_equipo as 'key', e.nombre as 'value' from equipos e";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(KeyValue.class));
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public KeyValue getEquipo(int idJugador) throws DaoException {
		String sqlQuery = "SELECT e.id_equipo as 'key', e.nombre as 'value' from equipos e join plantillas p on p.id_equipo=e.id_equipo where p.id_jugador=" + idJugador;
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(KeyValue.class));
		List l = query.list();
		if (!l.isEmpty()) {
			return (KeyValue) l.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EvaluacionDTO> evaluar(String idEquipo) throws DaoException {
		String sqlQuery = "select e.id_equipo as equipo, e.logo_draft as logo, (select sum(j.salario) from jugadores j inner join plantillas p on "
				+ "j.id_jugador=p.id_jugador where p.id_equipo=e.id_equipo and (j.renovar<>1 or j.renovar is null)) as salarios,e.sanciones,e.bonus_ant as bonusAnt, "
				+ "e.bonus_act as bonusAct,e.lesionados,e.cortes from equipos e where e.id_equipo = '" + idEquipo + "'";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(EvaluacionDTO.class));
		List<EvaluacionDTO> evaluacion = (List<EvaluacionDTO>) query.list();
		for (EvaluacionDTO e : evaluacion) {
			Equipo equipo = getById(e.getEquipo());
			List<Integer> jugadores = new ArrayList<Integer>();
			for (Plantilla p : equipo.getPlantilla()) {
				jugadores.add(p.getId().getIdJugador());
			}
			int total = 0;
			int fa = 0;
			List<Jugadores> plantilla = jugadoresDAO.getPlantilla(jugadores);
			for (Jugadores j : plantilla) {
				if (j.getRenovar() != null && j.getRenovar() == 1) {
					fa++;
				}
				total++;
			}
			e.setFa(fa);
			e.setJugadores(total);

		}
		return evaluacion;
	}

	@SuppressWarnings("unchecked")
	public List<EvaluacionDTO> evaluar() throws DaoException {
		String sqlQuery = "select e.id_equipo as equipo, e.nombre as nombre,e.propietario as propietario, e.logo_draft as logo, (select sum(j.salario) from jugadores j inner join plantillas p on "
				+ "j.id_jugador=p.id_jugador where p.id_equipo=e.id_equipo and (j.renovar<>1 or j.renovar is null)) as salarios,e.sanciones,e.bonus_ant as bonusAnt, "
				+ "e.bonus_act as bonusAct,e.lesionados,e.cortes from equipos e";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(EvaluacionDTO.class));
		List<EvaluacionDTO> evaluacion = (List<EvaluacionDTO>) query.list();
		for (EvaluacionDTO e : evaluacion) {
			Equipo equipo = getById(e.getEquipo());
			List<Integer> jugadores = new ArrayList<Integer>();
			for (Plantilla p : equipo.getPlantilla()) {
				jugadores.add(p.getId().getIdJugador());
			}
			int total = 0;
			int fa = 0;
			List<Jugadores> plantilla = jugadoresDAO.getPlantilla(jugadores);
			for (Jugadores j : plantilla) {
				if (j.getRenovar() != null && j.getRenovar() == 1) {
					fa++;
				}
				total++;
			}
			e.setFa(fa);
			e.setJugadores(total);

		}
		return evaluacion;
	}

}