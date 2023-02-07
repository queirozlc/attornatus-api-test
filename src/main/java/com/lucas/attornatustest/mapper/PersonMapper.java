package com.lucas.attornatustest.mapper;

import com.lucas.attornatustest.entity.Person;
import com.lucas.attornatustest.request.PersonRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

	@Mapping(target = "mainAddress", source = "mainAddress")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "birthDate", dateFormat = "dd/MM/yyyy")
	Person toPerson(PersonRequestBody personRequestBody);
}
