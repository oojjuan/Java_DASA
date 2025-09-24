package com.cp.dasa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Almoxarifado {
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
