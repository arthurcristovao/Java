package pacote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public void criarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, idade) VALUES (?, ?)";

        try (Connection conexao = Conexao.obterConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setInt(2, usuario.getIdade());

            preparedStatement.executeUpdate();

            System.out.println("Usu�rio salvo com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar usu�rio: " + e.getMessage());
        }
    }
    
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, idade = ? WHERE id = ?";

        try (Connection conexao = Conexao.obterConexao();
            PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setInt(2, usuario.getIdade());
            preparedStatement.setInt(3, usuario.getId());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usu�rio atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usu�rio encontrado com o ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usu�rio: " + e.getMessage());
        }
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        String sql = "SELECT id, nome, idade FROM usuarios WHERE nome = ?";

        try (Connection conexao = Conexao.obterConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, nome);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int idade = resultSet.getInt("idade");

                    return new Usuario(id, nome, idade);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usu�rio por nome: " + e.getMessage());
        }

        return null; // Retorna null se o usu�rio n�o for encontrado
    }
    
    public void deletarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conexao = Conexao.obterConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usu�rio com ID " + id + " foi exclu�do com sucesso.");
            } else {
                System.out.println("Nenhum usu�rio encontrado com o ID " + id + ". Nenhuma exclus�o realizada.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir usu�rio: " + e.getMessage());
        }
    }

    
}
