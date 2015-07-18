/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.DerechosDAO;
import es.nbajugones.dbdao.data.JugadoresDAO;
import es.nbajugones.dbdao.data.LogDAO;
import es.nbajugones.dbdao.data.RenovacionesDAO;
import es.nbajugones.dto.DerechoDTO;
import es.nbajugones.dto.ExportDTO;
import es.nbajugones.dto.JugadorDTO;
import es.nbajugones.dto.entities.Derecho;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

/**
 * 
 * @author Ignacio Blanco
 */
public class JugadorService {

	@Autowired
	JugadoresDAO jugadorDAO;

	@Autowired
	RenovacionesDAO renovacionesDAO;

	@Autowired
	LogDAO logDAO;

	@Autowired
	DerechosDAO derechosDAO;

	@Transactional
	public void ficharFA(String destino, int jugador, String salario,
			String anos) throws ServiceException {
		try {
			jugadorDAO.ficharFA(destino, jugador, salario, anos);
			logDAO.fa(destino, jugador, salario, anos);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}

	}

	@Transactional
	public void actualizaJug(String obs, String player) throws ServiceException {
		try {
			jugadorDAO.actualizaJug(obs, player);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	public void trade(String destino, int player) throws ServiceException {
		try {
			jugadorDAO.trade(destino, player);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	@Transactional
	public void crearJugador(String nombre, String posicion)
			throws ServiceException {
		try {
			jugadorDAO.crearJugador(nombre, posicion);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	@Transactional
	public void cut(String destino, int player, double factor)
			throws ServiceException {
		try {
			logDAO.cut(destino, player);
			jugadorDAO.cut(destino, player, factor);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	@Transactional
	public void activar(String jugador, String equipo) throws ServiceException {
		try {
			Jugadores j = derechosDAO.activarJugador(jugador);
			logDAO.activar(j.getIdJugador(), equipo);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	@Transactional
	public void renovar(int player, String origen, String destino,
			double salario, String anos) throws ServiceException {
		try {
			renovacionesDAO.renovar(player, destino, salario, anos);
			if (origen.equals(destino)) {
				logDAO.renovar(player, destino, salario, anos);
			} else {
				logDAO.noRenovar2(player, origen);
				logDAO.ficharRenovaciones(player, destino, salario, anos);
			}
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	@Transactional
	public void noRenovar(int player, String origen) throws ServiceException {
		try {
			renovacionesDAO.noRenovar(player, origen);
			logDAO.noRenovar(player, origen);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	@Transactional
	public List<JugadorDTO> getAll() {
		return convert(jugadorDAO.getAll());
	}

	@Transactional
	public List<JugadorDTO> getAllFA() {
		return convert(jugadorDAO.getAllFA());
	}

	@Transactional
	public List<JugadorDTO> getFA(String query) {
		return convert(jugadorDAO.getFA(query));
	}

	@Transactional
	public JugadorDTO getJugador(int id) {
		return new JugadorDTO(jugadorDAO.getById(id));
	}

	@Transactional
	public List<JugadorDTO> getTop5FA(String pos) {
		return convert(jugadorDAO.getTop5FA(pos));
	}

	@Transactional
	public List<DerechoDTO> getDerechos() throws ServiceException {
		try {
			List<Derecho> all = derechosDAO.getAll();
			List<DerechoDTO> result = new ArrayList<DerechoDTO>();
			for (Derecho d : all) {
				result.add(new DerechoDTO(d));
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private List<JugadorDTO> convert(List<Jugadores> input) {
		List<JugadorDTO> result = new ArrayList<JugadorDTO>();
		for (Jugadores j : input) {
			result.add(new JugadorDTO(j));
		}
		return result;
	}

	@Transactional
	public ExportDTO updateScores(File f) throws ServiceException {
		try {
			InputStream is = new FileInputStream(f);
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					java.nio.charset.Charset.forName("UTF-8")));
			String line;
			ExportDTO result = new ExportDTO();
			while ((line = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ";");
				// Primer token jugador, segundo minutos, tercero puntos, y
				// cuarto promedio
				if (st.hasMoreTokens()) {
					String name = st.nextToken();
					name = name.replaceAll("'", "");
					int jugados = Integer.parseInt(st.nextToken());
					Jugadores j = jugadorDAO.getByName(name);
					if (j != null) {
						j.setMinutos(Double.parseDouble(st.nextToken()));
						j.setPuntos(Double.parseDouble(st.nextToken()));
						j.setPromedio(Double.parseDouble(st.nextToken()));
						j.setActivo(1);
						jugadorDAO.saveOrUpdateEntity(j, j.getIdJugador());
					} else {
						Derecho d = derechosDAO.getByName(name);
						if (d != null) {
							DerechoDTO dto = new DerechoDTO(d);
							dto.setJugados(jugados);
							result.getDerechos().add(dto);
						} else {
							result.getJugadoresNuevos().add(name);
						}
					}
				}
			}
			in.close();
			result.sortDerechos();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
