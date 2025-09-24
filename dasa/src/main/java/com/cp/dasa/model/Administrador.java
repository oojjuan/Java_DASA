package com.cp.dasa.model;

import lombok.Data;

@Data
public class Administrador {
    private Long ID_Administrador;
    private String nome;
    private Long ID_Almoxarifado;

    public Administrador(String nome, Long ID_Almoxarifado) {
        this.nome = nome;
        this.ID_Almoxarifado = ID_Almoxarifado;
    }
}
