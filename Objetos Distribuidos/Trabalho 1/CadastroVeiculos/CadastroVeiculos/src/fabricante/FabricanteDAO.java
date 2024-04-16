
package fabricante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.sql.ConexaoMySQL;

public class FabricanteDAO {

    public int insert(Fabricante fabricante) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Fabricante(nome, paisOrigem) VALUES(?, ?)");
            ps.setString(1, fabricante.getNome());
            ps.setString(2, fabricante.getPaisOrigem());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public Fabricante read(int id) {
        Fabricante fabricante = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT nome, paisOrigem FROM Fabricante WHERE idFabricante = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String paisOrigem = rs.getString("paisOrigem");
                fabricante = new Fabricante(id, nome, paisOrigem);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricante;
    }

    public ArrayList<Fabricante> list() {
        ArrayList<Fabricante> fabricantesList = new ArrayList<>();
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT idFabricante, nome, paisOrigem FROM Fabricante");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idFabricante");
                String nome = rs.getString("nome");
                String paisOrigem = rs.getString("paisOrigem");
                Fabricante fabricante = new Fabricante(id, nome, paisOrigem);
                fabricantesList.add(fabricante);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricantesList;
    }
    
    public ArrayList<Fabricante> searchByTerm(String termoPesquisa) {
        ArrayList<Fabricante> fabricantesList = new ArrayList<>();
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps;

            // Verifica se o termo de pesquisa é um número (ID)
            if (termoPesquisa.matches("\\d+")) {
                // Se for um número, pesquisa apenas pelo ID
                ps = conn.prepareStatement("SELECT idFabricante, nome, paisOrigem FROM Fabricante WHERE idFabricante = ?");
                ps.setInt(1, Integer.parseInt(termoPesquisa));
            } else {
                // Se não for um número, pesquisa nos outros atributos
                ps = conn.prepareStatement("SELECT idFabricante, nome, paisOrigem FROM Fabricante WHERE nome LIKE ? OR paisOrigem LIKE ?");
                ps.setString(1, "%" + termoPesquisa + "%");
                ps.setString(2, "%" + termoPesquisa + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idFabricante");
                String nome = rs.getString("nome");
                String paisOrigem = rs.getString("paisOrigem");
                Fabricante fabricante = new Fabricante(id, nome, paisOrigem);
                fabricantesList.add(fabricante);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricantesList;
    }

    public int update(Fabricante fabricante) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE Fabricante SET nome = ?, paisOrigem = ? WHERE idFabricante = ?");
            ps.setString(1, fabricante.getNome());
            ps.setString(2, fabricante.getPaisOrigem());
            ps.setInt(3, fabricante.getIdFabricante());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public Fabricante findByNameAndPaisOrigem(String nome, String paisOrigem) {
        Fabricante fabricante = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Fabricante WHERE nome = ? AND paisOrigem = ?");
            ps.setString(1, nome);
            ps.setString(2, paisOrigem);
            ResultSet rs = ps.executeQuery();

            // Check if a result exists
            if (rs.next()) {
                fabricante = new Fabricante(
                        rs.getInt("idFabricante"),
                        rs.getString("nome"),
                        rs.getString("paisOrigem")
                );
            }

            // Close PreparedStatement within the try block
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricante;
    }
    
    public int delete(int id) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Fabricante WHERE idFabricante = ?");
            ps.setInt(1, id);
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }
}
