package es.nbajugones.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.LogDAO;
import es.nbajugones.dbdao.data.RenovacionesDAO;
import es.nbajugones.dto.RenovacionDTO;
import es.nbajugones.dto.entities.Renovacione;
import es.nbajugones.dto.entities.pk.RenovacionePK;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

public class RenovacionesService {

	@Autowired
	RenovacionesDAO renovacionesDAO;
	
	@Autowired
	LogDAO logDAO;

	@Transactional
	public List<RenovacionDTO> get(int y, int tanda) throws ServiceException {
		try {
			List<Renovacione> list = renovacionesDAO.get(y, tanda);
			List<RenovacionDTO> result = new ArrayList<RenovacionDTO>();
			for (Renovacione r : list) {
				result.add(new RenovacionDTO(r));
			}
			Collections.sort(result, new Comparator<RenovacionDTO>() {

				public int compare(RenovacionDTO o1, RenovacionDTO o2) {
					if (o1.getPuntos() == o2.getPuntos()) {
						return (int) (o2.getPromedio() - o1.getPromedio());
					}
					return (int) (o2.getPuntos() - o1.getPuntos());
				}

			});
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	public RenovacionDTO getRenovacion(int y, int idJugador) throws ServiceException {
		RenovacionePK pk = new RenovacionePK();
		pk.setIdJugador(idJugador);
		pk.setYear(y);
		return new RenovacionDTO(renovacionesDAO.getById(pk));
	}

	@Transactional
	public void renovacionTemp(String equipo, double salario, int anos, int player, int ano) throws ServiceException {
		try {
			renovacionesDAO.renovacionTemp(equipo, salario, anos, player, ano);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	public void renovar(int player, String origen, String destino, double salario, int anos) throws ServiceException {
		try {
			
			if (origen.equals(destino)){				
				if (salario < 2){
					salario =2;
				}
				logDAO.renovar(player, destino, salario, ""+anos);
			} else {
				logDAO.noRenovar2(player, origen);
				logDAO.ficharRenovaciones(player, destino, salario, ""+anos);
			}
			renovacionesDAO.renovar(player, destino, salario, anos);
			
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	public void noRenovar(int player, String origen) throws ServiceException {
		try {
			renovacionesDAO.noRenovar(player, origen);
			logDAO.noRenovar(player, origen);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
