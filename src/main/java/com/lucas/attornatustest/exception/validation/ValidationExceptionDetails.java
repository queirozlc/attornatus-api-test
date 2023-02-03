package com.lucas.attornatustest.exception.validation;

import com.lucas.attornatustest.exception.ExceptionDetails;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
	private String fields;
}
