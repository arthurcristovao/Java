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
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("INSERT INTO Veiculo(nome, cor, modelo) VALUES(?, ?, ?)");
            ps.setString(1, veiculo.getNome());
            ps.setString(2, veiculo.getCor());
            ps.setString(3, veiculo.getModelo());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Veiculo read(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("SELECT nome, cor, modelo FROM Veiculo WHERE idVeiculo = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String cor = rs.getString("cor");
                String modelo = rs.getString("modelo");
                return new Veiculo(id, nome, cor, modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public ArrayList<Veiculo> searchByTerm(String termoPesquisa) {
        ArrayList<Veiculo> veiculosList = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            if (termoPesquisa.matches("\\d+")) {
                ps = conn.prepareStatement("SELECT idVeiculo, nome, cor, modelo FROM Veiculo WHERE idVeiculo = ?");
                ps.setInt(1, Integer.parseInt(termoPesquisa));
            } else {
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
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return veiculosList;
    }

    public ArrayList<Veiculo> list() {
        ArrayList<Veiculo> veiculosList = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("SELECT idVeiculo, nome, cor, modelo FROM Veiculo");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idVeiculo");
                String nome = rs.getString("nome");
                String cor = rs.getString("cor");
                String modelo = rs.getString("modelo");
                Veiculo veiculo = new Veiculo(id, nome, cor, modelo);
                veiculosList.add(veiculo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return veiculosList;
    }

    public int update(Veiculo veiculo) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("UPDATE Veiculo SET nome = ?, cor = ?, modelo = ? WHERE idVeiculo = ?");
            ps.setString(1, veiculo.getNome());
            ps.setString(2, veiculo.getCor());
            ps.setString(3, veiculo.getModelo());
            ps.setInt(4, veiculo.getIdVeiculo());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int delete(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("DELETE FROM Veiculo WHERE idVeiculo = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
