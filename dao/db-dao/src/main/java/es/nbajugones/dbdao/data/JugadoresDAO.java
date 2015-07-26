package es.nbajugones.dbdao.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Plantilla;
import es.nbajugones.dto.entities.pk.PlantillaPK;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.dto.search.SearchCriteria.FilterCriterion.FilterType;
import es.nbajugones.exception.dbdao.DaoException;

public class JugadoresDAO extends GenericDAOImpl<Jugadores> {

	@Autowired
	EquipoDAO equipoDAO;
	
	@Autowired
	PlantillaDAO plantillaDAO;
	
	@SuppressWarnings("unchecked")
	public List<Jugadores> getPlantilla(List<Integer> jugadores) throws DaoException{
		Query query = entityManager.createNamedQuery("Jugadores.getPlantilla");
        query = query.setParameter("plantilla", jugadores);
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Jugadores> getTop5FA(String pos){
		String sqlQuery="SELECT j.id_jugador as idJugador, j.CORTADO_POR as cortadoPor, j.jugador, j.promedio,"
        		+ "j.jugados,j.minutos,j.obs,j.posicion,j.puntos FROM jugadores j WHERE activo=1 "
                + " and posicion like :position"
                + " and id_jugador not in (select id_jugador from plantillas)"
                + "order by Puntos DESC, jugados desc LIMIT 5";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setParameter("position", "%"+pos+"%");
		query.setResultTransformer(Transformers.aliasToBean(Jugadores.class));
		return (List<Jugadores>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Jugadores> getAllFA(){
        String sqlQuery="SELECT j.id_jugador as idJugador, j.CORTADO_POR as cortadoPor, j.jugador, j.promedio,"
        		+ "j.jugados,j.minutos,j.obs,j.posicion,j.puntos FROM jugadores j WHERE activo=1 "
                + " and id_jugador not in (select id_jugador from plantillas) order by Puntos DESC, jugados desc";
        SQLQuery query = getSQLQuery(sqlQuery);
        query.setResultTransformer(Transformers.aliasToBean(Jugadores.class));
        return (List<Jugadores>) query.list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Jugadores> getFA(String name){
        String sqlQuery="SELECT j.id_jugador as idJugador, j.CORTADO_POR as cortadoPor, j.jugador, j.promedio,"
        		+ "j.jugados,j.minutos,j.obs,j.posicion,j.puntos FROM jugadores j WHERE "
                + " id_jugador not in (select id_jugador from plantillas) "
                + "and lower(j.jugador) like :nombre order by Puntos DESC, jugados desc";
        SQLQuery query = getSQLQuery(sqlQuery);
        query.setParameter("nombre", "%"+name.toLowerCase()+"%");
        query.setResultTransformer(Transformers.aliasToBean(Jugadores.class));
        return (List<Jugadores>) query.list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Jugadores> getAll(){
        String sqlQuery="SELECT j.id_jugador as idJugador, j.AÑOS as years, j.CORTADO_POR as cortadoPor, j.jugador, "
        		+ "j.jugados,j.minutos,j.obs,j.posicion,j.puntos,j.salario, j.promedio, e.nombre as equipo FROM jugadores j"
                + " left join (select p.id_jugador, e1.nombre from plantillas p inner join equipos e1 on p.id_equipo=e1.id_equipo) e "
                + "on j.id_jugador=e.id_jugador WHERE activo=1 order by Puntos DESC, jugados desc";
        SQLQuery query = getSQLQuery(sqlQuery);
        query.setResultTransformer(Transformers.aliasToBean(Jugadores.class));
        return (List<Jugadores>) query.list();
    }
	
	public void ficharFA(String destino, int jugador, String salario, String anos) throws DaoException {
        double s = Double.parseDouble(salario);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 5);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        String obs = String.format("No movible antes del %s (Si se traspasa, se ha de mantener un mes en destino)", sdf.format(c.getTime()));
        Jugadores j = getById(jugador);
        j.setSalario(s);
        j.setYears(anos);
        j.setObs(obs);
        j.setActivo(1);
        saveOrUpdateEntity(j, jugador);  
        Equipo e = equipoDAO.getById(destino);
        Plantilla p = new Plantilla();
        PlantillaPK pk = new PlantillaPK();
        pk.setIdEquipo(destino);
        pk.setIdJugador(jugador);
        p.setId(pk);
        plantillaDAO.saveOrUpdateEntity(p, null);
        equipoDAO.saveOrUpdateEntity(e, destino);
    }

    public void actualizaJug(String obs, String player) throws DaoException {
    	Jugadores j = getById(player);
    	j.setObs(obs);
        saveOrUpdateEntity(j, player);  
    }

    public void trade(String destino, int player) throws DaoException {
    	SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.addFilter("id.idJugador", player, FilterType.EQUALS);
    	Plantilla rem = plantillaDAO.getByCriteria(searchCriteria).get(0);
        plantillaDAO.removeEntity(rem.getId());
        Equipo e = equipoDAO.getById(rem.getId().getIdEquipo());
        List<Plantilla> plant = e.getPlantilla();
        plant.remove(rem);
        equipoDAO.saveOrUpdateEntity(e, destino);
        Plantilla p = new Plantilla();
        PlantillaPK pk = new PlantillaPK();
        pk.setIdEquipo(destino);
        pk.setIdJugador(player);
        p.setId(pk);
        plantillaDAO.saveOrUpdateEntity(p, pk);
        
        
    }

    public Jugadores crearJugador(String nombre, String posicion) throws DaoException {
    	Jugadores j = new Jugadores();
    	j.setJugador(nombre);
    	j.setPosicion(posicion);
    	j.setYears("-");
    	j.setActivo(1);
    	saveOrUpdateEntity(j, null);
    	return j;
    }

    public void cut(String destino, int player, double factor) throws DaoException {
    	Jugadores j = getById(player);
    	Equipo e = equipoDAO.getById(destino);
    	Plantilla cut = null;
    	for (Plantilla p:e.getPlantilla()){
    		if (p.getId().getIdJugador()==player){
    			cut = p;
    		}
    	}
    	
    	double s = j.getSalario();
        j.setSalario((double) 0);
        j.setYears("-");
        j.setObs(null);
        String cortadoPor = j.getCortadoPor();
        j.setCortadoPor(cortadoPor+ ("".equals(cortadoPor) || cortadoPor == null ? "" : ", ")+e.getNombre());
        saveOrUpdateEntity(j, player);        
        double penalizacion = 0;
        if (factor > 0) {
            //Se introduce un factor de corte distinto (25% o gratis)
            if (factor != 1) {
                penalizacion = Math.round((s * 0.25) * 100.0) / 100.0;
            }
        } else {
            if (s > 2) {
                penalizacion = Math.round((s * 0.5) * 100.0) / 100.0;
            }
        }
        if (penalizacion !=0){
	        double cortes = (e.getCortes()==null?0:e.getCortes())+penalizacion;
	        e.setCortes(cortes);
        }
        equipoDAO.saveOrUpdateEntity(e, destino);
        plantillaDAO.removeEntity(cut.getId());
        List<Plantilla> p = e.getPlantilla();
        p.remove(cut);
        equipoDAO.saveOrUpdateEntity(e, destino);
    }

    public Jugadores getByName(String jugador) throws DaoException{
    	SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.addFilter("jugador", jugador, FilterType.EQUALS);
        List<Jugadores> result = getByCriteria(searchCriteria);
        if (!result.isEmpty()){
        	return result.get(0);
        }
        return null;
    }
	
}
