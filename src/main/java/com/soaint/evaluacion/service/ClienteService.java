package com.soaint.evaluacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soaint.evaluacion.constant.ApiState;
import com.soaint.evaluacion.dao.ClienteDao;
import com.soaint.evaluacion.dto.ApiResponse;
import com.soaint.evaluacion.entity.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	ClienteDao clienteDao;
	
	public ApiResponse findAll() {
		
		List<Cliente> clientes = clienteDao.findAll(); 
		
		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), clientes, clientes.size());
	}
	
	public ApiResponse findById(Integer id) {
		
		Cliente cliente = clienteDao.findById(id).orElse(null); 
		
		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), cliente, null);
	}
	
	public ApiResponse save(String request) {
		
		Cliente cliente;

		JsonNode root;
		Integer id = null;
		String dni = null;
		String nombre = null;
		String apellido = null;
		String telefono = null;
		String email = null;

		try {
			root = new ObjectMapper().readTree(request);

			id = root.path("id").asInt();
			dni = root.path("dni").asText();
			nombre = root.path("nombre").asText();
			apellido = root.path("apellido").asText();
			telefono = root.path("telefono").asText();
			email = root.path("email").asText();

		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetros inválidos");
		}
		
		cliente = new Cliente();
		
		cliente.setIdCliente(id);
		cliente.setDni(dni);
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setTelefono(telefono);
		cliente.setEmail(email);

		cliente = clienteDao.save(cliente);

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), cliente, null);
	}
	
	public ApiResponse delete(Integer id) {
		
		Cliente cliente = clienteDao.findById(id).orElse(null);
		
		if(cliente == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontró el cliente");
		}
		
		clienteDao.delete(cliente);
		
		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), cliente, null);
	}

}
