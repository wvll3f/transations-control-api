package com.wdev.secutity.dtos;

import com.wdev.secutity.enums.TipoTransacao;

import java.math.BigDecimal;

public class BalanceDTO {

    private BigDecimal balance;
    private TipoTransacao type;

    public BalanceDTO() {}

    public BalanceDTO(BigDecimal balance, TipoTransacao type) {
        this.balance = balance;
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public TipoTransacao getType() {
        return type;
    }
    public void setType(TipoTransacao type) {
        this.type = type;
    }
}
