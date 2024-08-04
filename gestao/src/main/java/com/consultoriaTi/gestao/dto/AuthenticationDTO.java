package com.consultoriaTi.gestao.dto;

import lombok.With;

@With
public record AuthenticationDTO(String login,
                                String password) {
}



