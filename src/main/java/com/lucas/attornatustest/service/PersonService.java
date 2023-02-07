package com.lucas.attornatustest.service;

import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.request.PersonRequestBody;

import java.util.List;
import java.util.UUID;

public interface PersonService {

	Person createPerson(PersonRequestBody personRequestBody);

	Person updatePerson(PersonRequestBody personRequestBody, UUID id);

	Person findOneById(UUID id);

	List<Person> findAll();



}
