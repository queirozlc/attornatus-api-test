package com.lucas.attornatustest.service.impl;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.exception.bad_request.BadRequestException;
import com.lucas.attornatustest.mapper.PersonMapper;
import com.lucas.attornatustest.repository.AddressRepository;
import com.lucas.attornatustest.repository.PersonRepository;
import com.lucas.attornatustest.request.PersonRequestBody;
import com.lucas.attornatustest.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.lucas.attornatustest.util.DateFormatter.toLocalDate;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

	private final PersonRepository repository;
	private final AddressRepository addressRepository;

	@Transactional
	@Override
	public Person createPerson(@Valid PersonRequestBody personRequestBody)  {
		Person personToBeSave = PersonMapper.INSTANCE.toPerson(personRequestBody);
		LocalDate birthDate = toLocalDate(personRequestBody.getBirthDate());

		if (birthDate.isAfter(LocalDate.now())) {
			throw new BadRequestException("A data de nascimento não pode ser maior que a data atual.");
		}

		personToBeSave.setBirthDate(birthDate);
		Person personSaved = repository.save(personToBeSave);
		if (personSaved.getMainAddress() != null) {
			personSaved.getMainAddress().setPerson(personSaved);
			addressRepository.save(personSaved.getMainAddress());
		}
		return personSaved;
	}

	@Transactional
	@Override
	public Person updatePerson(@Valid PersonRequestBody personRequestBody, UUID id) {
		Person personToBeUpdated = repository.findById(id)
				.orElseThrow(() -> new BadRequestException("Não existe usuário com id informado."));
		LocalDate birthDate = toLocalDate(personRequestBody.getBirthDate());
		Person personRequest = PersonMapper.INSTANCE.toPerson(personRequestBody);

		if (birthDate.getYear() > LocalDate.now().getYear()) {
			throw new BadRequestException("O ano de nascimento não pode ser maior que o atual.");
		}

		if (personRequest.getMainAddress() != null) {
			personRequest.getMainAddress().setPerson(personRequest);
			personRequest.getMainAddress().getPerson().setId(personToBeUpdated.getId());
			addressRepository.save(personRequest.getMainAddress());
		}

		personRequest.setId(personToBeUpdated.getId());
		personRequest.setBirthDate(birthDate);
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

	@Transactional(readOnly = true)
	@Override
	public List<Address> listAllAddressByPerson(UUID personId) {
		Person person = repository.findById(personId)
				.orElseThrow(() -> new BadRequestException("Não existe nenhuma pessoa com esse id."));
		return addressRepository.findAllByPerson(person);
	}
}
