package com.lucas.attornatustest.service.impl;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.exception.bad_request.BadRequestException;
import com.lucas.attornatustest.mapper.AddressMapper;
import com.lucas.attornatustest.repository.AddressRepository;
import com.lucas.attornatustest.repository.PersonRepository;
import com.lucas.attornatustest.request.AddressRequestBody;
import com.lucas.attornatustest.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressRepository repository;
	private final PersonRepository personRepository;

	@Transactional
	@Override
	public Address createAddress(@Valid AddressRequestBody addressRequestBody) {
		Address addressToBeSaved = AddressMapper.INSTANCE.toAddress(addressRequestBody);
		Person person = personRepository.findById(addressRequestBody.getPersonId())
				.orElseThrow(() ->
						new BadRequestException("Não existe nenhuma pessoa com id informado."));
		addressToBeSaved.setPerson(person);
		return repository.save(addressToBeSaved);
	}
}
