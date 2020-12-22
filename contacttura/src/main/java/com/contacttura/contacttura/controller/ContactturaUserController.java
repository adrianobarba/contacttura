package com.contacttura.contacttura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.contacttura.contacttura.model.ContactturaUser;
import com.contacttura.contacttura.repository.ContactturaUserRepository;


@RestController
@RequestMapping({"/user"})
public class ContactturaUserController {
	
	@Autowired
	private ContactturaUserRepository repository;
	
		
	@PostMapping
	public ContactturaUser create(@RequestBody ContactturaUser contactturaUser) {
	contactturaUser.setPassword(cripto(contactturaUser.getPassword()));// criptografando senha
		return repository.save(contactturaUser);
	}
	
	@DeleteMapping(path = {"{/id}"})
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id).map(record -> {
							repository.deleteById(id);
							return ResponseEntity.ok().build();
							}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody ContactturaUser contactturauser){
		return repository.findById(id).map(atualizar -> {
			atualizar.setNome(contactturauser.getNome());
			atualizar.setUsername(contactturauser.getUsername());
			atualizar.setAdmin(contactturauser.getAdmin());
			atualizar.setPassword(contactturauser.getPassword());
			ContactturaUser update = repository.save(atualizar);
			return ResponseEntity.ok().body(update);
		}).orElse(ResponseEntity.notFound().build());
			
		}
	
	
	@GetMapping
	public List findAll() {
		return repository.findAll();	
	}
	
	
	// criptografia de senha
	private String cripto( String password) {
		BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
		String word = pass.encode(password);
		return word;
			
		
	}
}