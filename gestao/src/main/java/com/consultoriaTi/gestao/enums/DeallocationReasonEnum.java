package com.consultoriaTi.gestao.enums;

import com.consultoriaTi.gestao.enums.serializer.EnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonSerialize(using = EnumSerializer.class)
public enum DeallocationReasonEnum implements EnumDescription {
    END_OF_ALLOCATION_PERIOD("Fim do período de alocação"),
    NEW_PROPOSAL_RECEIVED("Recebeu uma nova proposta"),
    BELOW_EXPECTATION_SALARY("Salário abaixo do esperado"),
    EXCESS_WORKLOAD("Carga de trabalho excessiva"),
    PROJECT_CONCLUDED("Projeto concluído"),
    UNDERPERFORMANCE("Desempenho abaixo do esperado"),
    STAFFING_RESTRUCTURE("Reestruturação de equipe"),
    OTHER("Outro");

    private final String description;
}
