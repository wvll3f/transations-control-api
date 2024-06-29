package com.wdev.secutity.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wdev.secutity.entities.Categoria;
import com.wdev.secutity.entities.MetodosPagamento;
import com.wdev.secutity.enums.TipoTransacao;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class CreateTransDTO {

    private Long id;
    private String description;
    private BigDecimal price;
    private String category;
    private TipoTransacao type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String metodoPagamento;
    private UUID user;

    public CreateTransDTO() {

    }

    public CreateTransDTO(Long id,
                          String description,
                          BigDecimal price,
                          String category,
                          TipoTransacao type,
                          LocalDate date,
                          UUID user,
                          String metodoPagamento) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.category = category;
        this.type = type;
        this.date = date;
        this.metodoPagamento = metodoPagamento;
        this.user = user;
    }



    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TipoTransacao getType() {
        return type;
    }

    public void setType(TipoTransacao type) {
        this.type = type;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getMetodoPagamento() {
        return metodoPagamento;
    }
    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
