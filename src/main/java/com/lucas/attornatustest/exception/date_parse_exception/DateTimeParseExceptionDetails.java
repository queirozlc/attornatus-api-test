package com.lucas.attornatustest.exception.date_parse_exception;

import com.lucas.attornatustest.exception.ExceptionDetails;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class DateTimeParseExceptionDetails extends ExceptionDetails {
	private int errorIndex;
	private CharSequence parsedData;
	private String devMessage;
}
