/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.frontend.security;

import es.nbajugones.dbdao.data.AdminDAO;
import es.nbajugones.dto.entities.Admin;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *
 * @author iblanco
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		Admin admin = adminDAO.getById(name);
		if (admin != null && password.equals(admin.getClave())) {
			//Ahora que el usuario se ha registrado en el sistema, busco sus roles
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if (name.equals("dm")) {
				authorities.add(new GrantedAuthorityImpl(IRoles.ADMIN));
			} else {
				authorities.add(new GrantedAuthorityImpl(IRoles.GM));
			}
			Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
			return auth;
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
