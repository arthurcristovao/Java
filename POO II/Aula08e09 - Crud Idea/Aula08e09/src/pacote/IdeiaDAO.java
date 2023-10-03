package pacote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdeiaDAO {

    public void inserir(Ideia ideia) {
        String sql = "INSERT INTO Ideia (titulo, descricao, urgencia) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoDB.obterConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, ideia.getTitulo());
            preparedStatement.setString(2, ideia.getDescricao());
            preparedStatement.setInt(3, ideia.getUrgencia().ordinal() + 1); 

            preparedStatement.executeUpdate();
            System.out.println("Ideia inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir ideia: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Ideia WHERE id = ?";

        try (Connection conexao = ConexaoDB.obterConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Ideia deletada com sucesso!");
            } else {
                System.out.println("Nenhuma ideia encontrada com o ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar ideia: " + e.getMessage());
        }
    }

    public List<Ideia> listar() {
        List<Ideia> ideias = new ArrayList<>();
        String sql = "SELECT * FROM Ideia";

        try (Connection conexao = ConexaoDB.obterConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String descricao = resultSet.getString("descricao");
                int urgencia = resultSet.getInt("urgencia"); 

                Ideia ideia = new Ideia(id, titulo, descricao, Urgencia.values()[urgencia - 1]);
                ideias.add(ideia);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar ideias: " + e.getMessage());
        }

        return ideias;
    }

}
