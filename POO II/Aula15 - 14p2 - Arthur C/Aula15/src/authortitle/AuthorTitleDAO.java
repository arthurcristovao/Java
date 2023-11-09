package authortitle;

import java.sql.*;
import java.util.ArrayList;
import authors.*;
import java.util.logging.*;
import titles.*;
import sql.ConexaoMySQL;

public class AuthorTitleDAO {
    public void associateAuthorTitle(int authorID, int titleISBN){
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO AuthorTitle (atAuthorsID, atISBN) VALUES (?, ?)")) {
            statement.setInt(1, authorID);
            statement.setInt(2, titleISBN);
            int rowCount = statement.executeUpdate();
            
            if (rowCount > 0) {
                System.out.println("Associação realizada com sucesso.");
            } else {
                System.out.println("Falha ao associar autor e título.");
            }

        } catch (SQLException e) {
            Logger.getLogger(AuthorTitleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void disassociateAuthorTitle(int authorID, int titleISBN){
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM AuthorTitle WHERE atAuthorsID = ? AND atISBN = ?")) {
            statement.setInt(1, authorID);
            statement.setInt(2, titleISBN);
            int rowCount = statement.executeUpdate();
            
            if (rowCount > 0) {
                System.out.println("Desassociação realizada com sucesso.");
            } else {
                System.out.println("Falha ao desassociar autor e título.");
            }

        } catch (SQLException e) {
            Logger.getLogger(AuthorTitleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public ArrayList<Titles> getTitlesByAuthor(int authorID){
        ArrayList<Titles> titles = new ArrayList<Titles>();
        
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM titles INNER JOIN AuthorTitle ON titles.ISBN = AuthorTitle.atISBN WHERE AuthorTitle.atAuthorsID = ?")) {
            statement.setInt(1, authorID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Titles title = new Titles(
                        resultSet.getInt("ISBN"),
                        resultSet.getString("Title"),
                        resultSet.getString("EditionNumber"),
                        resultSet.getString("Copyright")
                );
                titles.add(title);
            }
        } catch (SQLException e) {
            Logger.getLogger(AuthorTitleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return titles;
    }
    
    public ArrayList<Authors> getAuthorsByTitle(int titleISBN){
        ArrayList<Authors> authors = new ArrayList<Authors>();
        
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM authors INNER JOIN AuthorTitle ON authors.authorsID = AuthorTitle.atAuthorsID WHERE AuthorTitle.atISBN = ?")) {
            statement.setInt(1, titleISBN);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Authors author = new Authors(
                        resultSet.getInt("authorsID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName")
                );
                authors.add(author);
            }
        } catch (SQLException e) {
            Logger.getLogger(AuthorTitleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return authors;
    }
}
