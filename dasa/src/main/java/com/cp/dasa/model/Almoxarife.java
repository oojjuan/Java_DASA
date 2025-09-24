package com.cp.dasa.model;

import lombok.Data;

@Data
public class Almoxarife {

    private Long ID_Almoxarife;
    private String nome;
    private Long ID_Almoxarifado;

    public Almoxarife(String nome, Long ID_Almoxarifado) {
        this.nome = nome;
        this.ID_Almoxarifado = ID_Almoxarifado;
    }
}
