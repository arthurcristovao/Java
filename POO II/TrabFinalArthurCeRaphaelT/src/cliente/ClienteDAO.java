package cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.ConexaoMySQL;

public class ClienteDAO {

    public int insert(Cliente cliente) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Cliente(nome, endereco, telefone) VALUES(?, ?, ?)");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getTelefone());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public Cliente read(int id) {
        Cliente cliente = null;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT nome, endereco, telefone FROM Cliente WHERE idCliente = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                cliente = new Cliente(id, nome, endereco, telefone);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public ArrayList<Cliente> list() {
        ArrayList<Cliente> clientesList = new ArrayList<>();

        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT idCliente, nome, endereco, telefone FROM Cliente");

            while (rs.next()) {
                int id = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                Cliente cliente = new Cliente(id, nome, endereco, telefone);
                clientesList.add(cliente);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientesList;
    }

    public int update(Cliente cliente) {
        int rowCount = 0;

        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE Cliente SET nome = ?, endereco = ?, telefone = ? WHERE idCliente = ?");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getTelefone());
            ps.setInt(4, cliente.getIdCliente());
            rowCount = ps.executeUpdate();

            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public int delete(int id) {
        int rowCount = 0;
        try {
            Connection conn = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Cliente WHERE idCliente = ?");
            ps.setInt(1, id);
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    
}
