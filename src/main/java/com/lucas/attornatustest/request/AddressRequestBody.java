package com.lucas.attornatustest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequestBody {
	@NotBlank(message = "O campo 'endereço' não pode ser vazio.")
	private String streetAddress;
	@NotNull(message = "O campo 'número' não pode ser vazio.")
	private Integer number;
	@NotBlank(message = "O campo 'cidade' não pode ser vazio.")
	private String city;
	@NotBlank(message = "O campo 'cep' não pode ser vazio.")
	private String zipCode;
	@NotNull(message = "O id da pessoa (dona do endereço) é obrigatório.")
	private UUID personId;
}
