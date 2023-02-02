package com.lucas.attornatustest.controller;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.request.AddressRequestBody;
import com.lucas.attornatustest.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService service;

	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody @Valid AddressRequestBody requestBody) {
		return new ResponseEntity<>(service.createAddress(requestBody), CREATED);
	}
}
