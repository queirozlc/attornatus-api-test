package com.lucas.attornatustest.mapper;

import com.lucas.attornatustest.entity.Address;
import com.lucas.attornatustest.request.AddressRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

	@Mapping(target = "person", ignore = true)
	@Mapping(target = "id", ignore = true)
	Address toAddress(AddressRequestBody addressRequestBody);

}
