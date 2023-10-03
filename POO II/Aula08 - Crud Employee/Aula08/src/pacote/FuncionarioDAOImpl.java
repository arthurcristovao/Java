package pacote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOImpl implements FuncionarioDAO {
    private static Connection connection;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String SERVIDOR = "127.0.0.1";
    private static final String PORTA = "3306";
    private static final String BANCO = "sistema";
    private static final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    
    public static Connection getConnection(){
        if (connection == null) {
    	try { 
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar a conexão com o banco de dados.");
        }
        }
        return connection;
    }

    @Override
    public void adicionarFuncionario(Funcionario funcionario) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "INSERT INTO funcionarios (nome, cargo, salario) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCargo());
            preparedStatement.setDouble(3, funcionario.getSalario());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                funcionario.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    @Override
    public void atualizarFuncionario(Funcionario funcionario) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                     "UPDATE funcionarios SET nome = ?, cargo = ?, salario = ? WHERE id = ?")) {

            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCargo());
            preparedStatement.setDouble(3, funcionario.getSalario());
            preparedStatement.setInt(4, funcionario.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerFuncionario(int id) {
        try ( PreparedStatement preparedStatement = getConnection().prepareStatement(
                     "DELETE FROM funcionarios WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Funcionario buscarFuncionarioPorId(int id) {
        Funcionario funcionario = null;

        try ( PreparedStatement preparedStatement = getConnection().prepareStatement(
                     "SELECT * FROM funcionarios WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cargo = resultSet.getString("cargo");
                double salario = resultSet.getDouble("salario");
                funcionario = new Funcionario(id, nome, cargo, salario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionario;
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();

        try ( Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM funcionarios")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cargo = resultSet.getString("cargo");
                double salario = resultSet.getDouble("salario");

                Funcionario funcionario = new Funcionario(id, nome, cargo, salario);
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }
}
