package pacote;

import java.sql.*;
import java.util.Scanner;

public class Projeto {
    private static Connection c = null;
    private static Statement stat = null;
    private ResultSet rs = null;
    private ResultSet rsTitles = null;
    
    public static void main(String[] args) throws SQLException {
        c = DriverManager.getConnection("jdbc:mysql://localhost/aula12?user=root");
        stat = c.createStatement();
        
        Projeto projeto = new Projeto();
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("-----------> Escolha uma opção <-------------");
            System.out.println("1 - Mostrar lista de autores");
            System.out.println("2 - Mostrar lista de livros");
            System.out.println("3 - Cadastrar autor");
            System.out.println("4 - Cadastrar livro");
            System.out.println("5 - Alterar cadastro autor");
            System.out.println("6 - Alterar cadastro livro");
            System.out.println("7 - Excluir autor");
            System.out.println("8 - Excluir livro");
            System.out.println("0 - Sair");
            System.out.println("---------------------------------------------");
            System.out.print("Digite: ");
            int opcao = scan.nextInt();

            if (opcao == 1) {
                projeto.getAuthors();
            } else if (opcao == 2) {
                projeto.getTitles();
            } else if (opcao == 3) {
                projeto.insertAuthors();
            } else if (opcao == 4) {
                projeto.insertTitle();
            } else if (opcao == 5) {
                projeto.updateAuthors();
            } else if (opcao == 6) {
                projeto.updateTitles();
            } else if (opcao == 7) {
                projeto.deleteAuthors();
            } else if (opcao == 8) {
                projeto.deleteTitles();
            } else if (opcao == 0) {
                System.out.println("Saindo do programa...");
                System.exit(0);
            } else {
                System.out.println("Opção inválida!");
            }
        } while (true);
    }
    
    public void getAuthors() throws SQLException {
        String sql = "SELECT * FROM authors";
        PreparedStatement preparedStatement = c.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        try {
            while (rs.next()) {
                System.out.println("----------------------");
                System.out.println("Código do autor: " + rs.getInt(1));
                System.out.println("Nome do autor: " + rs.getString(2));
                System.out.println("Sobrenome do autor: " + rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Erro getAuthors: " + e);
        }
    }

    public void getTitles() throws SQLException {
        String sql = "SELECT * FROM titles";
        PreparedStatement preparedStatement = c.prepareStatement(sql);
        ResultSet rsTitles = preparedStatement.executeQuery();

        try {
            while (rsTitles.next()) {
                System.out.println("----------------------");
                System.out.println("ISBN: " + rsTitles.getInt("ISBN"));
                System.out.println("Título: " + rsTitles.getString("Title"));
                System.out.println("Número do Editor: " + rsTitles.getString("EditorNumber"));
                System.out.println("Copyright: " + rsTitles.getString("Copyright"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } 
    }

    public void insertAuthors() throws SQLException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o nome do autor: ");
        String firstName = scan.next();

        System.out.println("Digite o sobrenome do autor: ");
        String lastName = scan.next();

        String sql = "INSERT INTO authors (firstName, lastName) VALUES (?, ?)";
        PreparedStatement preparedStatement = c.prepareStatement(sql);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);

        int rowCount = preparedStatement.executeUpdate();

        if (rowCount > 0) {
            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next()) {
                System.out.println("Novo registro realizado no código: " + rs.getInt(1));
            }
        }
    }

    public void insertTitle() throws SQLException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o título do livro: ");
        String title = scan.next();

        System.out.println("Digite o número do editor: ");
        String editorNumber = scan.next();

        System.out.println("Digite o copyright: ");
        String copyright = scan.next();

        String sql = "INSERT INTO titles (Title, EditorNumber, Copyright) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = c.prepareStatement(sql);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, editorNumber);
        preparedStatement.setString(3, copyright);

        int rowCount = preparedStatement.executeUpdate();

        if (rowCount > 0) {
            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next()) {
                System.out.println("Novo registro realizado no código: " + rs.getInt(1));
            }
        }
    }
        
    public void updateAuthors() throws SQLException {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("########## VAMOS ALTERAR UM AUTOR ############");
            System.out.println("Digite o nome: ");
            String nome = teclado.next();
            System.out.println("Digite o código: ");
            int codigo = teclado.nextInt();

            String sql = "UPDATE authors SET firstName=? WHERE authorsID=?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, codigo);

            int retorno = preparedStatement.executeUpdate();

            if (retorno > 0) {
                System.out.println("\nNovo registro alterado: " + codigo + " - " + nome);
            } else {
                System.out.println("Não foi possível alterar o registro!");
            }
        } catch (Exception e) {
            System.out.println("Erro: Não conseguiu conectar no BD");
        } 
    }

    public void updateTitles() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o código do livro: ");
        int id = scan.nextInt();

        System.out.println("Digite o novo título do livro: ");
        String title = scan.next();

        String sql = "UPDATE titles SET Title = ? WHERE ISBN = ?";
        PreparedStatement preparedStatement = c.prepareStatement(sql);
        preparedStatement.setString(1, title);
        preparedStatement.setInt(2, id);

        int rowCount = preparedStatement.executeUpdate();

        if (rowCount > 0) {
            System.out.println("Registro atualizado com sucesso!");
        } else {
            System.out.println("Não foi possível atualizar o registro.");
        }
    }

    public void deleteAuthors() throws SQLException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o código do autor: ");
        int id = scan.nextInt();

        String sql = "DELETE FROM authors WHERE AuthorsID = ?";
        PreparedStatement preparedStatement = c.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int rowCount = preparedStatement.executeUpdate();

        if (rowCount > 0) {
            System.out.println("Registro excluído com sucesso!");
        } else {
            System.out.println("Não foi possível excluir o registro.");
        }
    }

    public void deleteTitles() throws SQLException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o código do livro: ");
        int id = scan.nextInt();

        String sql = "DELETE FROM titles WHERE ISBN = ?";
        PreparedStatement preparedStatement = c.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int rowCount = preparedStatement.executeUpdate();

        if (rowCount > 0) {
            System.out.println("Registro excluído com sucesso!");
        } else {
            System.out.println("Não foi possível excluir o registro.");
        }
    }

}
