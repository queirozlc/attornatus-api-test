package com.lucas.attornatustest.controller;

import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.request.PersonRequestBody;
import com.lucas.attornatustest.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

	private final PersonService service;

	@GetMapping
	public ResponseEntity<List<Person>> listAll() {
		return new ResponseEntity<>(service.findAll(), OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> findPersonById(@PathVariable UUID id) {
		return new ResponseEntity<>(service.findOneById(id), OK);
	}

	@PostMapping
	public ResponseEntity<Person> createPerson(@RequestBody @Valid PersonRequestBody requestBody) {
		Person personCreated = service.createPerson(requestBody);
		return new ResponseEntity<>(personCreated, CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody @Valid PersonRequestBody requestBody,
	                                           @PathVariable UUID id) {
		return new ResponseEntity<>(service.updatePerson(requestBody, id), OK);
	}
}
