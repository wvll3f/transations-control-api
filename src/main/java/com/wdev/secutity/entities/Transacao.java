package com.wdev.secutity.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wdev.secutity.dtos.BalanceDTO;
import com.wdev.secutity.dtos.CreateTransDTO;
import com.wdev.secutity.dtos.ResponseTransDTO;
import com.wdev.secutity.enums.TipoTransacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DynamicUpdate
@Table(name = "tb_transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categoria category;

    @ManyToOne
    @JoinColumn(name = "metodosPagamento_id")
    private MetodosPagamento metodoPagamento;

    private TipoTransacao type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "trans_date")
    private LocalDate date;

    @CreationTimestamp
    private Instant createTimeStamp;

    public Transacao() {
    }

    public Transacao(Long id, String description, User user, BigDecimal price, Categoria category, TipoTransacao type, Instant createTimeStamp, MetodosPagamento metodoPagamento, LocalDate date) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.price = price;
        this.category = category;
        this.type = type;
        this.createTimeStamp = createTimeStamp;
        this.metodoPagamento = metodoPagamento;
        this.date = date;
    }

    public Instant getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Instant createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

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

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public TipoTransacao getType() {
        return type;
    }

    public void setType(TipoTransacao type) {
        this.type = type;
    }

    public MetodosPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodosPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public CreateTransDTO modelToDTO(Transacao transacao) {
        var dto = new CreateTransDTO();
        var dateFormate = new SimpleDateFormat("yyyy-MM-dd");
        dto.setId(transacao.getId());
        dto.setDescription(transacao.getDescription());
        dto.setPrice(transacao.getPrice());
        dto.setCategory(transacao.getCategory().getName());
        dto.setMetodoPagamento(transacao.getMetodoPagamento().getName());
        dto.setType(transacao.getType());
        dto.setDate(transacao.getDate());
        dto.setUser(transacao.getUser().getId());

        return dto;
    }

    public ResponseTransDTO modelToResponseDTO(Transacao transacao) {
        var dto = new ResponseTransDTO();

        dto.setId(transacao.getId());
        dto.setDescription(transacao.getDescription());
        dto.setPrice(transacao.getPrice());
        dto.setCategory(transacao.getCategory());
        dto.setMetodoPagamento(transacao.getMetodoPagamento());
        dto.setType(transacao.getType());
        dto.setCreateTimeStamp(transacao.getCreateTimeStamp().toString().substring(0,10));
        dto.setUser(transacao.getUser().getId());

        return dto;
    }

    public BalanceDTO toBalanceDTO(Transacao transacao) {
        var dto = new BalanceDTO();

        dto.setBalance(transacao.getPrice());
        dto.setType(transacao.getType());

        return dto;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
