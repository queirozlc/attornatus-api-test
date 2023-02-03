package com.lucas.attornatustest.request;

import com.lucas.attornatustest.entity.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonRequestBody {
	@NotBlank(message = "O campo de nome não pode ser vazio.")
	private String name;
	@NotBlank(message = "O campo data de nascimento não pode ser vazio.")
	private String birthDate;
	private Address mainAddress;
}
