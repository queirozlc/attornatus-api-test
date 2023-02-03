package com.lucas.attornatustest.util;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.request.PersonRequestBody;

import static com.lucas.attornatustest.util.DateFormatter.toLocalDate;


public class TestUtils {

	public static Person createPersonWithoutMainAddressToSave() {
		return Person
				.builder()
				.name("Caleb César Rocha")
				.birthDate(toLocalDate("19/01/1958"))
				.build();
	}


	public static Person createPersonWithMainAddressToSave() {
		Address mainAddress = Address
				.builder()
				.streetAddress("Quadra Quadra 52")
				.zipCode("72917-684")
				.number(870)
				.city("Águas Lindas de Goiás")
				.build();
		Person person = Person
				.builder()
				.name("Caleb César Rocha")
				.birthDate(toLocalDate("19/01/1958"))
				.mainAddress(mainAddress)
				.build();
		person.getMainAddress().setPerson(person);
		return person;
	}

	public static Address createAddressToSave() {
		Person person = createPersonWithoutMainAddressToSave();
		return Address
				.builder()
				.city("Campina Grande")
				.zipCode("58429-326")
				.number(900)
				.streetAddress("Rua Professor Olavo Cruz")
				.person(person)
				.build();
	}

	public static PersonRequestBody createDto() {
		return PersonRequestBody
				.builder()
				.name("Lucas")
				.birthDate("04/12/2003")
				.build();
	}

	public static PersonRequestBody toDto(Person person) {
		return PersonRequestBody
				.builder()
				.name(person.getName())
				.birthDate("19/01/1958")
				.build();
	}
}
