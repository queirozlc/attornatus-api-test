package com.lucas.attornatustest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String streetAddress;
	private Integer number;
	private String city;
	private String zipCode;
	@ToString.Exclude
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Person person;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Address address = (Address) o;
		return id != null && Objects.equals(id, address.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
