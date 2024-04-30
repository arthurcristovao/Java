package fabricacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import fabricante.Fabricante;
import fabricante.FabricanteDAO;
import veiculo.Veiculo;
import veiculo.VeiculoDAO;
import main.sql.ConexaoMySQL;

public class FabricacaoDAO {

    public int insert(Fabricacao fabricacao) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("INSERT INTO Fabricacao(veiculo_id, fabricante_id, dataFabricacao, paisFabricacao) VALUES(?, ?, ?, ?)");
            ps.setInt(1, fabricacao.getVeiculo().getIdVeiculo());
            ps.setInt(2, fabricacao.getFabricante().getIdFabricante());
            ps.setDate(3, java.sql.Date.valueOf(fabricacao.getDataFabricacao()));
            ps.setString(4, fabricacao.getPaisFabricacao());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Fabricacao read(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("SELECT veiculo_id, fabricante_id, dataFabricacao, paisFabricacao FROM Fabricacao WHERE idFabricacao = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int veiculoId = rs.getInt("veiculo_id");
                int fabricanteId = rs.getInt("fabricante_id");
                LocalDate dataFabricacao = rs.getDate("dataFabricacao").toLocalDate();
                String paisFabricacao = rs.getString("paisFabricacao");
                Veiculo veiculo = new VeiculoDAO().read(veiculoId);
                Fabricante fabricante = new FabricanteDAO().read(fabricanteId);
                return new Fabricacao(id, veiculo, fabricante, dataFabricacao, paisFabricacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<Fabricacao> list() {
        ArrayList<Fabricacao> fabricacoesList = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("SELECT idFabricacao, veiculo_id, fabricante_id, dataFabricacao, paisFabricacao FROM Fabricacao");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idFabricacao");
                int veiculoId = rs.getInt("veiculo_id");
                int fabricanteId = rs.getInt("fabricante_id");
                LocalDate dataFabricacao = rs.getDate("dataFabricacao").toLocalDate();
                String paisFabricacao = rs.getString("paisFabricacao");
                Veiculo veiculo = new VeiculoDAO().read(veiculoId);
                Fabricante fabricante = new FabricanteDAO().read(fabricanteId);
                Fabricacao fabricacao = new Fabricacao(id, veiculo, fabricante, dataFabricacao, paisFabricacao);
                fabricacoesList.add(fabricacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fabricacoesList;
    }

    public ArrayList<Fabricacao> searchByTerm(String termoPesquisa) {
        ArrayList<Fabricacao> fabricacoesList = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            String sql = "SELECT f.idFabricacao, f.veiculo_id, f.fabricante_id, f.dataFabricacao, f.paisFabricacao " +
                         "FROM Fabricacao f " +
                         "JOIN Veiculo v ON f.veiculo_id = v.idVeiculo " +
                         "JOIN Fabricante fab ON f.fabricante_id = fab.idFabricante " +
                         "WHERE f.idFabricacao = ? OR " +
                         "v.nome LIKE ? OR v.cor LIKE ? OR v.modelo LIKE ? OR " +
                         "fab.nome LIKE ? OR fab.paisOrigem LIKE ? OR " +
                         "DATE_FORMAT(f.dataFabricacao, '%d/%m/%Y') LIKE ? OR f.paisFabricacao LIKE ?";
            ps = conn.prepareStatement(sql);
            
            for (int i = 1; i <= 8; i++) {
                ps.setString(i, "%" + termoPesquisa + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idFabricacao = rs.getInt("idFabricacao");
                int veiculoId = rs.getInt("veiculo_id");
                int fabricanteId = rs.getInt("fabricante_id");
                LocalDate dataFabricacao = rs.getDate("dataFabricacao").toLocalDate();
                String paisFabricacao = rs.getString("paisFabricacao");
                Veiculo veiculo = new VeiculoDAO().read(veiculoId);
                Fabricante fabricante = new FabricanteDAO().read(fabricanteId);
                Fabricacao fabricacao = new Fabricacao(idFabricacao, veiculo, fabricante, dataFabricacao, paisFabricacao);
                fabricacoesList.add(fabricacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fabricacoesList;
    }

    public int update(Fabricacao fabricacao) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("UPDATE Fabricacao SET veiculo_id = ?, fabricante_id = ?, dataFabricacao = ?, paisFabricacao = ? WHERE idFabricacao = ?");
            ps.setInt(1, fabricacao.getVeiculo().getIdVeiculo());
            ps.setInt(2, fabricacao.getFabricante().getIdFabricante());
            ps.setDate(3, java.sql.Date.valueOf(fabricacao.getDataFabricacao()));
            ps.setString(4, fabricacao.getPaisFabricacao());
            ps.setInt(5, fabricacao.getIdFabricacao());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int delete(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("DELETE FROM Fabricacao WHERE idFabricacao = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Fabricacao> getFabricacoesByVeiculoId(int veiculoId) {
        ArrayList<Fabricacao> fabricacoesList = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("SELECT idFabricacao FROM Fabricacao WHERE veiculo_id = ?");
            ps.setInt(1, veiculoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idFabricacao");
                Fabricacao fabricacao = read(id);
                fabricacoesList.add(fabricacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fabricacoesList;
    }

    public int deleteFabricacoesByVeiculoId(int veiculoId) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("DELETE FROM Fabricacao WHERE veiculo_id = ?");
            ps.setInt(1, veiculoId);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int deleteFabricacoesByFabricanteId(int idFabricante) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("DELETE FROM Fabricacao WHERE fabricante_id = ?");
            ps.setInt(1, idFabricante);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Fabricacao> getFabricacoesByFabricanteId(int idFabricante) {
        ArrayList<Fabricacao> fabricacoesList = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("SELECT idFabricacao FROM Fabricacao WHERE fabricante_id = ?");
            ps.setInt(1, idFabricante);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idFabricacao");
                Fabricacao fabricacao = read(id);
                fabricacoesList.add(fabricacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fabricacoesList;
    }

}
