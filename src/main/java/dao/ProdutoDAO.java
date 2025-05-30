package dao;

import database.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {

    public String inserir(Product produto) {
        String selectSql = "SELECT quantidade FROM produtos WHERE nome = ?";
        String updateSql = "UPDATE produtos SET quantidade = quantidade + ? WHERE nome = ?";
        String insertSql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {

            // ver se o produto já existe:
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                selectStmt.setString(1, produto.getNome());
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    // se o produto existir: atualiza quantidade:
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, produto.getQuantidade());
                        updateStmt.setString(2, produto.getNome());
                        updateStmt.executeUpdate();
                        return "Produto atualizado com sucesso";
                    }
                } else {
                    // se não existir: faz insert:
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setString(1, produto.getNome());
                        insertStmt.setDouble(2, produto.getPreco());
                        insertStmt.setInt(3, produto.getQuantidade());
                        insertStmt.executeUpdate();
                        return "Produto inserido";
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectSql;
    }

    //public String remove(String nome, int quantidadeParaRemover) {
    // }

    public ObservableList<Product> listar() {
        ObservableList<Product> produtos = FXCollections.observableArrayList();
        String sql = "SELECT ID, nome, preco, quantidade FROM produtos";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product produto = new Product(rs.getString("nome"), rs.getDouble("preco"), rs.getInt("quantidade"));
                produto.setID(rs.getInt("ID"));
                produtos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

}
