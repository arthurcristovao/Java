
package pacote;

import java.sql.*;


public class ConexaoMySQL {
    
    public static java.sql.Connection getConexaoMySQL(){
        Connection conn = null;
        
        String serverName = "localhost";
        String mydatabase = "aula14";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";
        String password = "";
        
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }    
}
