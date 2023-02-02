package com.lucas.attornatustest.mapper;

import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.request.person.PersonPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	@Mapping(target = "mainAddress", ignore = true)
	@Mapping(target = "id", ignore = true)
	Person toPerson(PersonPostRequestBody personPostRequestBody);
}
