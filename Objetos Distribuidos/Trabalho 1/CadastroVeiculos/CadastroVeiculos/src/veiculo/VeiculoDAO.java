
package veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.sql.ConexaoMySQL;

public class VeiculoDAO {

    public int insert(Veiculo veiculo) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Veiculo(nome, cor, modelo) VALUES(?, ?, ?)");
            ps.setString(1, veiculo.getNome());
            ps.setString(2, veiculo.getCor());
            ps.setString(3, veiculo.getModelo());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public Veiculo read(int id) {
        Veiculo veiculo = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT nome, cor, modelo FROM Veiculo WHERE idVeiculo = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String cor = rs.getString("cor");
                String modelo = rs.getString("modelo");
                veiculo = new Veiculo(id, nome, cor, modelo);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculo;
    }
    
    public ArrayList<Veiculo> searchByTerm(String termoPesquisa) {
        ArrayList<Veiculo> veiculosList = new ArrayList<>();
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps;
            
            // Verifica se o termo de pesquisa é um número (ID)
            if (termoPesquisa.matches("\\d+")) {
                // Se for um número, pesquisa apenas pelo ID
                ps = conn.prepareStatement("SELECT idVeiculo, nome, cor, modelo FROM Veiculo WHERE idVeiculo = ?");
                ps.setInt(1, Integer.parseInt(termoPesquisa));
            } else {
                // Se não for um número, pesquisa nos outros atributos
                ps = conn.prepareStatement("SELECT idVeiculo, nome, cor, modelo FROM Veiculo WHERE nome LIKE ? OR cor LIKE ? OR modelo LIKE ?");
                ps.setString(1, "%" + termoPesquisa + "%");
                ps.setString(2, "%" + termoPesquisa + "%");
                ps.setString(3, "%" + termoPesquisa + "%");
            }
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idVeiculo");
                String nome = rs.getString("nome");
                String cor = rs.getString("cor");
                String modelo = rs.getString("modelo");
                Veiculo veiculo = new Veiculo(id, nome, cor, modelo);
                veiculosList.add(veiculo);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculosList;
    }

    
    
    public ArrayList<Veiculo> list() {
        ArrayList<Veiculo> veiculosList = new ArrayList<>();
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT idVeiculo, nome, cor, modelo FROM Veiculo");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idVeiculo");
                String nome = rs.getString("nome");
                String cor = rs.getString("cor");
                String modelo = rs.getString("modelo");
                Veiculo veiculo = new Veiculo(id, nome, cor, modelo);
                veiculosList.add(veiculo);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculosList;
    }

    public int update(Veiculo veiculo) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE Veiculo SET nome = ?, cor = ?, modelo = ? WHERE idVeiculo = ?");
            ps.setString(1, veiculo.getNome());
            ps.setString(2, veiculo.getCor());
            ps.setString(3, veiculo.getModelo());
            ps.setInt(4, veiculo.getIdVeiculo());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public int delete(int id) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Veiculo WHERE idVeiculo = ?");
            ps.setInt(1, id);
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }
    
    

}
    