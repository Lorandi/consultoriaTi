package com.consultoriaTi.gestao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    ERROR_GENERIC_EXCEPTION("error.generic.exception"),
    ERROR_CPF_ALREADY_USED("error.cpf.already.used"),
    ERROR_ELECTOR_NOT_FOUND("error.elector.not.found"),
    ERROR_INVALID_CPF("error.invalid.cpf"),
    ERROR_DATE_FORMAT("error.date.format"),
    ERROR_SURVEY_NOT_FOUND("error.survey.not.found"),
    ERROR_VOTE_NOT_FOUND("error.vote.not.found"),
    ERROR_ELECTOR_UNABLE_TO_VOTE("error.elector.unable.to.vote"),
    ERROR_ELECTOR_ALREADY_VOTED_FOR_THIS_SURVEY("error.elector.already.voted.for.this.survey"),
    ERROR_THIS_SURVEY_IS_EXPIRED("error.this.survey.is.expired"),
    ERROR_PROFESSIONAL_NOT_FOUND("error.professional.not.found"),
    ERROR_ADDRESS_NOT_FOUND("error.address.not.found");

    private final String messageKey;
}
