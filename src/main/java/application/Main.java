package application;

import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        tabelaProdutos = criarTabela();
        mensagemLabel = new Label();
        Button btnAdicionarProduto = new Button("Adicionar Produto");
        Button btnEntrada = new Button("Entrada");
        Button btnSaida = new Button("Saída");

        btnAdicionarProduto.setOnAction(e -> adicionarProduto());
        btnEntrada.setOnAction(e -> DialogoEntrada());
        btnSaida.setOnAction(e -> DialogoSaida());

        HBox botoes = new HBox(10, btnAdicionarProduto, btnEntrada, btnSaida);
        botoes.setPadding(new Insets(10));

        VBox root = new VBox(10, botoes, tabelaProdutos, mensagemLabel);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 600, 450); // Aumentei um pouco a altura
        primaryStage.setTitle("Gerenciador de Estoque");
        primaryStage.setScene(scene);
        primaryStage.show();

        atualizarTabela();
    }

    private TableView<Product> criarTabela() {
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
        System.out.println("Número de produtos carregados: " + produtos.size());
        tabelaProdutos.setItems(produtos);
        tabelaProdutos.refresh();
    }

    private void adicionarProduto() {
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Adicionar Novo Produto");
        dialog.setHeaderText("Preencha os detalhes do novo produto:");

        // Layout do diálogo
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nomeTextField = new TextField();
        nomeTextField.setPromptText("Nome");
        TextField precoTextField = new TextField();
        precoTextField.setPromptText("Preço");
        TextField quantidadeTextField = new TextField();
        quantidadeTextField.setPromptText("Quantidade");

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeTextField, 1, 0);
        grid.add(new Label("Preço:"), 0, 1);
        grid.add(precoTextField, 1, 1);
        grid.add(new Label("Quantidade:"), 0, 2);
        grid.add(quantidadeTextField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // Botões do diálogo
        ButtonType btnSalvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnSalvar, btnCancelar);

        // Converter o resultado para um Produto quando o botão Salvar é clicado
        dialog.setResultConverter(button -> {
            if (button == btnSalvar) {
                try {
                    String nome = nomeTextField.getText();
                    double preco = Double.parseDouble(precoTextField.getText());
                    int quantidade = Integer.parseInt(quantidadeTextField.getText());
                    return new Product(nome, preco, quantidade);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        });

        // Processar o resultado do diálogo
        dialog.showAndWait().ifPresent(novoProduto -> {
            if (novoProduto != null) {
                String resultado = produtoDAO.inserir(novoProduto);
                mensagemLabel.setText(resultado);
                atualizarTabela();
            }
        });
    }

    private void DialogoEntrada() {
        Dialog<java.util.Map.Entry<String, Integer>> dialog = new Dialog<>();
        dialog.setTitle("Entrada de Produto");
        dialog.setHeaderText("Selecione o produto e a quantidade para adicionar:");

        VBox content = new VBox(10);
        ComboBox<String> produtoComboBox = new ComboBox<>();
        TextField quantidadeTextField = new TextField();
        quantidadeTextField.setPromptText("Quantidade para adicionar");

        ObservableList<String> nomesProdutos = FXCollections.observableArrayList();
        tabelaProdutos.getItems().stream()
                .map(Product::getNome)
                .sorted()
                .forEach(nomesProdutos::add);
        produtoComboBox.setItems(nomesProdutos);

        content.getChildren().addAll(new Label("Produto:"), produtoComboBox, new Label("Quantidade:"), quantidadeTextField);

        dialog.getDialogPane().setContent(content);

        ButtonType btnConfirmar = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnConfirmar, btnCancelar);

        dialog.setResultConverter(button -> {
            if (button == btnConfirmar) {
                String nomeSelecionado = produtoComboBox.getValue();
                String quantidadeStr = quantidadeTextField.getText();
                if (nomeSelecionado != null && !quantidadeStr.isEmpty()) {
                    try {
                        int quantidade = Integer.parseInt(quantidadeStr);
                        return new java.util.AbstractMap.SimpleEntry<>(nomeSelecionado, quantidade);
                    } catch (NumberFormatException e) {
                        mensagemLabel.setText("Quantidade inválida.");
                        return null;
                    }
                } else {
                    mensagemLabel.setText("Selecione um produto e informe a quantidade.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(resultado -> {
            if (resultado != null) {
                String nome = resultado.getKey();
                int quantidadeAdicionar = resultado.getValue();
                String resultadoOperacao = produtoDAO.adicionarQuantidade(nome, quantidadeAdicionar);
                mensagemLabel.setText("Entrada: " + resultadoOperacao);
                atualizarTabela();
            }
        });
    }

    private void DialogoSaida() {
        Dialog<java.util.Map.Entry<String, Integer>> dialog = new Dialog<>();
        dialog.setTitle("Saída de Produto");
        dialog.setHeaderText("Selecione o produto e a quantidade para remover:");

        VBox content = new VBox(10);
        ComboBox<String> produtoComboBox = new ComboBox<>();
        TextField quantidadeTextField = new TextField();
        quantidadeTextField.setPromptText("Quantidade para remover");

        // Carregar nomes dos produtos no ComboBox
        ObservableList<String> nomesProdutos = FXCollections.observableArrayList();
        tabelaProdutos.getItems().stream()
                .map(Product::getNome)
                .sorted()
                .forEach(nomesProdutos::add);
        produtoComboBox.setItems(nomesProdutos);

        content.getChildren().addAll(new Label("Produto:"), produtoComboBox, new Label("Quantidade:"), quantidadeTextField);

        dialog.getDialogPane().setContent(content);

        ButtonType btnConfirmar = new ButtonType("Remover", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnConfirmar, btnCancelar);

        dialog.setResultConverter(button -> {
            if (button == btnConfirmar) {
                String nomeSelecionado = produtoComboBox.getValue();
                String quantidadeStr = quantidadeTextField.getText();
                if (nomeSelecionado != null && !quantidadeStr.isEmpty()) {
                    try {
                        int quantidade = Integer.parseInt(quantidadeStr);
                        return new java.util.AbstractMap.SimpleEntry<>(nomeSelecionado, quantidade);
                    } catch (NumberFormatException e) {
                        mensagemLabel.setText("Quantidade inválida.");
                        return null;
                    }
                } else {
                    mensagemLabel.setText("Selecione um produto e informe a quantidade.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(resultado -> {
            if (resultado != null) {
                String nome = resultado.getKey();
                int quantidadeRemover = resultado.getValue();
                String resultadoOperacao = produtoDAO.remove(nome, quantidadeRemover);
                mensagemLabel.setText("Saída: " + resultadoOperacao);
                atualizarTabela();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}