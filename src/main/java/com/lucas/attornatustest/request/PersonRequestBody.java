package com.lucas.attornatustest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonRequestBody {
	@NotBlank(message = "O campo de nome não pode ser vazio.")
	private String name;
	@NotBlank(message = "O campo data de nascimento não pode ser vazio.")
	private String birthDate;
	private UUID mainAddressId;
}
