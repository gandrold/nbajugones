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
import es.nbajugones.dbdao.data.EquipoDAO;
import es.nbajugones.dbdao.data.JugadoresDAO;
import es.nbajugones.dbdao.data.LogDAO;
import es.nbajugones.dbdao.data.PlayersDAO;
import es.nbajugones.dbdao.data.RenovacionesDAO;
import es.nbajugones.dto.DerechoDTO;
import es.nbajugones.dto.ExportDTO;
import es.nbajugones.dto.JugadorDTO;
import es.nbajugones.dto.PlayerStatsDTO;
import es.nbajugones.dto.SeasonStatsDTO;
import es.nbajugones.dto.entities.Derecho;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Players;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

/**
 *
 * @author Ignacio Blanco
 */
@Transactional
public class JugadorService {

	@Autowired
	JugadoresDAO jugadorDAO;

	@Autowired
	EquipoDAO equipoDAO;

	@Autowired
	RenovacionesDAO renovacionesDAO;

	@Autowired
	LogDAO logDAO;

	@Autowired
	DerechosDAO derechosDAO;

	@Autowired
	PlayersDAO playersDAO;

	@Autowired
	StatsService statsService;

	@Autowired
	PlayersDAO jugadoresDAO;

	public void ficharFA(String destino, int jugador, String salario,
			String anos, String fecha) throws ServiceException {
		try {
			jugadorDAO.ficharFA(destino, jugador, salario, anos, fecha);
			logDAO.fa(destino, jugador, salario, anos);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}

	}

	public void trade(String destino, int player, String fecha) throws ServiceException {
		try {
			jugadorDAO.trade(destino, player, fecha);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	public Jugadores crearJugador(String nombre, String posicion)
			throws ServiceException {
		try {
			return jugadorDAO.crearJugador(nombre, posicion);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	public void cut(String destino, int player, double factor)
			throws ServiceException {
		try {
			logDAO.cut(destino, player);
			jugadorDAO.cut(destino, player, factor);
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	public void injured(String idEquipo, int player, boolean injured)
			throws ServiceException {
		Equipo e = equipoDAO.getById(idEquipo);
		Jugadores j = jugadorDAO.getById(player);
		j.setLesionado(injured ? 1 : 0);
		try {
			jugadorDAO.saveOrUpdateEntity(j, player);
			if (injured){
				e.setLesionados((e.getLesionados()==null?0:e.getLesionados()) + j.getSalario()/2);
			} else {
				e.setLesionados(e.getLesionados() - j.getSalario()/2);
			}
			equipoDAO.saveOrUpdateEntity(e, idEquipo);
		} catch (DaoException ex) {
			throw new ServiceException(ex.getFullMessage());
		}
	}

	public void activar(int jugador, String equipo) throws ServiceException {
		try {
			Jugadores j = derechosDAO.activarJugador(jugador);
			logDAO.activar(j.getIdjugador(), equipo);
			Players p = jugadoresDAO.getPlayer(null, null, j.getJugador(), null);
			if (p != null) {
				j.setPlayerid(p.getId());
				PlayerStatsDTO stats = statsService.getPlayerStats(p.getId());
				if (stats.getLastSeasonAvg().getSeason().equals("2017-18")) {
					j.setMinutos(stats.getLastSeasonAvg().getMinutos().doubleValue());
					j.setPuntos(stats.getHoopsAvg());
					j.setPromedio(stats.getFppmAvg());
					j.setJugados(stats.getLastSeasonAvg().getJugados().intValue());
				}
				jugadorDAO.saveOrUpdateEntity(j, j.getIdjugador());
			}
		} catch (DaoException e) {
			throw new ServiceException(e.getFullMessage());
		}
	}

	public List<JugadorDTO> getAll() throws ServiceException {
		return convert(jugadorDAO.getAll());
	}

	public List<JugadorDTO> getAllFA() throws ServiceException {
		return convert(jugadorDAO.getAllFA());
	}

	public List<JugadorDTO> getFA(String query) throws ServiceException {
		return convert(jugadorDAO.getFA(query));
	}

	public JugadorDTO getJugador(int id) {
		return new JugadorDTO(jugadorDAO.getById(id));
	}

	public JugadorDTO getByPlayerId(int id) throws DaoException {
		Jugadores j = jugadorDAO.getByPlayerId(id);
		if (j == null) {
			JugadorDTO jug = new JugadorDTO();
			Players p = playersDAO.getById(id);
			jug.setNombre(p.getDisplayname());
			return jug;
		} else {
			return new JugadorDTO(j);
		}
	}

	public List<JugadorDTO> getTop5FA(String pos) throws ServiceException {
		return convert(jugadorDAO.getTop5FA(pos));
	}

	public List<DerechoDTO> getDerechos() throws ServiceException {
		try {
			List<Derecho> all = derechosDAO.getAll();
			List<DerechoDTO> result = new ArrayList<DerechoDTO>();
			for (Derecho d : all) {
				DerechoDTO derecho = new DerechoDTO(d);
				Players player = jugadoresDAO.getPlayer(null, null, derecho.getJugador(), null);
				if (player != null) {
					SeasonStatsDTO stats = statsService.getPlayerStats(player.getId()).getLastSeasonAvg();
					if (stats.getSeason().equals("2017-18")) {
						derecho.setJugados(stats.getMinutos().intValue());
					}
				}
				result.add(derecho);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private List<JugadorDTO> convert(List<Jugadores> input) throws ServiceException {
		List<JugadorDTO> result = new ArrayList<JugadorDTO>();
		for (Jugadores j : input) {
			result.add(new JugadorDTO(j));
		}
		return result;
	}

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
					System.out.println(name);
					//name = name.replaceAll("'", "");
					int jugados = Integer.parseInt(st.nextToken());
					Jugadores j = jugadorDAO.getByName(name);
					if (j != null) {
						j.setMinutos(Double.parseDouble(st.nextToken()));
						j.setPuntos(Double.parseDouble(st.nextToken()));
						j.setPromedio(Double.parseDouble(st.nextToken()));
						j.setJugados(jugados);
						j.setActivo(1);
						jugadorDAO.saveOrUpdateEntity(j, j.getIdjugador());
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
