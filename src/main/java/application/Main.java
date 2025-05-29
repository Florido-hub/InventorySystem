package application;
import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Product;

public class Main extends Application {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        Product p = new Product(100000, "caneta", 2.0, "azul", 100);
        dao.inserir(p);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}