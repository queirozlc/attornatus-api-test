package com.lucas.attornatustest.repository;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static com.lucas.attornatustest.util.TestUtils.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest {

	@Autowired
	PersonRepository repository;
	@Autowired
	AddressRepository addressRepository;

	@Test
	void shouldSaveAPersonWithoutMainAddress() {
		Person personWithoutMainAddressToSave = createPersonWithoutMainAddressToSave();
		Person personSaved = repository.save(personWithoutMainAddressToSave);
		assertThat(personSaved).isNotNull().isInstanceOf(Person.class);
		assertThat(personSaved.getId()).isNotNull();
		assertThat(personSaved.getName())
				.isNotNull().isNotBlank().isEqualTo("Caleb César Rocha");
	}

	@Test
	void shouldSavePersonWithMainAddress() {
		Person personWithMainAddressToSave = createPersonWithMainAddressToSave();
		Person personSaved = repository.save(personWithMainAddressToSave);
		personSaved.getMainAddress().getPerson().setId(personSaved.getId());
		Address mainAddressSaved = addressRepository.save(personSaved.getMainAddress());
		assertThat(personSaved).isNotNull().isInstanceOf(Person.class);
		assertThat(personSaved.getId()).isNotNull();
		assertThat(mainAddressSaved).isNotNull();
		assertThat(mainAddressSaved.getId()).isNotNull();
		assertThat(mainAddressSaved.getPerson().getId()).isNotNull()
				.isEqualTo(personSaved.getId());
	}

	@Test
	void shouldFindOnePersonById()  {
		Person personWithoutMainAddressToSave = createPersonWithoutMainAddressToSave();
		Person personSaved = repository.save(personWithoutMainAddressToSave);
		Optional<Person> personOptional = repository.findById(personSaved.getId());
		assertThat(personOptional).isPresent();
		assertThat(personOptional.get().getId()).isNotNull().isEqualTo(personSaved.getId());
	}

	@Test
	void shouldThrowRuntimeExceptionWhenTryFindPersonByUnknownId() {
		Person personWithoutMainAddressToSave = createPersonWithoutMainAddressToSave();
		repository.save(personWithoutMainAddressToSave);
		assertThatRuntimeException()
				.isThrownBy(() -> repository.findById(UUID.randomUUID())
				.orElseThrow(RuntimeException::new));
	}

	@Test
	void shouldReturnAllPersonsSaved() {
		Person personToSave = createPersonWithoutMainAddressToSave();
		Person personWithoutMainAddressToSave = createPersonWithoutMainAddressToSave();
		repository.saveAll(List
				.of(personToSave, personWithoutMainAddressToSave));
		List<Person> personList = repository.findAll();
		assertThat(personList).isNotEmpty().isNotNull();
	}
}