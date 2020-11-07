package com.soaint.evaluacion.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soaint.evaluacion.dto.ApiResponse;
import com.soaint.evaluacion.dto.ErrorResponse;
import com.soaint.evaluacion.exception.ApiException;
import com.soaint.evaluacion.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginRest {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody String request) {

		log.info(request);
		ApiResponse response;
		
		try {
			
			response = userService.login(request);
			
		} catch (ApiException e) {
			return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage(), e.getDetailMessage()), HttpStatus.PRECONDITION_FAILED);
			
		}
		
		return ResponseEntity.ok(response);

	}

}
