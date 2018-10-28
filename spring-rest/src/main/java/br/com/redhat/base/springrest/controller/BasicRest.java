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

import br.com.redhat.base.springrest.domain.Basic;
import br.com.redhat.base.springrest.persistence.BasicPersistence;

@RestController
@RequestMapping("basic")
public class BasicRest {

	@Autowired
	private BasicPersistence persistence;
	
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Basic get(@PathVariable Long id) {
		return persistence.findOne(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody Basic basic) {
		persistence.save(basic);
	}
	
}
