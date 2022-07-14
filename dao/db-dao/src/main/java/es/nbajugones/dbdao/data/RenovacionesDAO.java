package es.nbajugones.dbdao.data;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Plantilla;
import es.nbajugones.dto.entities.Renovacione;
import es.nbajugones.dto.entities.pk.PlantillaPK;
import es.nbajugones.dto.entities.pk.RenovacionePK;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;

public class RenovacionesDAO extends GenericDAOImpl<Renovacione> {

	@Autowired
	EquipoDAO equipoDAO;

	@Autowired
	JugadoresDAO jugadoresDAO;

	@Autowired
	PlantillaDAO plantillaDAO;

	public List<Renovacione> get(int y, int tanda) throws DaoException {
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("id.year", y, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		criteria.addFilter("tanda", tanda, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		return getByCriteria(criteria);
	}

	public void renovar(int player, String destino, double salario, int anos) throws DaoException {
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);
		RenovacionePK renoPk = new RenovacionePK();
		renoPk.setYear(y);
		renoPk.setIdJugador(player);
		Renovacione r = getById(renoPk);
		Jugadores j = jugadoresDAO.getById(player);
		Equipo e1 = equipoDAO.getById(r.getIdEquipoProp());
		Plantilla cut = null;
		for (Plantilla p : e1.getPlantilla()) {
			if (p.getId().getIdJugador() == player) {
				cut = p;
			}
		}
		plantillaDAO.removeEntity(cut.getId());
		List<Plantilla> plant = e1.getPlantilla();
		plant.remove(cut);
		equipoDAO.saveOrUpdateEntity(e1, destino);
		Equipo e2 = equipoDAO.getById(destino);
		Plantilla p = new Plantilla();
		PlantillaPK pk = new PlantillaPK();
		pk.setIdEquipo(destino);
		pk.setIdJugador(player);
		p.setId(pk);
		e2.getPlantilla().add(p);
		plantillaDAO.saveOrUpdateEntity(p, null);
		j.setSalario(salario);
		j.setRenovar(2);
		j.setYears("" + anos);
		jugadoresDAO.saveOrUpdateEntity(j, player);
		equipoDAO.saveOrUpdateEntity(e1, e1.getIdEquipo());
		equipoDAO.saveOrUpdateEntity(e2, destino);
		r.setRenueva(e1.getIdEquipo().equals(destino) ? "RENOVADO" : "FICHADO");
		if (!e1.getIdEquipo().equals(destino)) {
			r.setIdEquipoGanador(destino);
		}
		r.setSalario(salario);
		r.setYears(anos);
		saveOrUpdateEntity(r, renoPk);
	}

	public void noRenovar(int player, String origen) throws DaoException {
		Jugadores j = jugadoresDAO.getById(player);
		Equipo e1 = equipoDAO.getById(origen);
		Plantilla cut = null;
		for (Plantilla p : e1.getPlantilla()) {
			if (p.getId().getIdJugador() == player) {
				cut = p;
			}
		}
		plantillaDAO.removeEntity(cut.getId());
		List<Plantilla> plant = e1.getPlantilla();
		plant.remove(cut);
		j.setYears("-");
		j.setRenovar(0);
		jugadoresDAO.saveOrUpdateEntity(j, player);
		equipoDAO.saveOrUpdateEntity(e1, origen);
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);
		RenovacionePK renoPk = new RenovacionePK();
		renoPk.setYear(y);
		renoPk.setIdJugador(player);
		Renovacione r = getById(renoPk);
		r.setRenueva("FA");
		saveOrUpdateEntity(r, renoPk);
	}

	public void renovacionTemp(String equipo, double salario, int anos, int player, int ano) throws DaoException {

		RenovacionePK renoPk = new RenovacionePK();
		renoPk.setYear(ano);
		renoPk.setIdJugador(player);
		Renovacione r = getById(renoPk);
		r.setIdEquipoGanador(equipo);
		r.setYears(anos);
		r.setSalario(salario);
		saveOrUpdateEntity(r, renoPk);
	}
}
