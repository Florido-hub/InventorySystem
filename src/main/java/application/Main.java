package application;
import dao.ProdutoDAO;
import model.Product;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        Product p = new Product(100000, "caneta", 2.0, "azul", 100);
        dao.inserir(p);
    }
}