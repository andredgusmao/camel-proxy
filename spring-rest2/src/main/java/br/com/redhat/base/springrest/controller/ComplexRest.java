package br.com.redhat.base.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.redhat.base.springrest.domain.Complex;
import br.com.redhat.base.springrest.persistence.ComplexPersistence;

@RestController
@RequestMapping("complex")
public class ComplexRest {

	@Autowired
	private ComplexPersistence persistence;

	@ResponseStatus(code = HttpStatus.CREATED)
	@GetMapping("setup")
	public void setup() {
		persistence.save(new Complex("chave1", "valor1", 1, true));
		persistence.save(new Complex("chave2", "valor2", 2, false));
		persistence.save(new Complex("chave3", "valor3", 3, true));
	}	
	
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Complex get(@PathVariable Long id) {
		return persistence.findOne(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody Complex simple) {
		persistence.save(simple);
	}
	
	@GetMapping(value = "status", produces = MediaType.APPLICATION_JSON_VALUE)
	public String status() {
		System.out.println("Status recebido");
		return "{ \"status\": \"ok\" }";
	}

}
