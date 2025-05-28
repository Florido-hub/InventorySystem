package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    private static final String URL = "jdbc:mysql://root:RvDcMKHxBeRcfEOVDYxdemFAOFlurXGj@mainline.proxy.rlwy.net:42819/railway";
    private static final String USUARIO = "root";
    private static final String SENHA = "RvDcMKHxBeRcfEOVDYxdemFAOFlurXGj";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
