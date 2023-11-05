package pacote;

import java.sql.*;

public class Projeto {
    public static void main(String[] args) {
        Connection c = null;
        Statement stat = null;
        ResultSet rs = null;
        ResultSet rsTitles = null;

        
        Projeto projeto = new Projeto();
        
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost/aula12?user=root");
            stat = c.createStatement();
            //rs = stat.executeQuery("SELECT * FROM authors");
            rsTitles = stat.executeQuery("SELECT * FROM titles");
            
            
            for(int i = 1; i <= 10; i++){
                //projeto.insertTitle(stat, "Titulo" + i, "000" + i, "copy" + i);
            }
            
            projeto.getTitles(rsTitles);
            //projeto.getAuthors(rs);
          
            // Criar uma instância da classe Projeto
            //projeto.insertAuthors(stat, "Joao", "Santos");
        } catch (SQLException e) {
            System.out.println("Erro, não foi possível conectar ao DB: " + e);
        }
    }
    
    public void getAuthors(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println("----------------------");
                System.out.println("Código do autor: " + rs.getInt(1));
                System.out.println("Nome do autor: " + rs.getString(2));
                System.out.println("Sobrenome do autor: " + rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Erro getAuthors: " + e);
        }
    }
    
        public void insertAuthors( Statement stat, String firstName, String lastName) {
        try {
            int rowCount = stat.executeUpdate("INSERT INTO authors (firstName, lastName) VALUES ('" + firstName + "', '" + lastName + "')", Statement.RETURN_GENERATED_KEYS);
            if (rowCount > 0) {
                ResultSet rs = stat.getGeneratedKeys();
                while (rs.next())
                    System.out.println("Novo registro realizado no código: " + rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    
        public void getTitles(ResultSet rsTitles) {
            try {
                while (rsTitles.next()) {
                    System.out.println("----------------------");
                    System.out.println("ISBN: " + rsTitles.getInt("ISBN"));
                    System.out.println("Título: " + rsTitles.getString("Title"));
                    System.out.println("Número do Editor: " + rsTitles.getString("EditorNumber"));
                    System.out.println("Copyright: " + rsTitles.getString("Copyright"));
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }

        public void insertTitle(Statement stat, String title, String editorNumber, String copyright) {
            try {
                String query = "INSERT INTO titles (Title, EditorNumber, Copyright) VALUES ('" + title + "', '" + editorNumber + "', '" + copyright + "')";
                int rowCount = stat.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                if (rowCount > 0) {
                    ResultSet rsGeneratedKeys = stat.getGeneratedKeys();
                    while (rsGeneratedKeys.next()) {
                        System.out.println("Novo registro realizado no código: " + rsGeneratedKeys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }

}
