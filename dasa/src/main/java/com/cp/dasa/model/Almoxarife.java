package com.cp.dasa.model;

import lombok.Data;

@Data
public class Almoxarife {

    private Long ID_Almoxarife;
    private String nome;
    private Almoxarifado almoxarifado;

    public Almoxarife(String nome, Almoxarifado almoxarifado) {
        this.nome = nome;
        this.almoxarifado = almoxarifado;
    }
}
