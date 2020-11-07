package com.soaint.evaluacion.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soaint.evaluacion.dto.ApiResponse;
import com.soaint.evaluacion.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		
		ApiResponse response = clienteService.findAll();
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		
		ApiResponse response = clienteService.findById(id);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody String request) {
		
		ApiResponse response = clienteService.save(request);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		ApiResponse response = clienteService.delete(id);
		
		return ResponseEntity.ok(response);
	}
}
