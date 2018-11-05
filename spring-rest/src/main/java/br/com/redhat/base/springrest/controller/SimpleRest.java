package br.com.redhat.base.springrest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
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

	@ResponseStatus(code = CREATED)
	@GetMapping("setup")
	public void setup() {
		persistence.save(new Simple("simple 1", "value 1"));
		persistence.save(new Simple("simple 2", "value 2"));
		persistence.save(new Simple("simple 3", "value 3"));
		persistence.save(new Simple("simple 4", "value 4"));
		persistence.save(new Simple("simple 5", "value 5"));
	}
	
	@GetMapping(value = "{id}", produces = APPLICATION_JSON_UTF8_VALUE)
	public Simple get(@PathVariable Long id) {
		return persistence.findOne(id);
	}
	
	@ResponseStatus(code = CREATED)
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
	public void post(@RequestBody Simple simple) {
		persistence.save(simple);
	}
	
	@GetMapping(value = "status", produces = APPLICATION_JSON_UTF8_VALUE)
	public String status() {
		System.out.println("Status recebido");
		return "{ \"status\": \"ok\" }";
	}

}
