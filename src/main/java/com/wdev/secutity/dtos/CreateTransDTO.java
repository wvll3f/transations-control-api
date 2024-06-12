package com.wdev.secutity.dtos;
import com.wdev.secutity.entities.Categoria;
import com.wdev.secutity.entities.MetodosPagamento;
import com.wdev.secutity.enums.TipoTransacao;
import java.math.BigDecimal;
import java.util.UUID;

public class CreateTransDTO {

    private Long id;
    private String description;
    private BigDecimal price;
    private Categoria category;
    private TipoTransacao type;
    private String createTimeStamp;
    private MetodosPagamento metodoPagamento;
    private UUID user;

    public CreateTransDTO(){

    }

    public CreateTransDTO(Long id, String description, BigDecimal price, Categoria category, TipoTransacao type,String createTimeStamp, UUID user, MetodosPagamento metodoPagamento) {
        this.id=id;
        this.description = description;
        this.price = price;
        this.category = category;
        this.type = type;
        this.createTimeStamp = createTimeStamp;
        this.metodoPagamento = metodoPagamento;
        this.user = user;
    }

    public String getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(String createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
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

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }
}
