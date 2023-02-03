package com.lucas.attornatustest.util;

import com.lucas.attornatustest.exception.date_parse_exception.DateParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateFormatter {

	public static LocalDate toLocalDate(String date) {
		try {
			return DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(date, LocalDate::from);
		}catch (DateTimeParseException e) {
			throw new DateParseException(e.getMessage(), e.getParsedString(), e.getErrorIndex());
		}
	}
}
