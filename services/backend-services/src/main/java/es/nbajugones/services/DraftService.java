package es.nbajugones.services;

import es.nbajugones.dbdao.data.DerechosDAO;
import es.nbajugones.dbdao.data.LogDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.OrdenDraftDAO;
import es.nbajugones.dbdao.data.RondasDraftDAO;
import es.nbajugones.dto.RondaDTO;
import es.nbajugones.dto.entities.Derecho;
import es.nbajugones.dto.entities.RondasDraft;
import es.nbajugones.dto.entities.pk.OrdenDraftPK;
import es.nbajugones.dto.entities.pk.RondasDraftPK;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

public class DraftService {

	@Autowired
	RondasDraftDAO rondasDraftDAO;

	@Autowired
	OrdenDraftDAO ordenDraftDAO;

	@Autowired
	DerechosDAO derechosDAO;

	@Autowired
	LogDAO logDAO;

	@Transactional
	public List<RondaDTO> getDraft(int y, int round) throws ServiceException {
		try {
			List<RondasDraft> rondas = rondasDraftDAO.getYear(y, round);
			List<RondaDTO> result = new ArrayList<RondaDTO>();
			for (RondasDraft ronda : rondas) {
				RondaDTO r = new RondaDTO(ronda);
				OrdenDraftPK pk = new OrdenDraftPK();
				pk.setAno(y);
				pk.setIdEquipo(ronda.getId().getIdEquipo());
				pk.setRonda(round);
				r.setOrden(ordenDraftDAO.getById(pk).getPosicion());
				result.add(r);
			}
			Collections.sort(result, new Comparator<RondaDTO>() {
				public int compare(RondaDTO o1, RondaDTO o2) {
					return o1.getOrden() - o2.getOrden();
				}
			});
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	public RondaDTO getPick(int y, int round, String team) {
		RondasDraftPK pk = new RondasDraftPK();
		pk.setAno(y);
		pk.setRonda(round);
		pk.setIdEquipo(team);
		return new RondaDTO(rondasDraftDAO.getById(pk));
	}

	@Transactional
	public void savePick(int y, int round, String team, String jugador, String posicion) throws ServiceException {
		try {
			RondasDraftPK pk = new RondasDraftPK();
			pk.setAno(y);
			pk.setRonda(round);
			pk.setIdEquipo(team);
			RondasDraft ronda = rondasDraftDAO.getById(pk);
			ronda.setJugador(jugador);
			ronda.setLetra(posicion);
			rondasDraftDAO.saveOrUpdateEntity(ronda, pk);
			Derecho d = new Derecho();
			d.setAnoEleccion(y);
			d.setJugador(jugador);
			d.setAnos(Integer.parseInt(ronda.getDuracion()));
			d.setSalario(Double.parseDouble(ronda.getSalario()));
			d.setIdEquipo(ronda.getIdEquipoProp());
			d.setPosicion(posicion);
			derechosDAO.saveOrUpdateEntity(d, null);
			OrdenDraftPK opk = new OrdenDraftPK();
			opk.setAno(y);
			opk.setIdEquipo(ronda.getId().getIdEquipo());
			opk.setRonda(round);
			logDAO.draft(ronda.getIdEquipoProp(), ordenDraftDAO.getById(opk).getPosicion(), round, jugador, posicion);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
