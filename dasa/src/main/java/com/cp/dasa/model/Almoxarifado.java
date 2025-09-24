package com.cp.dasa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Almoxarifado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Almoxarifado;

    private Long FK_item;
    private int qntdItem;
    private Long FK_administrador;

    public Almoxarifado(Long FK_item, int qntdItem, Long FK_administrador) {
        this.FK_item = FK_item;
        this.qntdItem = qntdItem;
        this.FK_administrador = FK_administrador;
    }
}
