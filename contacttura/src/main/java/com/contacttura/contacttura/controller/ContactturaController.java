package com.contacttura.contacttura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contacttura.contacttura.model.Contacttura;
import com.contacttura.contacttura.repository.ContactturaRepository;

@RestController
@RequestMapping({ "/contacttura" })
public class ContactturaController {
	
	@Autowired
	private ContactturaRepository repository;
	
	@GetMapping
//	http://localhost:8080/contcttura
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
//	Create
	@PostMapping
	public Contacttura create(@RequestBody Contacttura contacttura) {
		return repository.save(contacttura);
		
	}
	
//	Update
	@PutMapping(value = "{id}")
	public ResponseEntity update(@PathVariable long id, @RequestBody Contacttura contacttura) {
	return repository.findById(id).map(record -> {
		record.setNome(contacttura.getNome());
		record.setEmail(contacttura.getEmail());
		record.setPhone(contacttura.getPhone());
		Contacttura update = repository.save(record);
		
		return ResponseEntity.ok().body(update);
	}).orElse(ResponseEntity.notFound().build());
	
	}
	
	@DeleteMapping(path = { "/{id}" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity <?> delete (@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			
			return ResponseEntity.ok().body("Cliente " + record.getNome() + "Deletado");
		}).orElse(ResponseEntity.notFound().build());
		
		}
	}
	

