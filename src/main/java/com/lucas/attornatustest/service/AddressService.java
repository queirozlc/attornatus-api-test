package com.lucas.attornatustest.service;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.request.AddressRequestBody;

import java.util.List;
import java.util.UUID;

public interface AddressService {

	Address createAddress(AddressRequestBody addressRequestBody);

	List<Address> listAllAddressByPerson(UUID personId);

}
