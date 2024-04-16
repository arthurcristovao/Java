package main;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import cliente.*;
import carro.*;
import aluguel.*;
import avaliacao.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            showMainMenuOptions();
            option = getUserOption(scanner);

            switch (option) {
                case 1:
                    clientesMenu(scanner);
                    break;
                case 2:
                    carrosMenu(scanner);
                    break;
                case 3:
                    alugueisMenu(scanner);
                    break;
                case 4:
                    avaliacoesMenu(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }

    private static void showMainMenuOptions() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cliente");
        System.out.println("2. Carro");
        System.out.println("3. Aluguel");
        System.out.println("4. Avaliacao");
        System.out.println("0. Sair");
    }

    private static int getUserOption(Scanner scanner) {
        int option = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                option = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número válido.");
            }
        }
        return option;
    }
    
    private static void clientesMenu(Scanner scanner) {
        int option;
        ClienteDAO clienteDAO = new ClienteDAO();

        do {
            showClientesMenuOptions();
            option = getUserOption(scanner);

            switch (option) {
                case 1:
                    // Operação de inserção de Cliente
                    System.out.println("Operação de inserção de Cliente");
                    System.out.println("Insira o nome do cliente:");
                    String nome = scanner.nextLine();
                    System.out.println("Insira o endereço do cliente:");
                    String endereco = scanner.nextLine();
                    System.out.println("Insira o telefone do cliente:");
                    String telefone = scanner.nextLine();
                    Cliente newCliente = new Cliente(nome, endereco, telefone);
                    int rowCount = clienteDAO.insert(newCliente);
                    if (rowCount > 0) {
                        System.out.println("Cliente inserido com sucesso!");
                    } else {
                        System.out.println("Falha ao inserir o cliente.");
                    }
                    break;
                case 2:
                    // Operação de leitura de Cliente
                    System.out.println("Operação de leitura de Cliente");
                    System.out.println("Insira o ID do cliente:");
                    int clienteId = Integer.parseInt(scanner.nextLine());
                    Cliente readCliente = clienteDAO.read(clienteId);
                    if (readCliente != null) {
                        System.out.println("Cliente lido: " + readCliente.getNome() + ", " + readCliente.getEndereco() + ", " + readCliente.getTelefone());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 3:
                    // Operação de atualização de Cliente
                    System.out.println("Operação de atualização de Cliente");
                    System.out.println("Insira o ID do cliente que deseja atualizar:");
                    int clienteIdForUpdate = Integer.parseInt(scanner.nextLine());

                    Cliente clienteToUpdate = clienteDAO.read(clienteIdForUpdate);
                    if (clienteToUpdate != null) {
                        System.out.println("Insira o novo nome do cliente:");
                        String newNome = scanner.nextLine();
                        System.out.println("Insira o novo endereço do cliente:");
                        String newEndereco = scanner.nextLine();
                        System.out.println("Insira o novo telefone do cliente:");
                        String newTelefone = scanner.nextLine();

                        clienteToUpdate.setNome(newNome);
                        clienteToUpdate.setEndereco(newEndereco);
                        clienteToUpdate.setTelefone(newTelefone);

                        int updateCount = clienteDAO.update(clienteToUpdate);
                        if (updateCount > 0) {
                            System.out.println("Cliente atualizado com sucesso!");
                        } else {
                            System.out.println("Falha ao atualizar o cliente.");
                        }
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 4:
                    // Operação de exclusão de Cliente
                    System.out.println("Operação de exclusão de Cliente");
                    System.out.println("Insira o ID do cliente que deseja excluir:");
                    int clienteIdForDeletion = Integer.parseInt(scanner.nextLine());

                    int deleteCount = clienteDAO.delete(clienteIdForDeletion);
                    if (deleteCount > 0) {
                        System.out.println("Cliente excluído com sucesso!");
                    } else {
                        System.out.println("Falha ao excluir o cliente.");
                    }
                    break;
                case 5:
                    // Operação de listagem de Clientes
                    List<Cliente> clientesList = clienteDAO.list();
                    if (!clientesList.isEmpty()) {
                        System.out.println("Listagem de Clientes:");
                        for (Cliente cliente : clientesList) {
                            System.out.println("ID: " + cliente.getIdCliente() + ", Nome: " + cliente.getNome() + ", Endereço: " + cliente.getEndereco() + ", Telefone: " + cliente.getTelefone());
                        }
                    } else {
                        System.out.println("Nenhum cliente encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 0);
    }
    
    private static void showClientesMenuOptions() {
        System.out.println("\nMenu de Cliente:");
        System.out.println("1. Inserir");
        System.out.println("2. Ler");
        System.out.println("3. Atualizar");
        System.out.println("4. Deletar");
        System.out.println("5. Listar");
        System.out.println("0. Voltar");
    }
    
    private static void carrosMenu(Scanner scanner) {
        int option;
        CarroDAO carroDAO = new CarroDAO();

        do {
            showCarrosMenuOptions();
            option = getUserOption(scanner);

            switch (option) {
                case 1:
                    // Operação de inserção de Carro
                    System.out.println("Operação de inserção de Carro");
                    System.out.println("Insira a marca do carro:");
                    String marca = scanner.nextLine();
                    System.out.println("Insira o modelo do carro:");
                    String modelo = scanner.nextLine();
                    System.out.println("Insira o ano do carro:");
                    int ano = Integer.parseInt(scanner.nextLine());
                    System.out.println("O carro está disponível? (true/false):");
                    boolean disponivel = Boolean.parseBoolean(scanner.nextLine());

                    Carro newCarro = new Carro(marca, modelo, ano, disponivel);
                    int rowCount = carroDAO.insert(newCarro);
                    if (rowCount > 0) {
                        System.out.println("Carro inserido com sucesso!");
                    } else {
                        System.out.println("Falha ao inserir o carro.");
                    }
                    break;
                case 2:
                    // Operação de leitura de Carro
                    System.out.println("Operação de leitura de Carro");
                    System.out.println("Insira o ID do carro:");
                    int carroId = Integer.parseInt(scanner.nextLine());
                    Carro readCarro = carroDAO.read(carroId);
                    if (readCarro != null) {
                        System.out.println("Carro lido: " + readCarro.getMarca() + ", " + readCarro.getModelo() + ", " + readCarro.getAno() + ", Disponível: " + readCarro.isDisponivel());
                    } else {
                        System.out.println("Carro não encontrado.");
                    }
                    break;
                case 3:
                    // Operação de atualização de Carro
                    System.out.println("Operação de atualização de Carro");
                    System.out.println("Insira o ID do carro que deseja atualizar:");
                    int carroIdForUpdate = Integer.parseInt(scanner.nextLine());

                    Carro carroToUpdate = carroDAO.read(carroIdForUpdate);
                    if (carroToUpdate != null) {
                        System.out.println("Insira a nova marca do carro:");
                        String newMarca = scanner.nextLine();
                        System.out.println("Insira o novo modelo do carro:");
                        String newModelo = scanner.nextLine();
                        System.out.println("Insira o novo ano do carro:");
                        int newAno = Integer.parseInt(scanner.nextLine());
                        System.out.println("O carro está disponível? (true/false):");
                        boolean newDisponivel = Boolean.parseBoolean(scanner.nextLine());

                        carroToUpdate.setMarca(newMarca);
                        carroToUpdate.setModelo(newModelo);
                        carroToUpdate.setAno(newAno);
                        carroToUpdate.setDisponivel(newDisponivel);

                        int updateCount = carroDAO.update(carroToUpdate);
                        if (updateCount > 0) {
                            System.out.println("Carro atualizado com sucesso!");
                        } else {
                            System.out.println("Falha ao atualizar o carro.");
                        }
                    } else {
                        System.out.println("Carro não encontrado.");
                    }
                    break;
                case 4:
                    // Operação de exclusão de Carro
                    System.out.println("Operação de exclusão de Carro");
                    System.out.println("Insira o ID do carro que deseja excluir:");
                    int carroIdForDeletion = Integer.parseInt(scanner.nextLine());

                    int deleteCount = carroDAO.delete(carroIdForDeletion);
                    if (deleteCount > 0) {
                        System.out.println("Carro excluído com sucesso!");
                    } else {
                        System.out.println("Falha ao excluir o carro.");
                    }
                    break;
                case 5:
                    // Operação de listagem de Carros
                    List<Carro> carrosList = carroDAO.list();
                    if (!carrosList.isEmpty()) {
                        System.out.println("Listagem de Carros:");
                        for (Carro carro : carrosList) {
                            System.out.println("ID: " + carro.getIdCarro() + ", Marca: " + carro.getMarca() + ", Modelo: " + carro.getModelo() + ", Ano: " + carro.getAno() + ", Disponível: " + carro.isDisponivel());
                        }
                    } else {
                        System.out.println("Nenhum carro encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 0);
    }

    private static void showCarrosMenuOptions() {
        System.out.println("\nMenu de Carro:");
        System.out.println("1. Inserir");
        System.out.println("2. Ler");
        System.out.println("3. Atualizar");
        System.out.println("4. Deletar");
        System.out.println("5. Listar");
        System.out.println("0. Voltar");
    }
    
    private static void alugueisMenu(Scanner scanner) {
        int option;
        AluguelDAO aluguelDAO = new AluguelDAO();

        do {
            showAlugueisMenuOptions();
            option = getUserOption(scanner);

            switch (option) {
                case 1:
                    // Operação de inserção de Aluguel
                    System.out.println("Operação de inserção de Aluguel");
                    System.out.println("Insira o ID do Cliente:");
                    int clienteId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o ID do Carro:");
                    int carroId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira a data de aluguel (Formato: AAAA-MM-DD):");
                    LocalDate dataAluguel = LocalDate.parse(scanner.nextLine());
                    System.out.println("Insira a data de devolução (Formato: AAAA-MM-DD):");
                    LocalDate dataDevolucao = LocalDate.parse(scanner.nextLine());

                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(clienteId);
                    Carro carro = new Carro();
                    carro.setIdCarro(carroId);

                    Aluguel newAluguel = new Aluguel(cliente, carro, dataAluguel, dataDevolucao);
                    int rowCount = aluguelDAO.insert(newAluguel);
                    if (rowCount > 0) {
                        System.out.println("Aluguel inserido com sucesso!");
                    } else {
                        System.out.println("Falha ao inserir o aluguel.");
                    }
                    break;
                case 2:
                    // Operação de leitura de Aluguel
                    System.out.println("Operação de leitura de Aluguel");
                    System.out.println("Insira o ID do aluguel:");
                    int aluguelId = Integer.parseInt(scanner.nextLine());
                    Aluguel readAluguel = aluguelDAO.read(aluguelId);
                    if (readAluguel != null) {
                        System.out.println("Aluguel lido: Cliente ID: " + readAluguel.getCliente().getIdCliente() +
                                ", Carro ID: " + readAluguel.getCarro().getIdCarro() +
                                ", Data de Aluguel: " + readAluguel.getDataAluguel() +
                                ", Data de Devolução: " + readAluguel.getDataDevolucao());
                    } else {
                        System.out.println("Aluguel não encontrado.");
                    }
                    break;
                case 3:
                    // Operação de atualização de Aluguel
                    System.out.println("Operação de atualização de Aluguel");
                    System.out.println("Insira o ID do aluguel que deseja atualizar:");
                    int aluguelIdForUpdate = Integer.parseInt(scanner.nextLine());

                    Aluguel aluguelToUpdate = aluguelDAO.read(aluguelIdForUpdate);
                    if (aluguelToUpdate != null) {
                        System.out.println("Insira o novo ID do Cliente:");
                        int newClienteId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Insira o novo ID do Carro:");
                        int newCarroId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Insira a nova data de aluguel (Formato: YYYY-MM-DD):");
                        LocalDate newDataAluguel = LocalDate.parse(scanner.nextLine());
                        System.out.println("Insira a nova data de devolução (Formato: YYYY-MM-DD):");
                        LocalDate newDataDevolucao = LocalDate.parse(scanner.nextLine());

                        Cliente newCliente = new Cliente();
                        newCliente.setIdCliente(newClienteId);
                        Carro newCarro = new Carro();
                        newCarro.setIdCarro(newCarroId);

                        aluguelToUpdate.setCliente(newCliente);
                        aluguelToUpdate.setCarro(newCarro);
                        aluguelToUpdate.setDataAluguel(newDataAluguel);
                        aluguelToUpdate.setDataDevolucao(newDataDevolucao);

                        int updateCount = aluguelDAO.update(aluguelToUpdate);
                        if (updateCount > 0) {
                            System.out.println("Aluguel atualizado com sucesso!");
                        } else {
                            System.out.println("Falha ao atualizar o aluguel.");
                        }
                    } else {
                        System.out.println("Aluguel não encontrado.");
                    }
                    break;
                case 4:
                    // Operação de exclusão de Aluguel
                    System.out.println("Operação de exclusão de Aluguel");
                    System.out.println("Insira o ID do aluguel que deseja excluir:");
                    int aluguelIdForDeletion = Integer.parseInt(scanner.nextLine());

                    int deleteCount = aluguelDAO.delete(aluguelIdForDeletion);
                    if (deleteCount > 0) {
                        System.out.println("Aluguel excluído com sucesso!");
                    } else {
                        System.out.println("Falha ao excluir o aluguel.");
                    }
                    break;
                case 5:
                    // Operação de listagem de Alugueis
                    List<Aluguel> alugueisList = aluguelDAO.list();
                    if (!alugueisList.isEmpty()) {
                        System.out.println("Listagem de Alugueis:");
                        for (Aluguel aluguel : alugueisList) {
                            System.out.println("ID: " + aluguel.getIdAluguel() +
                                    ", Cliente ID: " + aluguel.getCliente().getIdCliente() +
                                    ", Carro ID: " + aluguel.getCarro().getIdCarro() +
                                    ", Data de Aluguel: " + aluguel.getDataAluguel() +
                                    ", Data de Devolução: " + aluguel.getDataDevolucao());
                        }
                    } else {
                        System.out.println("Nenhum aluguel encontrado.");
                    }
                    break;
                case 6:
                    // Consultar aluguéis de um cliente pelo ID
                    System.out.println("Consultar aluguéis de um cliente pelo ID");
                    System.out.println("Insira o ID do Cliente:");
                    int consultaClienteId = Integer.parseInt(scanner.nextLine());
                    List<Aluguel> alugueisCliente = aluguelDAO.listByCliente(consultaClienteId);
                    if (!alugueisCliente.isEmpty()) {
                        System.out.println("Aluguéis do Cliente ID " + consultaClienteId + ":");
                        for (Aluguel aluguel : alugueisCliente) {
                            System.out.println("ID: " + aluguel.getIdAluguel() +
                                    ", Carro ID: " + aluguel.getCarro().getIdCarro() +
                                    ", Data de Aluguel: " + aluguel.getDataAluguel() +
                                    ", Data de Devolução: " + aluguel.getDataDevolucao());
                        }
                    } else {
                        System.out.println("Nenhum aluguel encontrado para o Cliente ID " + consultaClienteId + ".");
                    }
                    break;
                case 7:
                    // Consultar aluguéis de um carro pelo ID
                    System.out.println("Consultar aluguéis de um carro pelo ID");
                    System.out.println("Insira o ID do Carro:");
                    int consultaCarroId = Integer.parseInt(scanner.nextLine());
                    List<Aluguel> alugueisCarro = aluguelDAO.listByCarro(consultaCarroId);
                    if (!alugueisCarro.isEmpty()) {
                        System.out.println("Aluguéis do Carro ID " + consultaCarroId + ":");
                        for (Aluguel aluguel : alugueisCarro) {
                            System.out.println("ID: " + aluguel.getIdAluguel() +
                                    ", Cliente ID: " + aluguel.getCliente().getIdCliente() +
                                    ", Data de Aluguel: " + aluguel.getDataAluguel() +
                                    ", Data de Devolução: " + aluguel.getDataDevolucao());
                        }
                    } else {
                        System.out.println("Nenhum aluguel encontrado para o Carro ID " + consultaCarroId + ".");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 0);
    }

    private static void showAlugueisMenuOptions() {
        System.out.println("\nMenu de Aluguel:");
        System.out.println("1. Inserir");
        System.out.println("2. Ler");
        System.out.println("3. Atualizar");
        System.out.println("4. Deletar");
        System.out.println("5. Listar");
        System.out.println("6. Consultar aluguéis de um cliente pelo ID");
        System.out.println("7. Consultar aluguéis de um carro pelo ID");
        System.out.println("0. Voltar");
    }
    
    private static void avaliacoesMenu(Scanner scanner) {
        int option;
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        do {
            showAvaliacoesMenuOptions();
            option = getUserOption(scanner);

            switch (option) {
                case 1:
                    // Operação de inserção de Avaliacao
                    System.out.println("Operação de inserção de Avaliacao");
                    System.out.println("Insira o ID do Aluguel:");
                    int aluguelId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira a nota:");
                    int nota = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o comentário:");
                    String comentario = scanner.nextLine();

                    Avaliacao newAvaliacao = new Avaliacao();
                    newAvaliacao.setAluguel(new Aluguel(aluguelId)); // Crie um construtor em Aluguel para aceitar apenas o ID
                    newAvaliacao.setNota(nota);
                    newAvaliacao.setComentario(comentario);

                    int rowCount = avaliacaoDAO.insert(newAvaliacao);
                    if (rowCount > 0) {
                        System.out.println("Avaliação inserida com sucesso!");
                    } else {
                        System.out.println("Falha ao inserir a avaliação.");
                    }
                    break;
                case 2:
                    // Operação de leitura de Avaliacao
                    System.out.println("Operação de leitura de Avaliacao");
                    System.out.println("Insira o ID da avaliação:");
                    int avaliacaoId = Integer.parseInt(scanner.nextLine());
                    Avaliacao readAvaliacao = avaliacaoDAO.read(avaliacaoId);
                    if (readAvaliacao != null) {
                        System.out.println("Avaliação lida: Aluguel ID: " + readAvaliacao.getAluguel().getIdAluguel() +
                                ", Nota: " + readAvaliacao.getNota() +
                                ", Comentário: " + readAvaliacao.getComentario());
                    } else {
                        System.out.println("Avaliação não encontrada.");
                    }
                    break;
                case 3:
                    // Operação de atualização de Avaliacao
                    System.out.println("Operação de atualização de Avaliacao");
                    System.out.println("Insira o ID da avaliação que deseja atualizar:");
                    int avaliacaoIdForUpdate = Integer.parseInt(scanner.nextLine());

                    Avaliacao avaliacaoToUpdate = avaliacaoDAO.read(avaliacaoIdForUpdate);
                    if (avaliacaoToUpdate != null) {
                        System.out.println("Insira o novo ID do Aluguel:");
                        int newAluguelId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Insira a nova nota:");
                        int newNota = Integer.parseInt(scanner.nextLine());
                        System.out.println("Insira o novo comentário:");
                        String newComentario = scanner.nextLine();

                        avaliacaoToUpdate.setAluguel(new Aluguel(newAluguelId));
                        avaliacaoToUpdate.setNota(newNota);
                        avaliacaoToUpdate.setComentario(newComentario);

                        int updateCount = avaliacaoDAO.update(avaliacaoToUpdate);
                        if (updateCount > 0) {
                            System.out.println("Avaliação atualizada com sucesso!");
                        } else {
                            System.out.println("Falha ao atualizar a avaliação.");
                        }
                    } else {
                        System.out.println("Avaliação não encontrada.");
                    }
                    break;
                case 4:
                    // Operação de exclusão de Avaliacao
                    System.out.println("Operação de exclusão de Avaliacao");
                    System.out.println("Insira o ID da avaliação que deseja excluir:");
                    int avaliacaoIdForDeletion = Integer.parseInt(scanner.nextLine());

                    int deleteCount = avaliacaoDAO.delete(avaliacaoIdForDeletion);
                    if (deleteCount > 0) {
                        System.out.println("Avaliação excluída com sucesso!");
                    } else {
                        System.out.println("Falha ao excluir a avaliação.");
                    }
                    break;
                case 5:
                    // Operação de listagem de Avaliacoes
                    List<Avaliacao> avaliacoesList = avaliacaoDAO.list();
                    if (!avaliacoesList.isEmpty()) {
                        System.out.println("Listagem de Avaliações:");
                        for (Avaliacao avaliacao : avaliacoesList) {
                            System.out.println("ID: " + avaliacao.getIdAvaliacao() +
                                    ", Aluguel ID: " + avaliacao.getAluguel().getIdAluguel() +
                                    ", Nota: " + avaliacao.getNota() +
                                    ", Comentário: " + avaliacao.getComentario());
                        }
                    } else {
                        System.out.println("Nenhuma avaliação encontrada.");
                    }
                    break;
                case 6:
                    // Operação de pesquisa de Avaliacoes por Cliente ID
                    System.out.println("Operação de pesquisa de Avaliações por Cliente ID");
                    System.out.println("Insira o ID do Cliente:");
                    int clienteIdForAvaliacoes = Integer.parseInt(scanner.nextLine());
                    List<Avaliacao> avaliacoesByClienteId = avaliacaoDAO.listByClienteId(clienteIdForAvaliacoes);
                    if (!avaliacoesByClienteId.isEmpty()) {
                        System.out.println("Avaliações do Cliente ID " + clienteIdForAvaliacoes + ":");
                        for (Avaliacao avaliacao : avaliacoesByClienteId) {
                            System.out.println("ID: " + avaliacao.getIdAvaliacao() +
                                    ", Aluguel ID: " + avaliacao.getAluguel().getIdAluguel() +
                                    ", Nota: " + avaliacao.getNota() +
                                    ", Comentário: " + avaliacao.getComentario());
                        }
                    } else {
                        System.out.println("Nenhuma avaliação encontrada para o Cliente ID " + clienteIdForAvaliacoes + ".");
                    }
                    break;
                case 7:
                    // Operação de pesquisa de Avaliacoes por Carro ID
                    System.out.println("Operação de pesquisa de Avaliações por Carro ID");
                    System.out.println("Insira o ID do Carro:");
                    int carroIdForAvaliacoes = Integer.parseInt(scanner.nextLine());
                    List<Avaliacao> avaliacoesByCarroId = avaliacaoDAO.listByCarroId(carroIdForAvaliacoes);
                    if (!avaliacoesByCarroId.isEmpty()) {
                        System.out.println("Avaliações do Carro ID " + carroIdForAvaliacoes + ":");
                        for (Avaliacao avaliacao : avaliacoesByCarroId) {
                            System.out.println("ID: " + avaliacao.getIdAvaliacao() +
                                    ", Aluguel ID: " + avaliacao.getAluguel().getIdAluguel() +
                                    ", Nota: " + avaliacao.getNota() +
                                    ", Comentário: " + avaliacao.getComentario());
                        }
                    } else {
                        System.out.println("Nenhuma avaliação encontrada para o Carro ID " + carroIdForAvaliacoes + ".");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 0);
    }

    private static void showAvaliacoesMenuOptions() {
        System.out.println("\nMenu de Avaliação:");
        System.out.println("1. Inserir");
        System.out.println("2. Ler");
        System.out.println("3. Atualizar");
        System.out.println("4. Deletar");
        System.out.println("5. Listar");
        System.out.println("6. Pesquisar por Cliente ID");
        System.out.println("7. Pesquisar por Carro ID");
        System.out.println("0. Voltar");
    }

}