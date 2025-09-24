package com.cp.dasa.service;

import com.cp.dasa.config.ConnectionFactory;
import com.cp.dasa.dao.AdminDAO;
import com.cp.dasa.model.Almoxarifado;
import com.cp.dasa.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    AdminDAO adminDAO;

    /// Métodos
    public List<Almoxarifado> exibirAlmoxarifados(Long ID_Almoxarifado) {
        return adminDAO.exibirAlmoxarifados(ID_Almoxarifado);
    }

    public Almoxarifado exibirAlmoxarifado(Long ID_Admin) {
        return adminDAO.exibirAlmoxarifado(ID_Admin);
    }

    public Item inserirItem(Long ID_Item, int Qtd, Long ID_Almoxarifado) {
        Long ID_Admin = buscarAdminPorAlmoxarifado(ID_Almoxarifado);
        verificarQtd(Qtd);
        return adminDAO.inserirItem(ID_Item, Qtd, ID_Admin);
    }

    public Item criarItem(Item item) {
        verificarItem(item);
        return adminDAO.criarItem(item);
    }

    public Item removerItem(Long ID_Item, Long ID_Almoxarifado) {
        Long ID_Admin = buscarAdminPorAlmoxarifado(ID_Almoxarifado);
        return adminDAO.removerItem(ID_Item, ID_Admin);
    }

    public Item excluirItem(Item item) {
        return adminDAO.excluirItem(item.getID_Item());
    }

    /// Serviços
    public Long buscarAdminPorAlmoxarifado(Long ID_Almoxarifado) {
        try {
            String sql = "SELECT fk_admin FROM almoxarifado WHERE id_almoxarifado = ?";

            Connection con = connectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, ID_Almoxarifado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("id_almoxarifado");
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
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

    public void verificarItem (Item item) {
        if (item.getNome().length() > 100 || item.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        if (item.getDescricao().length() > 255 || item.getDescricao().length() < 10 || item.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição inválida");
        }
        if (item.getCategoria().length() > 50 || item.getCategoria().isBlank()) {
            throw new IllegalArgumentException("Categoria inválida");
        }
    }
}
