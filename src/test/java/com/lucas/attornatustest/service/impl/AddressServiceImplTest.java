package com.lucas.attornatustest.service.impl;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.exception.bad_request.BadRequestException;
import com.lucas.attornatustest.repository.AddressRepository;
import com.lucas.attornatustest.repository.PersonRepository;
import com.lucas.attornatustest.request.AddressRequestBody;
import static com.lucas.attornatustest.util.TestUtils.*;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class AddressServiceImplTest {

	@Mock
	private AddressRepository repository;
	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private AddressServiceImpl service;

	@Test
	@DisplayName("should create an address with a person as owner.")
	void createAddressWithPersonAsOwner() {
		AddressRequestBody addressRequestBody = createAddressDto();
		Person person = createPersonWithoutMainAddressToSave();
		person.setId(addressRequestBody.getPersonId());

		Mockito.when(personRepository.findById(addressRequestBody.getPersonId()))
						.thenReturn(Optional.of(person));

		service.createAddress(addressRequestBody);

		Mockito.verify(repository, Mockito.times(1))
				.save(ArgumentMatchers.any(Address.class));
	}

	@Test
	@DisplayName("Should throw a Bad Request exception when try to find an unknown person id.")
	void createAddressThrowBadRequestExceptionWhenTryToFindAnUnknownPersonId() {
		AddressRequestBody addressDto = createAddressDto();
		Person person = createPersonWithMainAddressToSave();
		person.setId(UUID.randomUUID());

		Mockito.when(personRepository.findById(addressDto.getPersonId()))
				.thenThrow(BadRequestException.class);

		assertThatException().isThrownBy(() -> service.createAddress(addressDto));
	}
}