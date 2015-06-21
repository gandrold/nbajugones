package es.nbajugones.dbdao.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.LogDTO;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Log;
import es.nbajugones.exception.dbdao.DaoException;

public class LogDAO extends GenericDAOImpl<Log> {

	private static final String FA = "%s - %s ficha a %s por %s en %s años ";
	private static final String CORTA = "%s - %s corta a %s (%s - %s) ";
	private static final String RENUEVA = "%s - %s renueva a %s por %s en %s años ";
	private static final String NO_RENUEVA = "%s - %s no renueva a %s";
	private static final String NO_RENUEVA_FA = "%s - %s no renueva a %s y pasa a ser FA";
	private static final String FICHA_RENOVACIONES = "%s - %s ficha en renovaciones a %s por %s en %s años ";
	private static final String ACTIVA = "%s - %s activa a %s (%s - %s) ";
	
	private SimpleDateFormat sdf = new SimpleDateFormat(LogDTO.DATE_FORMATS[0]);
	
	@Autowired
	JugadoresDAO jugadoresDAO;
	
	@Autowired
	EquipoDAO equipoDAO;
	
	public Log fa(String destino, int jugador, String salario, String anos) throws DaoException {
        String mensaje = String.format(FA, sdf.format(Calendar.getInstance().getTime()),
        		equipoDAO.getById(destino).getNombre(),
        		jugadoresDAO.getById(jugador).getJugador(),salario,anos);
        Log log = new Log();
        log.setIdEquipo(destino);
        log.setTexto(mensaje);
        saveOrUpdateEntity(log, null);
        return log;
    }

   

    public Log cut(String equipo, int player) throws DaoException {
    	Jugadores j = jugadoresDAO.getById(player);
    	String mensaje = String.format(CORTA, sdf.format(Calendar.getInstance().getTime()),
        		equipoDAO.getById(equipo).getNombre(),
        		j.getJugador(),j.getSalario(),j.getYears());
    	Log log = new Log();
        log.setIdEquipo(equipo);
        log.setTexto(mensaje);
        saveOrUpdateEntity(log, null);
        return log;
    }

    public Log activar(int jugador, String equipo, String salario, String anos) throws DaoException {
    	Jugadores j = jugadoresDAO.getById(jugador);
    	String mensaje = String.format(ACTIVA, sdf.format(Calendar.getInstance().getTime()),
        		equipoDAO.getById(equipo).getNombre(),
        		j.getJugador(),salario,anos);
    	Log log = new Log();
        log.setIdEquipo(equipo);
        log.setTexto(mensaje);
        saveOrUpdateEntity(log, null);
        return log;
    }

    public Log renovar(int jugador, String equipo, double salario, String anos) throws DaoException {
    	Jugadores j = jugadoresDAO.getById(jugador);
    	String mensaje = String.format(RENUEVA, sdf.format(Calendar.getInstance().getTime()),
        		equipoDAO.getById(equipo).getNombre(),
        		j.getJugador(),salario,anos);
    	Log log = new Log();
        log.setIdEquipo(equipo);
        log.setTexto(mensaje);
        saveOrUpdateEntity(log, null);
        return log;
    }

    public Log ficharRenovaciones(int jugador, String equipo, double salario, String anos) throws DaoException {
    	Jugadores j = jugadoresDAO.getById(jugador);
    	String mensaje = String.format(FICHA_RENOVACIONES, sdf.format(Calendar.getInstance().getTime()),
        		equipoDAO.getById(equipo).getNombre(),
        		j.getJugador(),salario,anos);
    	Log log = new Log();
        log.setIdEquipo(equipo);
        log.setTexto(mensaje);
        saveOrUpdateEntity(log, null);
        return log;
    }

    public Log noRenovar(int jugador, String equipo) throws DaoException {
    	Jugadores j = jugadoresDAO.getById(jugador);
    	String mensaje = String.format(NO_RENUEVA_FA, sdf.format(Calendar.getInstance().getTime()),
        		equipoDAO.getById(equipo).getNombre(),
        		j.getJugador());
    	Log log = new Log();
        log.setIdEquipo(equipo);
        log.setTexto(mensaje);
        saveOrUpdateEntity(log, null);
        return log;
    }

    public Log noRenovar2(int jugador, String equipo) throws DaoException {
    	Jugadores j = jugadoresDAO.getById(jugador);
    	String mensaje = String.format(NO_RENUEVA, sdf.format(Calendar.getInstance().getTime()),
        		equipoDAO.getById(equipo).getNombre(),
        		j.getJugador());
    	Log log = new Log();
        log.setIdEquipo(equipo);
        log.setTexto(mensaje);
        saveOrUpdateEntity(log, null);
        return log;
    }
	
}
