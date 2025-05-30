package application;

import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;

public class Main extends Application {

    private TableView<Product> tabelaProdutos;
    private ProdutoDAO produtoDAO;
    private Label mensagemLabel;

    @Override
    public void start(Stage primaryStage) {
        produtoDAO = new ProdutoDAO();
        tabelaProdutos = TabelaProdutos();
        mensagemLabel = new Label();
        Button btnInserir = new Button("Inserir Produto");
        btnInserir.setOnAction(e -> inserirProduto());

        VBox root = new VBox(10, btnInserir, tabelaProdutos, mensagemLabel);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Lista de Produtos");
        primaryStage.setScene(scene);
        primaryStage.show();

        atualizarTabela();
    }

    private TableView<Product> TabelaProdutos() {
        TableView<Product> table = new TableView<>();

        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Product, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Product, Double> precoColumn = new TableColumn<>("Preço");
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        TableColumn<Product, Integer> quantidadeColumn = new TableColumn<>("Quantidade");
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        table.getColumns().addAll(idColumn, nomeColumn, precoColumn, quantidadeColumn);
        return table;
    }

    private void atualizarTabela() {
        ObservableList<Product> produtos = produtoDAO.listar();
        tabelaProdutos.setItems(produtos);
        tabelaProdutos.refresh();
    }

    private void inserirProduto() {
        Product novoProduto = new Product("Lápis", 1.50, 50);
        String resultado = produtoDAO.inserir(novoProduto);
        mensagemLabel.setText(resultado);
        atualizarTabela();
    }

    public static void main(String[] args) {
        launch(args);
    }
}