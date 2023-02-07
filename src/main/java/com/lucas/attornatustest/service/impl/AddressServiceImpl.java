package com.lucas.attornatustest.service.impl;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.exception.bad_request.BadRequestException;
import com.lucas.attornatustest.mapper.AddressMapper;
import com.lucas.attornatustest.repository.AddressRepository;
import com.lucas.attornatustest.repository.PersonRepository;
import com.lucas.attornatustest.request.AddressRequestBody;
import com.lucas.attornatustest.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {

	private final AddressRepository repository;
	private final PersonRepository personRepository;
	private final AddressMapper mapper;

	@Override
	public Address createAddress(AddressRequestBody addressRequestBody) {
		Address addressToBeSaved = mapper.toAddress(addressRequestBody);

		Person person = personRepository
				.findById(addressToBeSaved.getPerson().getId())
				.orElseThrow(() -> new BadRequestException("Não existe nenhuma pessoa com id informado."));
		
		addressToBeSaved.setPerson(person);
		return repository.save(addressToBeSaved);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Address> listAllAddressByPerson(UUID personId) {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new BadRequestException("Não existe nenhuma pessoa com id informado."));
		return repository.findAllByPerson(person);
	}
}
