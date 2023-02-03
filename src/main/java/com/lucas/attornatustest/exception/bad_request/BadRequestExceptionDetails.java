package com.lucas.attornatustest.exception.bad_request;

import com.lucas.attornatustest.exception.ExceptionDetails;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BadRequestExceptionDetails extends ExceptionDetails {
}
