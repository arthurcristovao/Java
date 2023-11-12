package carro;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.ConexaoMySQL;

public class CarroDAO {

    public int insert(Carro c) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Carro(marca, modelo, ano, disponivel) VALUES(?, ?, ?, ?)");
            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getAno());
            ps.setBoolean(4, c.isDisponivel());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public Carro read(int id) {
        Carro c = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT marca, modelo, ano, disponivel FROM Carro WHERE idCarro = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                boolean disponivel = rs.getBoolean("disponivel");
                c = new Carro(id, marca, modelo, ano, disponivel);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public ArrayList<Carro> list() {
        ArrayList<Carro> carrosList = new ArrayList<>();

        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT idCarro, marca, modelo, ano, disponivel FROM Carro");

            while (rs.next()) {
                int id = rs.getInt("idCarro");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                boolean disponivel = rs.getBoolean("disponivel");
                Carro carro = new Carro(id, marca, modelo, ano, disponivel);
                carrosList.add(carro);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return carrosList;
    }

    public int update(Carro c) {
        int rowCount = 0;

        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE Carro SET marca = ?, modelo = ?, ano = ?, disponivel = ? WHERE idCarro = ?");
            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getAno());
            ps.setBoolean(4, c.isDisponivel());
            ps.setInt(5, c.getIdCarro());
            rowCount = ps.executeUpdate();

            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public int delete(int id) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Carro WHERE idCarro = ?");
            ps.setInt(1, id);
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
}
