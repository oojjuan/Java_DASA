package com.cp.dasa.dao;

import com.cp.dasa.config.ConnectionFactory;
import com.cp.dasa.model.Almoxarifado;
import com.cp.dasa.model.Almoxarife;
import com.cp.dasa.model.Item;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlmoxarifeDAO {
    private final ConnectionFactory connectionFactory;

    public AlmoxarifeDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    // READ - Exibir todos os itens do almoxarifado do almoxarife
    public List<Item> exibirAlmoxarifado(Almoxarife almoxarife) {
        List<Item> itens = new ArrayList<>();
        String sql = "SELECT a.fk_item, i.nome, i.categoria, i.descricao, a.qtd_item" +
                "FROM almoxarifado a" +
                "JOIN item i" +
                "ON a.fk_item = i.id_item" +
                "WHERE a.id_almoxarifado = ?";

        try {
            Connection con = connectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, almoxarife.getID_Almoxarifado());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setID_Item(rs.getLong(1));
                item.setNome(rs.getString(2));
                item.setCategoria(rs.getString(3));
                item.setDescricao(rs.getString(4));
                itens.add(item);
            }

            return itens;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE - Retira uma qtd específica de um item do almoxarifado
    public Almoxarifado alterarQtdItensEstoque(Long ID_Almoxarifado, Long ID_itemEspecifico, int Qtd ) {
        Almoxarifado almoxarifadoAlterado = new Almoxarifado();

        String sqlUpdate = "UPDATE almoxarifado SET qtd_Item = qtd_Item + ? WHERE ID_Almoxarifado = ? AND FK_ITEM = ?";
        String sqlSelect = "SELECT * FROM almoxarifado WHERE ID_Almoxarifado = ? AND FK_ITEM = ?";


        try (Connection con = connectionFactory.getConnection()) {
            /// 1° query: atualiza o almoxarifado
            try {
                PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
                psUpdate.setInt(1, Qtd);
                psUpdate.setLong(2, ID_Almoxarifado);
                psUpdate.setLong(3, ID_itemEspecifico);
                psUpdate.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            /// 2° query: salva o almoxarifado alterado
            try {
                PreparedStatement psSelect = con.prepareStatement(sqlSelect);
                psSelect.setLong(1, ID_Almoxarifado);
                psSelect.setLong(2, ID_itemEspecifico);
                ResultSet rs = psSelect.executeQuery();

                if (rs.next()) {
                    almoxarifadoAlterado.setID_Almoxarifado(rs.getLong("ID_Almoxarifado"));
                    almoxarifadoAlterado.setFK_item(rs.getLong("FK_ITEM"));
                    almoxarifadoAlterado.setQntdItem(rs.getInt("qtd_Item"));
                    almoxarifadoAlterado.setFK_administrador(rs.getLong("FK_ADMINISTRADOR"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return almoxarifadoAlterado;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
