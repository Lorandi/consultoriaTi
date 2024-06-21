package com.consultoriaTi.client.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    ERROR_GENERIC_EXCEPTION("error.generic.exception"),
    ERROR_DATE_FORMAT("error.date.format"),
    ERROR_PROFESSIONAL_NOT_FOUND("error.professional.not.found"),
    ERROR_ADDRESS_NOT_FOUND("error.address.not.found"),
    ERROR_CLIENT_NOT_FOUND("error.client.not.found"),
    ERROR_PROFESSIONAL_ALREADY_ALLOCATED_ON_THIS_CLIENT("error.professional.already.allocated.on.this.client"),
    ERROR_ALLOCATION_END_DATE_BEFORE_ALLOCATION_START_DATE("error.allocation.end.date.before.allocation.start.date"),
    ERROR_ALLOCATION_DATE_END_IS_TODAY("error.allocation.date.end.is.today"),
    ERROR_ALLOCATION_NOT_FOUND("error.allocation.not.found"),
    ERROR_ALLOCATION_NOT_FOUND_FOR_THIS_PROFESSIONAL("error.allocation.not.found.for.this.professional"),;

    private final String messageKey;
}
