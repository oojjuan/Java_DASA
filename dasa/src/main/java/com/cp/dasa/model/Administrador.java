package com.cp.dasa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Administrador;
    private String nome;
    private Long ID_Almoxarifado;

    public Administrador(String nome, Long ID_Almoxarifado) {
        this.nome = nome;
        this.ID_Almoxarifado = ID_Almoxarifado;
    }
}
