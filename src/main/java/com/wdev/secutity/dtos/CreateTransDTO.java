package com.wdev.secutity.dtos;

import com.wdev.secutity.enums.TipoTransacao;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateTransDTO {

    private Long id;
    private String description;
    private BigDecimal price;
    private String category;
    private TipoTransacao type;
    private UUID user;

    public CreateTransDTO(){

    }

    public CreateTransDTO(Long id, String description, BigDecimal price, String category, TipoTransacao type, UUID user) {
        this.id=id;
        this.description = description;
        this.price = price;
        this.category = category;
        this.type = type;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
