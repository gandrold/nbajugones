package es.nbajugones.dbdao.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Derecho;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Plantilla;
import es.nbajugones.dto.entities.pk.DerechoPK;
import es.nbajugones.dto.entities.pk.PlantillaPK;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.dto.search.SearchCriteria.FilterCriterion.FilterType;
import es.nbajugones.exception.dbdao.DaoException;

public class DerechosDAO extends GenericDAOImpl<Derecho> {

	@Autowired
	JugadoresDAO jugadoresDAO;

	@Autowired
	PlantillaDAO plantillaDAO;

	@Autowired
	LogDAO logDAO;

	public Jugadores activarJugador(String name) throws DaoException {
		Derecho d = getByName(name);
		Jugadores j = new Jugadores();
		j.setActivo(1);
		j.setJugador(name);
		j.setPosicion(d.getPosicion());
		j.setSalario(d.getSalario());
		j.setYears("" + d.getAnos());
		j = jugadoresDAO.saveOrUpdateEntity(j, null);
		Plantilla p = new Plantilla();
		PlantillaPK pk = new PlantillaPK();
		pk.setIdEquipo(d.getId().getIdEquipo());
		pk.setIdJugador(j.getIdJugador());
		p.setId(pk);
		plantillaDAO.saveOrUpdateEntity(p, null);
		removeEntity(d.getId());
		return j;
	}
	
	public List<Derecho> getAll() throws DaoException{
		SearchCriteria criteria = new SearchCriteria();
		return getByCriteria(criteria);
	}

	public Derecho getByName(String name) throws DaoException {
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("id.jugador", name, FilterType.EQUALS);
		List<Derecho> results = getByCriteria(criteria);
		if (!results.isEmpty()) {
			Derecho d = getByCriteria(criteria).get(0);
			return d;
		}
		return null;
	}

	public void trade(String idEquipoOrigen, String idEquipoDestino, String name)
			throws DaoException {
		Derecho derecho = getByName(name);
		if (derecho.getId().getIdEquipo().equals(idEquipoOrigen)) {
			removeEntity(derecho.getId());
			DerechoPK pk = derecho.getId();
			pk.setIdEquipo(idEquipoDestino);
			saveOrUpdateEntity(derecho, null);
		}

	}

}
