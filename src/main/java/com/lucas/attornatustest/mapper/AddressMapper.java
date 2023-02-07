package com.lucas.attornatustest.mapper;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.request.AddressRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	@Mapping(target = "person", ignore = true)
	@Mapping(target = "person.id", source = "personId")
	@Mapping(target = "id", ignore = true)
	Address toAddress(AddressRequestBody addressRequestBody);

}
