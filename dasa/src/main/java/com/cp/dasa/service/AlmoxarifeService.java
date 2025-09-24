package com.cp.dasa.service;

import com.cp.dasa.dao.AlmoxarifeDAO;
import com.cp.dasa.model.Almoxarifado;
import com.cp.dasa.model.Almoxarife;
import com.cp.dasa.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlmoxarifeService {
    @Autowired
    AlmoxarifeDAO almoxarifeDAO;

    /// Métodos
    public List<Item> exibirAlmoxarifado(Almoxarife almoxarife, Long ID_Almoxarifado) {
        verificarAlmoxarifado(almoxarife.getID_Almoxarife(), ID_Almoxarifado);
        return almoxarifeDAO.exibirAlmoxarifado(almoxarife);
    }


    public Almoxarifado alterarQtdItensEstoque(Long ID_Almoxarife , Long ID_Almoxarifado, Long ID_Item, int Qtd) {
        verificarAlmoxarifado(ID_Almoxarife, ID_Almoxarifado);
        verificarQtd(Qtd);
        return almoxarifeDAO.alterarQtdItensEstoque(ID_Almoxarifado, ID_Item, Qtd);
    }

    /// Serviços
    public void verificarAlmoxarifado(Long ID_Almoxarife, Long ID_Almoxarifado) {
        if (ID_Almoxarifado == null || ID_Almoxarife == null) {
            throw new IllegalArgumentException("IDs inválidos");
        }
        if (ID_Almoxarife.compareTo(ID_Almoxarifado) == 0) {
            throw new IllegalArgumentException("O ID do almoxarife não corresponde ao ID do responsável por cuidar do almoxarifado especificado");
        }
    }

    public void verificarQtd(int Qtd) {
        // Limita remover insumos até 100 / Limita adicionar insumos até 500
        if (Qtd < -100 || Qtd > 500) {
            throw new IllegalArgumentException("Quantidade acima do limite!");
        }
        if (Qtd == 0) {
            throw new IllegalArgumentException("Quantidade Inválida");
        }
    }


}
