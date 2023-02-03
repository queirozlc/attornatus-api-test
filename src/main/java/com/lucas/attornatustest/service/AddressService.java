package com.lucas.attornatustest.service;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.request.AddressRequestBody;

public interface AddressService {

	Address createAddress(AddressRequestBody addressRequestBody);



}
