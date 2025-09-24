package com.cp.dasa.dao;

import com.cp.dasa.config.ConnectionFactory;
import com.cp.dasa.model.Almoxarifado;
import com.cp.dasa.model.Item;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminDAO {
    private final ConnectionFactory connectionFactory;

    public AdminDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    // READ - Exibe as informações do almoxarifados
    public List<Almoxarifado> exibirAlmoxarifados(Long ID_Almoxarifado) {
        List<Almoxarifado> almoxarifadosListados = new ArrayList<>();
        String sql = "SELECT * FROM almoxarifado WHERE ID_Almoxarifado = ?";

        try {
            Connection con = connectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, ID_Almoxarifado);
            ps.execute();
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Almoxarifado almoxarifado = new Almoxarifado();
                almoxarifado.setID_Almoxarifado(rs.getLong("ID_Almoxarifado"));
                almoxarifado.setFK_item(rs.getLong("FK_Item"));
                almoxarifado.setQntdItem(rs.getInt("qtd_Item"));
                almoxarifado.setFK_administrador(rs.getLong("FK_administrador"));
                almoxarifadosListados.add(almoxarifado);
            }

            return almoxarifadosListados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // READ - Exibe a informação do almoxarifado do qual gerencia
    public Almoxarifado exibirAlmoxarifado(Long ID_Admin) {
        Almoxarifado almoxarifadoExibido = new Almoxarifado();
        String sql = "SELECT * FROM almoxarifado WHERE Gerente = ?";

        try {
            Connection con = connectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, ID_Admin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                almoxarifadoExibido.setID_Almoxarifado(rs.getLong("ID_Almoxarifado"));
                almoxarifadoExibido.setFK_item(rs.getLong("FK_Item"));
                almoxarifadoExibido.setQntdItem(rs.getInt("qtd_Item"));
                almoxarifadoExibido.setFK_administrador(ID_Admin);
            }

            return almoxarifadoExibido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // CREATE - Adicionar novo item ao almoxarifado
    public Item inserirItem(Long ID_Item, int Qtd, Long FK_administrador) {
        Item itemInserido = new Item();
        String sql = "INSERT INTO almoxarifado (FK_Item, QTD_Item, Gerente) VALUES (?, ?, ?)";

        try {
            Connection con = connectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, ID_Item);
            ps.setInt(2, Qtd);
            ps.setLong(3, FK_administrador);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                itemInserido.setID_Item(rs.getLong("ID_Item"));
                itemInserido.setNome(rs.getString("NOME"));
                itemInserido.setDescricao(rs.getString("DESCRICAO"));
                itemInserido.setCategoria(rs.getString("CATEGORIA"));
            }

            return itemInserido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // CREATE - Adiciona novo item ao sistema
    public Item criarItem(Item item) {
        Item itemCriado = new Item();
        String sql = "INSERT INTO item (nome, descricao, categoria) VALUES (?, ?, ?)";

        try {
            Connection con = connectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, item.getNome());
            ps.setString(2, item.getDescricao());
            ps.setString(3, item.getCategoria());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                itemCriado.setID_Item(rs.getLong("ID_Item"));
                itemCriado.setNome(rs.getString("NOME"));
                itemCriado.setDescricao(rs.getString("DESCRICAO"));
                itemCriado.setCategoria(rs.getString("CATEGORIA"));
            }

            return itemCriado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE - Exclui item do almoxarifado
    public Item removerItem(Long ID_Item, Long ID_Almoxarifado) {
        Item itemRemovido = new Item();
        String sql = "DELETE FROM almoxarifado WHERE FK_Item = ? AND ID_Almoxarifado = ?";

        try {
            Connection con = connectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, ID_Item);
            ps.setLong(2, ID_Almoxarifado);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                itemRemovido.setID_Item(rs.getLong("ID_Item"));
                itemRemovido.setNome(rs.getString("NOME"));
                itemRemovido.setDescricao(rs.getString("DESCRICAO"));
                itemRemovido.setCategoria(rs.getString("CATEGORIA"));
            }

            return itemRemovido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE - Exclui item do sistema
    public Item excluirItem(Long ID_Item) {
        Item itemExcluido = new Item();
        String sql = "DELETE FROM item WHERE ID_Item = ?";

        try {
            Connection con = connectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, ID_Item);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                itemExcluido.setID_Item(rs.getLong("ID_Item"));
                itemExcluido.setNome(rs.getString("NOME"));
                itemExcluido.setDescricao(rs.getString("DESCRICAO"));
                itemExcluido.setCategoria(rs.getString("CATEGORIA"));
            }

            return itemExcluido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
