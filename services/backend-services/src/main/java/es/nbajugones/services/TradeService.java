package es.nbajugones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.DerechosDAO;
import es.nbajugones.dbdao.data.JugadoresDAO;
import es.nbajugones.dbdao.data.LogDAO;
import es.nbajugones.dbdao.data.RondasDraftDAO;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

public class TradeService {

	@Autowired
	JugadoresDAO jugadoresDAO;

	@Autowired
	DerechosDAO derechosDAO;

	@Autowired
	RondasDraftDAO rondasDraftDAO;

	@Autowired
	LogDAO logDAO;

	@Transactional
	public void trade(List<String> players1, List<String> players2,
			List<String> rondas1, List<String> rondas2, List<String> derechos1,
			List<String> derechos2, String equipo1, String equipo2, String fecha)
			throws ServiceException {
		try {
			if (players1 != null) {
				for (String p : players1) {
					jugadoresDAO.trade(equipo2, Integer.parseInt(p), fecha);
				}
			}
			if (players2 != null) {
				for (String p : players2) {
					jugadoresDAO.trade(equipo1, Integer.parseInt(p), fecha);
				}
			}
			if (rondas1 != null) {
				for (String r : rondas1) {
					String[] trozos = r.split("_");
					int ano = Integer.parseInt(trozos[0]);
					int ronda = Integer.parseInt(trozos[1]);
					String equipo = trozos[2];
					rondasDraftDAO.trade(equipo, equipo2, ano, ronda);
				}
			}
			if (rondas2 != null) {
				for (String r : rondas2) {
					String[] trozos = r.split("_");
					int ano = Integer.parseInt(trozos[0]);
					int ronda = Integer.parseInt(trozos[1]);
					String equipo = trozos[2];
					rondasDraftDAO.trade(equipo, equipo1, ano, ronda);
				}
			}
			if (derechos1 != null) {
				for (String d : derechos1) {
					derechosDAO.trade(equipo1, equipo2, d);
				}
			}
			if (derechos2 != null) {
				for (String d : derechos2) {
					derechosDAO.trade(equipo2, equipo1, d);
				}
			}
			logDAO.trade(players1, players2, rondas1, rondas2, derechos1,
					derechos2, equipo1, equipo2);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
