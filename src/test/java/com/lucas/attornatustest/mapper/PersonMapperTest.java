package com.lucas.attornatustest.mapper;

import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.request.PersonRequestBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PersonMapperTest {

	@Test
	void createPersonEntityFromPersonDto() {
		PersonRequestBody personRequestBody = PersonRequestBody
				.builder()
				.name("Lucas")
				.build();

		Person person = PersonMapper.INSTANCE.toPerson(personRequestBody);

		System.out.println(person);

		assertThat(person).isNotNull().isInstanceOf(Person.class);
		assertThat(person.getName()).isNotBlank().isNotNull().isEqualTo("Lucas");
	}
}