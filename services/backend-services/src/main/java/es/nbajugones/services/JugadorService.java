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
import es.nbajugones.dto.JugadorDTO;
import es.nbajugones.dto.entities.Jugadores;

/**
 *
 * @author Ignacio Blanco
 */
public class JugadorService {

    @Autowired
    JugadoresDAO jugadorDAO;

    /*public void ficharFA(String destino, String jugador, String salario, String anos) {
        jugadorDAO.ficharFA(destino, jugador, salario, anos);
        logDAO.fa(destino, jugador, salario, anos);
    }

    public void actualizaJug(String obs, String player) {
        jugadorDAO.actualizaJug(obs, player);
    }

    public void trade(String destino, String player) {
        jugadorDAO.trade(destino, player);
        
    }

    public void crearJugador(String nombre, String posicion) {
        jugadorDAO.crearJugador(nombre, posicion);
    }

    public void cut(String destino, String player, double factor) {
        jugadorDAO.cut(destino, player, factor);
    }

    public void renovar(String player, String origen, String destino, String salario, String anos) {
        jugadorDAO.renovar(player, origen, destino, salario, anos);
        if (origen.equals(destino)) {
            logDAO.renovar(player, destino, salario + "m$ a " + anos + " a�os");
        } else {
            logDAO.noRenovar2(player, origen);
            logDAO.ficharRenovaciones(player, destino, salario + "m$ a " + anos + " a�os");
        }
    }

    public void noRenovar(String player, String origen) {
        jugadorDAO.noRenovar(player, origen);
        logDAO.noRenovar(player, origen);
    }*/

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
    
    private List<JugadorDTO> convert(List<Jugadores> input){
    	List<JugadorDTO> result = new ArrayList<JugadorDTO>();
    	for (Jugadores j:input){
    		result.add(new JugadorDTO(j));
    	}
    	return result;
    }
}
