package com.lucas.attornatustest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private LocalDate birthDate;
	@OneToOne(fetch = FetchType.EAGER)
	private Address mainAddress;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Person person = (Person) o;
		return id != null && Objects.equals(id, person.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
