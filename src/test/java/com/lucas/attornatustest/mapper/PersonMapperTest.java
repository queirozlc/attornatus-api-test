package com.lucas.attornatustest.mapper;

import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.request.person.PersonPostRequestBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PersonMapperTest {

	@Test
	void createPersonEntityFromPersonDto() {
		DateTimeFormatter formatter = timeFormatter();
		LocalDate birthDate = LocalDate.parse("04/12/2003", formatter);
		PersonPostRequestBody personPostRequestBody = PersonPostRequestBody
				.builder()
				.name("Lucas")
				.birthDate(birthDate)
				.build();

		Person person = PersonMapper.INSTANCE.toPerson(personPostRequestBody);

		System.out.println(person);

		assertThat(person).isNotNull().isInstanceOf(Person.class);
		assertThat(person.getName()).isNotBlank().isNotNull().isEqualTo("Lucas");
	}

	private DateTimeFormatter timeFormatter() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
}