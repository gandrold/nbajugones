/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.JugadoresDAO;
import es.nbajugones.dbdao.data.LogDAO;
import es.nbajugones.dbdao.data.RenovacionesDAO;
import es.nbajugones.dto.JugadorDTO;
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
	public List<JugadorDTO> getTop5FA(String pos) {
		return convert(jugadorDAO.getTop5FA(pos));
	}

	private List<JugadorDTO> convert(List<Jugadores> input) {
		List<JugadorDTO> result = new ArrayList<JugadorDTO>();
		for (Jugadores j : input) {
			result.add(new JugadorDTO(j));
		}
		return result;
	}
}
