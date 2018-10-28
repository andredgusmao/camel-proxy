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

import br.com.redhat.base.springrest.domain.Simple;
import br.com.redhat.base.springrest.persistence.SimplePersistence;

@RestController
@RequestMapping("simple")
public class SimpleRest {

	@Autowired
	private SimplePersistence persistence;
	
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Simple get(@PathVariable Long id) {
		return persistence.findOne(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody Simple simple) {
		persistence.save(simple);
	}
	
	@GetMapping(value = "status", produces = MediaType.APPLICATION_JSON_VALUE)
	public String status() {
		System.out.println("Status recebido");
		return "{ \"status\": \"ok\" }";
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping("/post")
	public void post2() {
		System.out.println("Ok POST!");
	}

}
