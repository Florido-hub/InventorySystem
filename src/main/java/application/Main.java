package application;

import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label mensagem = new Label();
        Button btn = new Button("Inserir Produto");

        btn.setOnAction(e -> {
            Product produto = new Product("Caderno", 12.5, 10);
            ProdutoDAO dao = new ProdutoDAO();
            String resultado = dao.inserir(produto);
            mensagem.setText(resultado);
        });

        VBox root = new VBox(10, btn, mensagem);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Gerenciador de Estoque");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
