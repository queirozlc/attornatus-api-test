package com.lucas.attornatustest.service.impl;

import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.exception.bad_request.BadRequestException;
import com.lucas.attornatustest.exception.date_parse_exception.DateParseException;
import com.lucas.attornatustest.mapper.PersonMapper;
import com.lucas.attornatustest.repository.AddressRepository;
import com.lucas.attornatustest.repository.PersonRepository;
import com.lucas.attornatustest.request.PersonRequestBody;
import com.lucas.attornatustest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements PersonService {

	private final PersonRepository repository;
	private final AddressRepository addressRepository;
	private final PersonMapper mapper;

	@Override
	public Person createPerson(PersonRequestBody personRequestBody) {
		Person personToBeSave = toPersonEntity(personRequestBody);

		Person personSaved = repository.save(personToBeSave);
		if (personSaved.getMainAddress() != null) {
			personSaved.getMainAddress().setPerson(personSaved);
			addressRepository.save(personSaved.getMainAddress());
		}
		return personSaved;
	}

	@Override
	public Person updatePerson(PersonRequestBody personRequestBody, UUID id) {
		Person personToBeUpdated = repository.findById(id)
				.orElseThrow(() -> new BadRequestException("Não existe nenhuma pessoa com esse id."));
		Person personRequest = toPersonEntity(personRequestBody);

		if (personRequest.getMainAddress() != null) {
			personRequest.getMainAddress().setPerson(personRequest);
			personRequest.getMainAddress().getPerson().setId(personToBeUpdated.getId());
			addressRepository.save(personRequest.getMainAddress());
		}

		personRequest.setId(personToBeUpdated.getId());
		return repository.save(personRequest);
	}

	@Transactional(readOnly = true)
	@Override
	public Person findOneById(UUID id) {
		return repository.findById(id)
				.orElseThrow(() -> new BadRequestException("Não existe nenhuma pessoa com esse id."));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Person> findAll() {
		return repository.findAll();
	}

	private Person toPersonEntity(PersonRequestBody personRequestBody) {
		Person personToBeSave;
		try {
			personToBeSave = mapper.toPerson(personRequestBody);
			if (personToBeSave.getBirthDate().isAfter(LocalDate.now())) {
				throw new BadRequestException("O ano de nascimento não pode ser maior que o atual.");
			}
		}
		catch (DateTimeParseException e) {
			throw new DateParseException(e.getMessage(), e.getParsedString(), e.getErrorIndex());
		}
		return personToBeSave;
	}

}
