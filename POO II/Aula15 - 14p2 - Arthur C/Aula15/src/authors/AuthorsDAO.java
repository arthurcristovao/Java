/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authors;

import java.sql.*;
import java.util.logging.Level;
import java.util.ArrayList;
import sql.ConexaoMySQL;
import java.util.logging.*;


/**
 *
 * @author 08220186
 */
public class AuthorsDAO{
    public int insert(Authors a) {
        int rowCount = 0;
        try {
            
            Connection conn = ConexaoMySQL.getConexaoMySQL(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO authors(firstName, lastName) VALUES(?,?)");
            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(AuthorsDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return rowCount;
    }

    
    public Authors read(int id) {
        Authors a = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT firstName, lastName FROM authors WHERE AuthorsID=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String nome = rs.getString("firstName");
                String sobrenome = rs.getString("lastName");
                a = new Authors(id, nome, sobrenome);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
        
    
    public ArrayList<Authors> list() {
        ArrayList<Authors> authorsList = new ArrayList<>();

        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT AuthorsID, firstName, lastName FROM authors");

            while (rs.next()) {
                int id = rs.getInt("AuthorsID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Authors author = new Authors(id, firstName, lastName);
                authorsList.add(author);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return authorsList;
    }

    public int update(Authors a) {
        int rowCount = 0;

        try {
            
            Connection conn = ConexaoMySQL.getConexaoMySQL(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE authors SET firstName = ?, lastName = ? WHERE AuthorsID = ?");
            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            ps.setInt(3, a.getAuthorsID());
            rowCount = ps.executeUpdate();
            
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(AuthorsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    
    public int delete(int id) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL(); 
            PreparedStatement ps = conn.prepareStatement("DELETE FROM authors WHERE AuthorsID = ?");
            ps.setInt(1, id);
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(AuthorsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
}
