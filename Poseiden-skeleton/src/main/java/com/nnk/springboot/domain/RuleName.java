package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Json is mandatory")
    private String json;

    @NotBlank(message = "Template is mandatory")
    private String template;

    // colonne en base = sqlStr
    @Column(name = "sqlStr")
    @NotBlank(message = "SQL is mandatory")
    private String sql;

    // colonne en base = sqlPart
    @Column(name = "sqlPart")
    @NotBlank(message = "SQL Part is mandatory")
    private String sqlPart;

    // ===== Getters & Setters =====

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }
    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }
    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSql() {
        return sql;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlPart() {
        return sqlPart;
    }
    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
