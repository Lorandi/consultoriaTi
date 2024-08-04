package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.RoleEnum;
import lombok.With;

@With
public record RegisterDTO(String login,
                          String password,
                          RoleEnum role) {
}



