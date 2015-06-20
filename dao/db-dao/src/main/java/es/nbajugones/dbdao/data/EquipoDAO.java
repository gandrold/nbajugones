package es.nbajugones.dbdao.data;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.EvaluacionDTO;
import es.nbajugones.dto.KeyValue;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Plantilla;
import es.nbajugones.exception.dbdao.DaoException;

public class EquipoDAO extends GenericDAOImpl<Equipo> {

	@Autowired
	JugadoresDAO jugadoresDAO;
	
	
	@SuppressWarnings("unchecked")
	public List<KeyValue> getEquipos() throws DaoException{
		String sqlQuery="SELECT e.id_equipo as 'key', e.nombre as 'value' from Equipos e";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(KeyValue.class));
		return (List<KeyValue>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<EvaluacionDTO> evaluar() throws DaoException{
		String sqlQuery="select e.id_equipo as equipo, e.logo_draft as logo, (select sum(j.salario) from jugadores j inner join plantillas p on "
                + "j.id_jugador=p.id_jugador where p.id_equipo=e.id_equipo and j.obs<>'FA') as salarios,e.sanciones,e.bonus_ant as bonusAnt, "
                + "e.bonus_act as bonusAct,e.lesionados,e.cortes from equipos e";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(EvaluacionDTO.class));
		List<EvaluacionDTO> evaluacion = (List<EvaluacionDTO>) query.list();
		for (EvaluacionDTO e:evaluacion){
			Equipo equipo = getById(e.getEquipo());
			List<Integer> jugadores = new ArrayList<Integer>();
			for (Plantilla p:equipo.getPlantilla()){
				jugadores.add(p.getId().getIdJugador());
			}
			List<Jugadores> plantilla = jugadoresDAO.getPlantilla(jugadores);
			for (Jugadores j:plantilla){
				if ("FA".equals(j.getObs())){
					e.setFa(e.getFa()+1);
				} 
				e.setJugadores(e.getJugadores()+1);
			}
		}
		return (List<EvaluacionDTO>) query.list();
	}
	
}
