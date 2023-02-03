package com.lucas.attornatustest.service.impl;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.repository.AddressRepository;
import com.lucas.attornatustest.repository.PersonRepository;
import com.lucas.attornatustest.request.PersonRequestBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.lucas.attornatustest.util.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PersonServiceImplTest {


	@InjectMocks
	private PersonServiceImpl service;
	@Mock
	private PersonRepository personRepository;
	@Mock
	private AddressRepository addressRepository;



	@Test
	void createPersonWhenSuccessful() {
		Person personToBeSaved = createPersonWithoutMainAddressToSave();
		PersonRequestBody personRequestBody = toDto(personToBeSaved);

		Mockito.when(personRepository.save(personToBeSaved)).thenReturn(personToBeSaved);

		Person personSaved = service.createPerson(personRequestBody);

		assertThat(personSaved).isNotNull();
		assertThat(personSaved.getName()).isEqualTo(personToBeSaved.getName());
	}

	@Test
	void updatePersonWhenSuccessful() {
		UUID id = UUID.fromString("0f0da5e1-daf8-40ba-b04b-000ea58a4f62");
		Person personToBeSaved = createPersonWithoutMainAddressToSave();
		PersonRequestBody personRequestBody = createDto();
		personToBeSaved.setName(personRequestBody.getName());
		personToBeSaved.setBirthDate(LocalDate.parse(personRequestBody.getBirthDate(), DateTimeFormatter.ofPattern(
				"dd/MM/yyyy")));

		Mockito.when(personRepository.findById(id)).thenReturn(Optional.of(personToBeSaved));

		service.updatePerson(personRequestBody, id);

		Mockito.verify(personRepository, Mockito.times(1))
				.save(personToBeSaved);
	}


	@Test
	void findOneByIdWhenSuccessful() {
		UUID id = UUID.fromString("0f0da5e1-daf8-40ba-b04b-000ea58a4f62");
		Person personToBeSaved = createPersonWithoutMainAddressToSave();
		personToBeSaved.setId(id);

		Mockito.when(personRepository.findById(id)).thenReturn(Optional.of(personToBeSaved));

		Person personFound = service.findOneById(id);

		assertThat(personFound).isNotNull();
		assertThat(personFound.getId())
				.isNotNull()
				.isEqualTo(personToBeSaved.getId());
	}

	@Test
	void listAllAddressFromPersonWhenSuccessful() {
		Person personToBeSaved = createPersonWithoutMainAddressToSave();
		UUID id = UUID.fromString("0138f1cf-af33-4f16-b90b-a2415ab39e39");
		personToBeSaved.setId(id);
		Address addressToSave = createAddressToSave();

		Mockito.when(personRepository.findById(id)).thenReturn(Optional.of(personToBeSaved));
		Mockito.when(addressRepository.findAllByPerson(personToBeSaved))
				.thenReturn(List.of(addressToSave));

		List<Address> addresses = service.listAllAddressByPerson(id);

		assertThat(addresses).isNotEmpty();
	}


	@Test
	void findAllPersonsWhenSuccessful() {
		List<Person> personsList = List.of(createPersonWithoutMainAddressToSave());
		Mockito.when(personRepository.findAll()).thenReturn(personsList);

		List<Person> personList = service.findAll();
		assertThat(personList)
				.isNotNull()
				.isNotEmpty()
				.hasSize(1);
		assertThat(personList.get(0).getName()).isEqualTo("Caleb César Rocha");
	}

}