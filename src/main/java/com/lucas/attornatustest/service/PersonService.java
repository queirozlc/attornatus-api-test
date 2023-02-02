package com.lucas.attornatustest.service;

import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.request.person.PersonPostRequestBody;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {

	Person createPerson(@Valid PersonPostRequestBody personPostRequestBody);

}
