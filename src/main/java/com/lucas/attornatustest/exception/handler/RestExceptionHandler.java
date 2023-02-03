package com.lucas.attornatustest.exception.handler;

import com.lucas.attornatustest.exception.bad_request.BadRequestException;
import com.lucas.attornatustest.exception.bad_request.BadRequestExceptionDetails;
import com.lucas.attornatustest.exception.date_parse_exception.DateParseException;
import com.lucas.attornatustest.exception.date_parse_exception.DateTimeParseExceptionDetails;
import com.lucas.attornatustest.exception.validation.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDetails> handleBadRequest(BadRequestException e) {
		BadRequestExceptionDetails badRequestExceptionDetails = BadRequestExceptionDetails
				.builder()
				.title("Bad Request. Check api docs")
				.status(BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.message(e.getMessage())
				.details(e.getClass().getName())
				.build();

		return new ResponseEntity<>(badRequestExceptionDetails, BAD_REQUEST);
	}

	@ExceptionHandler(DateParseException.class)
	public ResponseEntity<DateTimeParseExceptionDetails> handleParseException(DateParseException e) {
		DateTimeParseExceptionDetails dateTimeParseExceptionDetails = DateTimeParseExceptionDetails
				.builder()
				.title("Date parse Exception. Check api docs")
				.status(BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.message(e.getMessage())
				.details(e.getParsedString())
				.devMessage("O formato de data deve ser dd/MM/yyyy.")
				.errorIndex(e.getErrorIndex())
				.parsedData(e.getParsedString())
				.build();
		return new ResponseEntity<>(dateTimeParseExceptionDetails, BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
	                                                              HttpHeaders headers,
	                                                              HttpStatusCode status,
	                                                              WebRequest request) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream()
				.map(FieldError::getField)
				.collect(Collectors.joining(", "));
		String message = fieldErrors
				.stream()
				.map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		ValidationExceptionDetails validationExceptionDetails = ValidationExceptionDetails
				.builder()
				.title("Invalid Field(s). Check api docs")
				.status(status.value())
				.timestamp(LocalDateTime.now())
				.message(message)
				.details(e.getClass().getName())
				.fields(fields)
				.build();
		return new ResponseEntity<>(validationExceptionDetails, BAD_REQUEST);
	}
}
