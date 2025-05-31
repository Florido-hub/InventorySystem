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
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // setar os parâmetros ANTES de executar
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.executeUpdate();

            return "produto inserido com sucesso";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sql;
    }

    public String adicionarQuantidade(String nome, int quantidade) {
        String sql = "UPDATE produtos SET quantidade = quantidade + ? WHERE nome = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setString(2, nome);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return "Quantidade de " + nome + " aumentada em " + quantidade;
            } else {
                return "Produto " + nome + " não encontrado para adicionar quantidade.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao adicionar quantidade.";
        }
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
