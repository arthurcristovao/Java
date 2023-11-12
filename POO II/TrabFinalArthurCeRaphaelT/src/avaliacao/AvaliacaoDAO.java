package avaliacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import aluguel.*;
import sql.ConexaoMySQL;

public class AvaliacaoDAO {

    public int insert(Avaliacao avaliacao) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Avaliacao(aluguel_id, nota, comentario) VALUES(?, ?, ?)");
            ps.setInt(1, avaliacao.getAluguel().getIdAluguel());
            ps.setInt(2, avaliacao.getNota());
            ps.setString(3, avaliacao.getComentario());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public Avaliacao read(int id) {
        Avaliacao avaliacao = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT aluguel_id, nota, comentario FROM Avaliacao WHERE idAvaliacao = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int aluguelId = rs.getInt("aluguel_id");
                int nota = rs.getInt("nota");
                String comentario = rs.getString("comentario");
                AluguelDAO aluguelDAO = new AluguelDAO();
                Aluguel aluguel = aluguelDAO.read(aluguelId);
                avaliacao = new Avaliacao(id, aluguel, nota, comentario);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avaliacao;
    }

    public ArrayList<Avaliacao> list() {
        ArrayList<Avaliacao> avaliacoesList = new ArrayList<>();

        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT idAvaliacao, aluguel_id, nota, comentario FROM Avaliacao");

            while (rs.next()) {
                int id = rs.getInt("idAvaliacao");
                int aluguelId = rs.getInt("aluguel_id");
                int nota = rs.getInt("nota");
                String comentario = rs.getString("comentario");
                AluguelDAO aluguelDAO = new AluguelDAO();
                Aluguel aluguel = aluguelDAO.read(aluguelId);
                Avaliacao avaliacao = new Avaliacao(id, aluguel, nota, comentario);
                avaliacoesList.add(avaliacao);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return avaliacoesList;
    }

    public int update(Avaliacao avaliacao) {
        int rowCount = 0;

        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE Avaliacao SET aluguel_id = ?, nota = ?, comentario = ? WHERE idAvaliacao = ?");
            ps.setInt(1, avaliacao.getAluguel().getIdAluguel());
            ps.setInt(2, avaliacao.getNota());
            ps.setString(3, avaliacao.getComentario());
            ps.setInt(4, avaliacao.getIdAvaliacao());
            rowCount = ps.executeUpdate();

            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public int delete(int id) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Avaliacao WHERE idAvaliacao = ?");
            ps.setInt(1, id);
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
}
