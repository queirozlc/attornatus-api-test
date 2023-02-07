package com.lucas.attornatustest.service.impl;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.exception.bad_request.BadRequestException;
import com.lucas.attornatustest.mapper.AddressMapper;
import com.lucas.attornatustest.repository.AddressRepository;
import com.lucas.attornatustest.repository.PersonRepository;
import com.lucas.attornatustest.request.AddressRequestBody;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AddressServiceImplTest {
	public static final UUID ID = UUID.fromString("7c5c3dac-d966-4030-8aa5-809529298c1e");
	public static final LocalDate BIRTH_DATE = LocalDate.parse("2007-12-03");
	public static final String NAME = "person";
	public static final String PERSON_DONT_EXIST = "Não existe nenhuma pessoa com id informado.";
	public static final String CITY = "New York";
	public static final String STREET_ADDRESS = "123 Main Street";
	public static final String ZIP_CODE = "12345";
	public static final int NUMBER = 10;
	public static Person person;
	public static Optional<Person> personOptional;
	public static AddressRequestBody addressRequestBody;
	public static Address address;
	public static List<Address> addresses;

	@InjectMocks
	private AddressServiceImpl addressService;
	@Mock
	private AddressRepository repository;
	@Mock
	private PersonRepository personRepository;
	@Mock
	private AddressMapper mapper;

	@BeforeEach
	void setUp() {
		initClasses();
	}

	@Test
	@DisplayName("Create address when successful")
	void createAddressWhenSuccessful() {
		when(mapper.toAddress(any(AddressRequestBody.class))).thenReturn(address);
		when(repository.save(any(Address.class))).thenReturn(address);
		when(personRepository.findById(any(UUID.class))).thenReturn(personOptional);

		Address response = addressService.createAddress(addressRequestBody);

		assertNotNull(response);
		assertEquals(Address.class, response.getClass());
		assertEquals(UUID.class, response.getId().getClass());
		assertEquals(Person.class, address.getPerson().getClass());
	}

	@Test
	@DisplayName("Create address throws bad request exception when person doesn't found")
	void createAddressThrowsBadRequestExceptionWhenPersonDoesNotExist() {
		when(mapper.toAddress(any(AddressRequestBody.class))).thenReturn(address);
		when(repository.save(any(Address.class))).thenReturn(address);
		when(personRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

		Exception exception = assertThrows(BadRequestException.class,
				() -> addressService.createAddress(addressRequestBody));

		assertEquals(PERSON_DONT_EXIST, exception.getMessage());
	}

	@Test
	@DisplayName("List all addresses by person owner when successful")
	void listAllAddressesByPersonOwnerWhenSuccessful() {
		when(personRepository.findById(any(UUID.class))).thenReturn(personOptional);
		when(repository.findAllByPerson(any(Person.class))).thenReturn(addresses);

		List<Address> response = addressService.listAllAddressByPerson(ID);

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Address.class, response.get(0).getClass());
		assertEquals(UUID.class, response.get(0).getId().getClass());
		assertEquals(Person.class, response.get(0).getPerson().getClass());
	}

	@Test
	@DisplayName("List all addresses by person owner throws bad request exception when person doesn't found")
	void listAllAddressesByPersonOwnerThrowsBadRequestExceptionWhenPersonDoesNotExist() {
		when(personRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

		Exception exception = assertThrows(BadRequestException.class,
				() -> addressService.listAllAddressByPerson(ID));

		assertEquals(PERSON_DONT_EXIST, exception.getMessage());
	}

	private void initClasses() {
		person = new Person(ID, NAME, BIRTH_DATE, new Address());
		personOptional = Optional.of(person);
		addressRequestBody = AddressRequestBody
				.builder()
				.personId(ID)
				.city(CITY)
				.number(NUMBER)
				.streetAddress(STREET_ADDRESS)
				.zipCode(ZIP_CODE)
				.build();
		address = Address
				.builder()
				.id(ID)
				.person(person)
				.number(NUMBER)
				.zipCode(ZIP_CODE)
				.city(CITY)
				.streetAddress(STREET_ADDRESS)
				.build();
		addresses = List.of(address);
	}
}