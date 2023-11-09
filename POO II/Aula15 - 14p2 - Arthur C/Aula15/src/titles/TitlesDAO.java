package titles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sql.ConexaoMySQL;
import java.util.logging.*;

public class TitlesDAO {
    
    public int insert(Titles title) {
        try {
            
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO titles (ISBN, Title, EditionNumber, Copyright) VALUES (?, ?, ?, ?)");
             
        
            statement.setInt(1, title.getISBN());
            statement.setString(2, title.getTitle());
            statement.setString(3, title.getEditionNumber());
            statement.setString(4, title.getCopyright());

            int rowCount = statement.executeUpdate();
            
            System.out.println("Título inserido com sucesso.");
            conn.close();
            return rowCount;
        } catch (SQLException e) {
            Logger.getLogger(TitlesDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

    public Titles read(int ISBN) {
        Titles title = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM titles WHERE ISBN = ?");
        
            statement.setInt(1, ISBN);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                title = new Titles(
                        resultSet.getInt("ISBN"),
                        resultSet.getString("Title"),
                        resultSet.getString("EditionNumber"),
                        resultSet.getString("Copyright")
                );
            }
            conn.close();
            System.out.println("Leitura do título realizada com sucesso.");
        } catch (SQLException e) {
            Logger.getLogger(TitlesDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return title;
    }

    public int update(Titles title) {
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement statement = conn.prepareStatement("UPDATE titles SET Title = ?, EditionNumber = ?, Copyright = ? WHERE ISBN = ?");
            
            statement.setString(1, title.getTitle());
            statement.setString(2, title.getEditionNumber());
            statement.setString(3, title.getCopyright());
            statement.setInt(4, title.getISBN());

            int rowCount = statement.executeUpdate();
            System.out.println("Título atualizado com sucesso.");
            return rowCount;
        } catch (SQLException e) {
            Logger.getLogger(TitlesDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

    public int delete(int ISBN) {
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM titles WHERE ISBN = ?");
        
            statement.setInt(1, ISBN);
            int rowCount = statement.executeUpdate();
            conn.close();
            System.out.println("Título excluído com sucesso.");
            return rowCount;
            
        } catch (SQLException e) {
            Logger.getLogger(TitlesDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

    public List<Titles> list() {
        List<Titles> titlesList = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             Statement statement = conn.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM titles");

            while (resultSet.next()) {
                Titles title = new Titles(
                        resultSet.getInt("ISBN"),
                        resultSet.getString("Title"),
                        resultSet.getString("EditionNumber"),
                        resultSet.getString("Copyright")
                );
                titlesList.add(title);
            }
            System.out.println("Listagem de títulos concluída com sucesso.");
        } catch (SQLException e) {
            Logger.getLogger(TitlesDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return titlesList;
    }
}