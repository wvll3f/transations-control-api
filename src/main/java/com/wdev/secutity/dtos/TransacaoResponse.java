package com.wdev.secutity.dtos;

import com.wdev.secutity.enums.TipoTransacao;

import java.math.BigDecimal;
import java.util.UUID;

public record TransacaoResponse(String description,
                                BigDecimal price,
                                String category,
                                TipoTransacao type,
                                UUID user) {
}