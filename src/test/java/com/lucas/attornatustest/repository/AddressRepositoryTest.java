package com.lucas.attornatustest.repository;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static com.lucas.attornatustest.util.TestUtils.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AddressRepositoryTest {

	@Autowired
	AddressRepository repository;
	@Autowired
	PersonRepository personRepository;

	@Test
	void shouldFindAllAddressByPerson() {
		Person personToSave = createPersonWithMainAddressToSave();
		Person personSaved = personRepository.save(personToSave);
		personSaved.getMainAddress().getPerson().setId(personSaved.getId());
		repository.save(personSaved.getMainAddress());
		List<Address> addressList = repository.findAllByPerson(personSaved);
		assertThat(addressList).isNotNull().isNotEmpty();
		assertThat(addressList.size()).isPositive().isEqualTo(1);
	}

	@Test
	void shouldCreateAddress() {
		Address addressToSave = createAddressToSave();
		Address addressSaved = repository.save(addressToSave);
		Person addressPersonSaved = personRepository.save(addressSaved.getPerson());
		addressSaved.setPerson(addressPersonSaved);
		assertThat(addressSaved).isNotNull().isInstanceOf(Address.class);
		assertThat(addressSaved.getPerson()).isNotNull();
		assertThat(addressSaved.getPerson().getId()).isNotNull().isInstanceOf(UUID.class);
	}
}