package pacote;

import java.util.List;
import java.util.Scanner;

public class AppFuncionario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();

        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Atualizar Funcionário");
            System.out.println("3. Remover Funcionário");
            System.out.println("4. Buscar Funcionário por ID");
            System.out.println("5. Listar Funcionários");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do funcionário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o cargo do funcionário: ");
                    String cargo = scanner.nextLine();
                    System.out.print("Digite o salário do funcionário: ");
                    double salario = scanner.nextDouble();

                    Funcionario novoFuncionario = new Funcionario(0, nome, cargo, salario);
                    funcionarioDAO.adicionarFuncionario(novoFuncionario);
                    System.out.println("Funcionário adicionado com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o ID do funcionário a ser atualizado: ");
                    int idAtualizacao = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    Funcionario funcionarioExistente = funcionarioDAO.buscarFuncionarioPorId(idAtualizacao);

                    if (funcionarioExistente != null) {
                        System.out.print("Digite o novo nome do funcionário: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Digite o novo cargo do funcionário: ");
                        String novoCargo = scanner.nextLine();
                        System.out.print("Digite o novo salário do funcionário: ");
                        double novoSalario = scanner.nextDouble();

                        funcionarioExistente.setNome(novoNome);
                        funcionarioExistente.setCargo(novoCargo);
                        funcionarioExistente.setSalario(novoSalario);

                        funcionarioDAO.atualizarFuncionario(funcionarioExistente);
                        System.out.println("Funcionário atualizado com sucesso!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do funcionário a ser removido: ");
                    int idRemocao = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    Funcionario funcionarioParaRemover = funcionarioDAO.buscarFuncionarioPorId(idRemocao);

                    if (funcionarioParaRemover != null) {
                        funcionarioDAO.removerFuncionario(idRemocao);
                        System.out.println("Funcionário removido com sucesso!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do funcionário a ser buscado: ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    Funcionario funcionarioBuscado = funcionarioDAO.buscarFuncionarioPorId(idBusca);

                    if (funcionarioBuscado != null) {
                        System.out.println("Funcionário encontrado: " + funcionarioBuscado);
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;
                case 5:
                    List<Funcionario> funcionarios = funcionarioDAO.listarFuncionarios();
                    for (Funcionario funcionario : funcionarios) {
                        System.out.println(funcionario);
                    }
                    break;
                case 6:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
