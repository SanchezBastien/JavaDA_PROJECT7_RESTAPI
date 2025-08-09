package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")        // <- PK réelle en BDD
    private Integer id;

    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Bid Quantity is mandatory")
    @Column(name = "bidQuantity")
    private Double bidQuantity;

    // --- getters & setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getBidQuantity() { return bidQuantity; }
    public void setBidQuantity(Double bidQuantity) { this.bidQuantity = bidQuantity; }
}
