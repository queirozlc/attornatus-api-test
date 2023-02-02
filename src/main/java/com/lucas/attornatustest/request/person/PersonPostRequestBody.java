package com.lucas.attornatustest.request.person;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonPostRequestBody {
	@NotBlank(message = "O campo de nome não pode ser vazio.")
	private String name;
	@NotBlank(message = "O campo data de nascimento não pode ser vazio.")
	private LocalDate birthDate;
	private UUID mainAddressId;
}
