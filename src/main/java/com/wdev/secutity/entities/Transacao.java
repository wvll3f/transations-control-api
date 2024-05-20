package com.wdev.secutity.entities;

import com.wdev.secutity.dtos.BalanceDTO;
import com.wdev.secutity.dtos.CreateTransDTO;
import com.wdev.secutity.enums.TipoTransacao;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@DynamicUpdate
@Table(name = "tb_transacoes")
public class Transacao {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal price;


    private String category;

    private TipoTransacao type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public CreateTransDTO modelToDTO(Transacao transacao) {
        var dto = new CreateTransDTO();

        dto.setId(transacao.getId());
        dto.setDescription(transacao.getDescription());
        dto.setPrice(transacao.getPrice());
        dto.setCategory(transacao.getCategory());
        dto.setType(transacao.getType());
        dto.setUser(transacao.getUser().getId());

        return dto;
    }

    public BalanceDTO toBalanceDTO(Transacao transacao) {
        var dto = new BalanceDTO();

        dto.setBalance(transacao.getPrice());
        dto.setType(transacao.getType());

        return dto;
    }
}
