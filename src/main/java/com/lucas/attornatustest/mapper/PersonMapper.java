package com.lucas.attornatustest.mapper;

import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.request.PersonRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	@Mapping(target = "mainAddress", source = "mainAddress")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "birthDate", ignore = true)
	Person toPerson(PersonRequestBody personRequestBody);
}
