package aluguel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import cliente.*;
import carro.*;
import sql.ConexaoMySQL;

public class AluguelDAO {

    public int insert(Aluguel aluguel) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Aluguel(cliente_id, carro_id, dataAluguel, dataDevolucao) VALUES(?, ?, ?, ?)");
            ps.setInt(1, aluguel.getCliente().getIdCliente());
            ps.setInt(2, aluguel.getCarro().getIdCarro());
            ps.setDate(3, Date.valueOf(aluguel.getDataAluguel()));
            ps.setDate(4, Date.valueOf(aluguel.getDataDevolucao()));
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public Aluguel read(int id) {
        Aluguel aluguel = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT cliente_id, carro_id, dataAluguel, dataDevolucao FROM Aluguel WHERE idAluguel = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int clienteId = rs.getInt("cliente_id");
                int carroId = rs.getInt("carro_id");
                LocalDate dataAluguel = rs.getDate("dataAluguel").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("dataDevolucao").toLocalDate();
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.read(clienteId);
                CarroDAO carroDAO = new CarroDAO();
                Carro carro = carroDAO.read(carroId);
                aluguel = new Aluguel(id, cliente, carro, dataAluguel, dataDevolucao);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aluguel;
    }

    public ArrayList<Aluguel> list() {
        ArrayList<Aluguel> alugueisList = new ArrayList<>();

        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT idAluguel, cliente_id, carro_id, dataAluguel, dataDevolucao FROM Aluguel");

            while (rs.next()) {
                int id = rs.getInt("idAluguel");
                int clienteId = rs.getInt("cliente_id");
                int carroId = rs.getInt("carro_id");
                LocalDate dataAluguel = rs.getDate("dataAluguel").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("dataDevolucao").toLocalDate();
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.read(clienteId);
                CarroDAO carroDAO = new CarroDAO();
                Carro carro = carroDAO.read(carroId);
                Aluguel aluguel = new Aluguel(id, cliente, carro, dataAluguel, dataDevolucao);
                alugueisList.add(aluguel);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alugueisList;
    }

    public int update(Aluguel aluguel) {
        int rowCount = 0;

        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE Aluguel SET cliente_id = ?, carro_id = ?, dataAluguel = ?, dataDevolucao = ? WHERE idAluguel = ?");
            ps.setInt(1, aluguel.getCliente().getIdCliente());
            ps.setInt(2, aluguel.getCarro().getIdCarro());
            ps.setDate(3, Date.valueOf(aluguel.getDataAluguel()));
            ps.setDate(4, Date.valueOf(aluguel.getDataDevolucao()));
            ps.setInt(5, aluguel.getIdAluguel());
            rowCount = ps.executeUpdate();

            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public int delete(int id) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Aluguel WHERE idAluguel = ?");
            ps.setInt(1, id);
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
}
