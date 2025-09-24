package com.cp.dasa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Almoxarife {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Almoxarife;
    private String nome;
    private Almoxarifado almoxarifado;

    public Almoxarife(String nome, Almoxarifado almoxarifado) {
        this.nome = nome;
        this.almoxarifado = almoxarifado;
    }
}
