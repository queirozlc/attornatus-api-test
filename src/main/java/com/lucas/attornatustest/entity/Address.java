package com.lucas.attornatustest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
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
	@ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
	private Person person;
}
