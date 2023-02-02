package com.lucas.attornatustest.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
public abstract class ExceptionDetails {
	protected String title;
	protected int status;
	protected String details;
	protected String message;
	protected LocalDateTime timestamp;
}
