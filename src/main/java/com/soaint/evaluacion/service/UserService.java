package com.soaint.evaluacion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soaint.evaluacion.config.JwtToken;
import com.soaint.evaluacion.constant.ApiState;
import com.soaint.evaluacion.dao.UserDao;
import com.soaint.evaluacion.dto.ApiResponse;
import com.soaint.evaluacion.dto.AuthUser;
import com.soaint.evaluacion.dto.converter.AuthUserConverter;
import com.soaint.evaluacion.entity.User;
import com.soaint.evaluacion.exception.ApiException;
import com.soaint.evaluacion.util.CryptPassword;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

	@Autowired
	UserDao userDao;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtToken jwtToken;
	@Autowired
	CryptPassword cryptPassword;
	@Autowired
	AuthUserConverter authUserConverter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();

		roles.add(new SimpleGrantedAuthority("ADMIN"));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
	}

	public ApiResponse login(String request) throws ApiException {

		AuthUser authUser;

		JsonNode root;

		String username = null;
		String password = null;

		try {
			root = new ObjectMapper().readTree(request);

			username = root.path("username").asText();
			password = root.path("password").asText();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());

		} catch (DisabledException e) {
			throw new ApiException(ApiState.USER_DISABLED.getCode(), ApiState.USER_DISABLED.getMessage(),
					e.getMessage());

		} catch (BadCredentialsException e) {
			throw new ApiException(ApiState.LOGIN_FAILED.getCode(), ApiState.LOGIN_FAILED.getMessage(), e.getMessage());

		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String token = jwtToken.generateToken(userDetails);
		System.out.println(token);
		User user = userDao.findByUsername(username);

		authUser = new AuthUser();
		authUser = authUserConverter.fromEntity(user);

		authUser.setToken(token);

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), authUser, null);
	}

}
