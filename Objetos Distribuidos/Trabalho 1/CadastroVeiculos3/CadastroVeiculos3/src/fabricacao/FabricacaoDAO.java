
package fabricacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import veiculo.Veiculo;
import fabricante.Fabricante;
import fabricante.FabricanteDAO;
import main.sql.ConexaoMySQL;
import veiculo.VeiculoDAO;

public class FabricacaoDAO {

    public int insert(Fabricacao fabricacao) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Fabricacao(veiculo_id, fabricante_id, dataFabricacao, paisFabricacao) VALUES(?, ?, ?, ?)");
            ps.setInt(1, fabricacao.getVeiculo().getIdVeiculo());
            ps.setInt(2, fabricacao.getFabricante().getIdFabricante());
            ps.setDate(3, java.sql.Date.valueOf(fabricacao.getDataFabricacao()));
            ps.setString(4, fabricacao.getPaisFabricacao());
            rowCount = ps.executeUpdate();
            ps.close();
            conn.close();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public Fabricacao read(int id) {
        Fabricacao fabricacao = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT veiculo_id, fabricante_id, dataFabricacao, paisFabricacao FROM Fabricacao WHERE idFabricacao = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int veiculoId = rs.getInt("veiculo_id");
                int fabricanteId = rs.getInt("fabricante_id");
                LocalDate dataFabricacao = rs.getDate("dataFabricacao").toLocalDate();
                String paisFabricacao = rs.getString("paisFabricacao");
                Veiculo veiculo = new VeiculoDAO().read(veiculoId);
                Fabricante fabricante = new FabricanteDAO().read(fabricanteId);
                fabricacao = new Fabricacao(id, veiculo, fabricante, dataFabricacao, paisFabricacao);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricacao;
    }

    public ArrayList<Fabricacao> list() {
        ArrayList<Fabricacao> fabricacoesList = new ArrayList<>();
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT idFabricacao, veiculo_id, fabricante_id, dataFabricacao, paisFabricacao FROM Fabricacao");
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
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricacoesList;
    }


    public ArrayList<Fabricacao> searchByTerm(String termoPesquisa) {
        ArrayList<Fabricacao> fabricacoesList = new ArrayList<>();
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps;

            String sql = "SELECT * FROM Fabricacao f " +
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
                int veiculo_id = rs.getInt("veiculo_id");
                int fabricante_id = rs.getInt("fabricante_id");
                LocalDate dataFabricacao = rs.getDate("dataFabricacao").toLocalDate();
                String paisFabricacao = rs.getString("paisFabricacao");

                Veiculo veiculo = new Veiculo(rs.getInt("v.idVeiculo"), rs.getString("v.nome"), rs.getString("v.cor"), rs.getString("v.modelo"));
                Fabricante fabricante = new Fabricante(rs.getInt("fab.idFabricante"), rs.getString("fab.nome"), rs.getString("fab.paisOrigem"));

                Fabricacao fabricacao = new Fabricacao(idFabricacao, veiculo, fabricante, dataFabricacao, paisFabricacao);
                fabricacoesList.add(fabricacao);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricacoesList;
    }

    public int update(Fabricacao fabricacao) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE Fabricacao SET veiculo_id = ?, fabricante_id = ?, dataFabricacao = ?, paisFabricacao = ? WHERE idFabricacao = ?");
            ps.setInt(1, fabricacao.getVeiculo().getIdVeiculo());
            ps.setInt(2, fabricacao.getFabricante().getIdFabricante());
            ps.setDate(3, java.sql.Date.valueOf(fabricacao.getDataFabricacao()));
            ps.setString(4, fabricacao.getPaisFabricacao());
            ps.setInt(5, fabricacao.getIdFabricacao());
            rowCount = ps.executeUpdate();
            ps.close();
            conn.close();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public int delete(int id) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Fabricacao WHERE idFabricacao = ?");
            ps.setInt(1, id);
            rowCount = ps.executeUpdate();
            ps.close();
            conn.close();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public ArrayList<Fabricacao> getFabricacoesByVeiculoId(int veiculoId) {
        ArrayList<Fabricacao> fabricacoesList = new ArrayList<>();
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT idFabricacao FROM Fabricacao WHERE veiculo_id = ?");
            ps.setInt(1, veiculoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idFabricacao");
                Fabricacao fabricacao = read(id);
                fabricacoesList.add(fabricacao);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricacoesList;
    }

    public int deleteFabricacoesByVeiculoId(int veiculoId) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Fabricacao WHERE veiculo_id = ?");
            ps.setInt(1, veiculoId);
            rowCount = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public int deleteFabricacoesByFabricanteId(int idFabricante) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Fabricacao WHERE fabricante_id = ?");
            ps.setInt(1, idFabricante);
            rowCount = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public ArrayList<Fabricacao> getFabricacoesByFabricanteId(int idFabricante) {
        ArrayList<Fabricacao> fabricacoesList = new ArrayList<>();
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT idFabricacao FROM Fabricacao WHERE fabricante_id = ?");
            ps.setInt(1, idFabricante);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idFabricacao");
                Fabricacao fabricacao = read(id);
                fabricacoesList.add(fabricacao);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fabricacoesList;
    }

}
