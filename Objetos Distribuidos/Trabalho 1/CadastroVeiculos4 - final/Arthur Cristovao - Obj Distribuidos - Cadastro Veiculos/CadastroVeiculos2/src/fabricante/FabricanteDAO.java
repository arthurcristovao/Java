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
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("INSERT INTO Fabricante(nome, paisOrigem) VALUES(?, ?)");
            ps.setString(1, fabricante.getNome());
            ps.setString(2, fabricante.getPaisOrigem());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Fabricante read(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("SELECT nome, paisOrigem FROM Fabricante WHERE idFabricante = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String paisOrigem = rs.getString("paisOrigem");
                return new Fabricante(id, nome, paisOrigem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<Fabricante> list() {
        ArrayList<Fabricante> fabricantesList = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("SELECT idFabricante, nome, paisOrigem FROM Fabricante");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idFabricante");
                String nome = rs.getString("nome");
                String paisOrigem = rs.getString("paisOrigem");
                Fabricante fabricante = new Fabricante(id, nome, paisOrigem);
                fabricantesList.add(fabricante);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fabricantesList;
    }

    public ArrayList<Fabricante> searchByTerm(String termoPesquisa) {
        ArrayList<Fabricante> fabricantesList = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            if (termoPesquisa.matches("\\d+")) {
                ps = conn.prepareStatement("SELECT idFabricante, nome, paisOrigem FROM Fabricante WHERE idFabricante = ?");
                ps.setInt(1, Integer.parseInt(termoPesquisa));
            } else {
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
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fabricantesList;
    }

    public int update(Fabricante fabricante) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("UPDATE Fabricante SET nome = ?, paisOrigem = ? WHERE idFabricante = ?");
            ps.setString(1, fabricante.getNome());
            ps.setString(2, fabricante.getPaisOrigem());
            ps.setInt(3, fabricante.getIdFabricante());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Fabricante findByNameAndPaisOrigem(String nome, String paisOrigem) {
        Fabricante fabricante = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("SELECT * FROM Fabricante WHERE nome = ? AND paisOrigem = ?");
            ps.setString(1, nome);
            ps.setString(2, paisOrigem);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                fabricante = new Fabricante(
                        rs.getInt("idFabricante"),
                        rs.getString("nome"),
                        rs.getString("paisOrigem")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fabricante;
    }

    public int delete(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = ConexaoMySQL.getConexaoMySQL();
            ps = conn.prepareStatement("DELETE FROM Fabricante WHERE idFabricante = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
