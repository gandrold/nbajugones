package es.nbajugones.frontend.security;

import es.nbajugones.dbdao.data.AdminDAO;
import es.nbajugones.dto.entities.Admin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class JugonesUserDetailsService implements UserDetailsService{

	@Autowired
	AdminDAO adminDAO;

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException, DataAccessException {

		//Obtenemos los datos del usuario
		Admin admin = adminDAO.getById(nombreUsuario);
		if(admin == null){
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

		//Ahora que el usuario se ha registrado en el sistema, busco sus roles
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    	if(nombreUsuario.equals("dm")){
    		authorities.add(new GrantedAuthorityImpl(IRoles.ADMIN));
    	} else {
			authorities.add(new GrantedAuthorityImpl(IRoles.GM));
		}

		//Creamos el objeto contenedor de los datos del usuario, UserDetails
		User user = new User(admin.getUsuario(), admin.getClave(), true, true, true, true, authorities);

		return user;
	}


	/**
	 * Comprueba si el usuario registrado esta o no en un rol determinado.
	 * @param role
	 * @return
	 */
	public boolean isUserInRole(String role){
		SecurityContext ctx = SecurityContextHolder.getContext();
		User user = (User) ctx.getAuthentication().getPrincipal();
		Collection<GrantedAuthority> authorities = user.getAuthorities();

		for(GrantedAuthority authority : authorities){
			if(authority.getAuthority().equalsIgnoreCase(role)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Recupera el usuario registrado en el sistema
	 * @return
	 */
	public User getRegisteredUser(){
		SecurityContext ctx = SecurityContextHolder.getContext();
		User user = (User) ctx.getAuthentication().getPrincipal();
		return user;
	}


}
