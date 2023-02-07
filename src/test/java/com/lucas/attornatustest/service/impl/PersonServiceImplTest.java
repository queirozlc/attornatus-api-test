package com.lucas.attornatustest.service.impl;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.exception.bad_request.BadRequestException;
import com.lucas.attornatustest.exception.date_parse_exception.DateParseException;
import com.lucas.attornatustest.mapper.PersonMapper;
import com.lucas.attornatustest.repository.AddressRepository;
import com.lucas.attornatustest.repository.PersonRepository;
import com.lucas.attornatustest.request.PersonRequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PersonServiceImplTest {
	public static final UUID ID = UUID.fromString("7c5c3dac-d966-4030-8aa5-809529298c1e");
	public static final LocalDate BIRTH_DATE = LocalDate.parse("2007-12-03");
	public static final String NAME = "person";
	public static final String REQUEST_BIRTH_DATE = "04/12/2003";
	public static final String PERSON_DONT_EXIST = "Não existe nenhuma pessoa com esse id.";
	public static Person person;
	public static Optional<Person> personOptional;
	public static PersonRequestBody personRequestBody;
	public static Address address;
	public static List<Person> personList;

	@InjectMocks
	private PersonServiceImpl service;
	@Mock
	private PersonRepository repository;
	@Mock
	private AddressRepository addressRepository;
	@Mock
	private PersonMapper mapper;

	@BeforeEach
	void setUp() {
		initClasses();
	}

	@Test
	@DisplayName("Create person without main address when successful")
	void createPersonWithoutMainAddressWhenSuccessful() {
		when(mapper.toPerson(any(PersonRequestBody.class))).thenReturn(person);
		when(repository.save(any(Person.class))).thenReturn(person);

		Person response = service.createPerson(personRequestBody);

		assertNotNull(response);
		assertEquals(Person.class, response.getClass());
		assertEquals(UUID.class, response.getId().getClass());
		assertEquals(NAME, response.getName());
		verify(repository, times(1)).save(person);
	}

	@Test
	@DisplayName("Create person with main address when successful")
	void createPersonWithMainAddressWhenSuccessful() {
		when(mapper.toPerson(any(PersonRequestBody.class))).thenReturn(person);
		when(repository.save(any(Person.class))).thenReturn(person);
		when(addressRepository.save(any(Address.class))).thenReturn(address);
		person.setMainAddress(address);

		Person response = service.createPerson(personRequestBody);

		assertNotNull(response);
		assertEquals(Person.class, response.getClass());
		assertEquals(UUID.class, response.getId().getClass());
		assertEquals(NAME, response.getName());
		verify(repository, times(1)).save(person);
	}

	@Test
	@DisplayName("Create person throws date parse exception when birthDate is invalid")
	void createPersonThrowsDateParseExceptionWhenBirthDateIsInvalid() {
		when(mapper.toPerson(any(PersonRequestBody.class))).thenThrow(DateParseException.class);

		try {
			personRequestBody.setBirthDate("1/13/2010");
			mapper.toPerson(personRequestBody);
		}
		catch (Exception e) {
			assertEquals(DateParseException.class, e.getClass());
		}
	}

	@Test
	@DisplayName("Create person throws bad request exception when birthDate is greater than current date")
	void createPersonThrowsBadRequestExceptionWhenBirthDateIsGreaterThanCurrentDate() {
		when(mapper.toPerson(any(PersonRequestBody.class))).thenThrow(DateParseException.class);

		try {
			personRequestBody.setBirthDate(LocalDate.now().plusDays(1).toString());
			mapper.toPerson(personRequestBody);
		}
		catch (Exception e) {
			assertEquals(DateParseException.class, e.getClass());
		}
	}

	@Test
	@DisplayName("Update person when successful")
	void updatePersonWhenSuccessful() {
		when(repository.findById(any(UUID.class))).thenReturn(personOptional);
		when(mapper.toPerson(any(PersonRequestBody.class))).thenReturn(person);
		when(repository.save(any(Person.class))).thenReturn(person);

		Person response = service.updatePerson(personRequestBody, ID);

		assertNotNull(response);
		assertEquals(Person.class, response.getClass());
		assertEquals(UUID.class, response.getId().getClass());
		assertEquals(NAME, response.getName());
		verify(repository, times(1)).save(person);
	}

	@Test
	@DisplayName("Update person with main address when successful")
	void updatePersonWithMainAddressWhenSuccessful() {
		when(repository.findById(any(UUID.class))).thenReturn(personOptional);
		when(mapper.toPerson(any(PersonRequestBody.class))).thenReturn(person);
		when(repository.save(any(Person.class))).thenReturn(person);
		when(addressRepository.save(any(Address.class))).thenReturn(address);
		person.setMainAddress(address);

		Person response = service.updatePerson(personRequestBody, ID);

		assertNotNull(response);
		assertEquals(Person.class, response.getClass());
		assertEquals(UUID.class, response.getId().getClass());
		assertEquals(NAME, response.getName());
		verify(repository, times(1)).save(person);
	}

	@Test
	@DisplayName("Update person throws bad request exception when no person found.")
	void updatePersonThrowsBadRequestExceptionWhenNoPersonFound() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

		try {
			service.updatePerson(personRequestBody, ID);
		}
		catch (Exception e) {
			assertEquals(BadRequestException.class, e.getClass());
			assertEquals(PERSON_DONT_EXIST, e.getMessage());
		}
	}

	@Test
	@DisplayName("Update person throws date parse exception when birthDate is invalid")
	void updatePersonThrowsDateParseExceptionWhenBirthDateIsInvalid() {
		when(repository.findById(any(UUID.class))).thenReturn(personOptional);
		when(mapper.toPerson(any(PersonRequestBody.class))).thenThrow(DateParseException.class);

		try {
			personRequestBody.setBirthDate("1/13/2010");
			mapper.toPerson(personRequestBody);
		}
		catch (Exception e) {
			assertEquals(DateParseException.class, e.getClass());
		}
	}

	@Test
	@DisplayName("Update person throws bad request exception when birthDate is greater than current date")
	void updatePersonThrowsBadRequestExceptionWhenBirthDateIsGreaterThanCurrentDate() {
		when(repository.findById(any(UUID.class))).thenReturn(personOptional);
		when(mapper.toPerson(any(PersonRequestBody.class))).thenThrow(DateParseException.class);

		try {
			personRequestBody.setBirthDate(LocalDate.now().plusDays(1).toString());
			mapper.toPerson(personRequestBody);
		}
		catch (Exception e) {
			assertEquals(DateParseException.class, e.getClass());
		}
	}

	@Test
	@DisplayName("Find one person by id when successful")
	void findOnePersonByIdWhenSuccessful() {
		when(repository.findById(any(UUID.class))).thenReturn(personOptional);
		Person response = service.findOneById(ID);

		assertEquals(Person.class, response.getClass());
		assertEquals(NAME, response.getName());
		assertEquals(BIRTH_DATE, response.getBirthDate());
	}

	@Test
	@DisplayName("Find one throws bad request exception when no person found.")
	void findOneThrowsBadRequestExceptionWhenNoPersonFound() {
		when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

		try {
			service.findOneById(ID);
		}
		catch (Exception e) {
			assertEquals(BadRequestException.class, e.getClass());
			assertEquals(PERSON_DONT_EXIST, e.getMessage());
		}
	}

	@Test
	@DisplayName("Find all Persons when successful")
	void findAllPersonsWhenSuccessful() {
		when(repository.findAll()).thenReturn(personList);
		List<Person> response = service.findAll();

		assertEquals(Person.class, response.get(0).getClass());
		assertEquals(NAME, response.get(0).getName());
		assertEquals(1, response.size());
	}

	private void initClasses() {
		person = new Person(ID, NAME, BIRTH_DATE, new Address());
		personOptional = Optional.of(person);
		personRequestBody = new PersonRequestBody(NAME, REQUEST_BIRTH_DATE, null);
		personList = List.of(person);
		address = Address
				.builder()
				.person(person)
				.number(10)
				.zipCode("12345")
				.city("New York")
				.streetAddress("123 Main Street")
				.build();
	}
}