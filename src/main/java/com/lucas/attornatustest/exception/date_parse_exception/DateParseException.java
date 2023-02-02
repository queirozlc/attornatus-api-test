package com.lucas.attornatustest.exception.date_parse_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.format.DateTimeParseException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateParseException extends DateTimeParseException {
	public DateParseException(String message, CharSequence parsedData, int errorIndex) {
		super(message, parsedData, errorIndex);
	}
}
