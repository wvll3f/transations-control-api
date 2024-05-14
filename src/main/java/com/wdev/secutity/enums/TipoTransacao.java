package com.wdev.secutity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoTransacao {
    ENTRADA("E", "Entrada"),
    SAIDA("S", "Saida");

    private String codigo;
    private String descricao;

    private TipoTransacao(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static TipoTransacao gerarValor(String codigo) {

        if (codigo.equals("E")) {
            return ENTRADA;
        } else if (codigo.equals("S")) {
            return SAIDA;
        } else {
            return null;
        }
    }

}
