package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TradeId")
    private Integer id;

    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Buy Quantity is mandatory")
    @Positive(message = "Buy Quantity must be positive")
    @Column(name = "buyQuantity")
    private Double buyQuantity;

    // ==== Getters & Setters ====
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }
    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
}
