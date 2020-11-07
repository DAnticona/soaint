package com.soaint.evaluacion.dto.converter;

import org.springframework.stereotype.Component;

import com.soaint.evaluacion.dto.AuthUser;
import com.soaint.evaluacion.entity.User;

@Component
public class AuthUserConverter extends AbstractConverter<User, AuthUser> {

	@Override
	public AuthUser fromEntity(User entity) {
		AuthUser user = new AuthUser();
		user.setIdUser(entity.getIdUser());
		user.setUsername(entity.getUsername());
		return user;
	}

	@Override
	public User fromDto(AuthUser dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
