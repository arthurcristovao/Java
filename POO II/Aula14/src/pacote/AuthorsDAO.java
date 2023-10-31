/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.sql.*;
import java.util.logging.Level;
import java.util.ArrayList;


/**
 *
 * @author 08220186
 */
public class AuthorsDAO implements DAO{

    @Override
    public int insert(Authors a) {
        int rowCount = 0;
        try (Connection conn = ConexaoMySQL.getConexaoMySQL()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO authors(firstName, lastName) VALUES(?,?)");
            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            // Faça o tratamento adequado da exceção
            ex.printStackTrace(); // Isso irá mostrar o erro no console para depuração
        }
        return rowCount;
    }

    @Override
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
            // Faça o tratamento adequado da exceção
            ex.printStackTrace(); // Isso irá mostrar o erro no console para depuração
        }
        return a;
    }
        
    @Override
    public ArrayList<Authors> list() {
        
        return null;
        
    }

    @Override
    public int update(Authors a) {
        
        return 0;
        
    }

    @Override
    public int delete(int id) {
        
        return 0;
        
    }
    
}
