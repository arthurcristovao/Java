package main;

import titles.*;
import authors.*;
import authortitle.*;
import java.util.Scanner;
import java.util.List;


public class Aula14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            showMainMenuOptions();
            option = getUserOption(scanner);

            switch (option) {
                case 1:
                    authorsMenu(scanner);
                    break;
                case 2:
                    titlesMenu(scanner);
                    break;
                case 3:
                    authorTitleMenu(scanner);
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
        System.out.println("1. Authors");
        System.out.println("2. Titles");
        System.out.println("3. Relação Authors e Titles");
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

    private static void authorsMenu(Scanner scanner) {
        int option;
        AuthorsDAO authorsDAO = new AuthorsDAO();

        do {
            showAuthorsMenuOptions();
            option = getUserOption(scanner);

            switch (option) {
                case 1:
                    System.out.println("Operação de inserção de Authors");
                    System.out.println("Insira o primeiro nome do autor:");
                    String firstName = scanner.nextLine();
                    System.out.println("Insira o sobrenome do autor:");
                    String lastName = scanner.nextLine();
                    Authors newAuthor = new Authors(firstName, lastName);
                    int rowCount = authorsDAO.insert(newAuthor);
                    if (rowCount > 0) {
                        System.out.println("Autor inserido com sucesso!");
                    } else {
                        System.out.println("Falha ao inserir o autor.");
                    }
                    break;
                case 2:
                    System.out.println("Operação de leitura de Authors");
                    System.out.println("Insira o ID do autor:");
                    int authorId = Integer.parseInt(scanner.nextLine());
                    Authors readAuthor = authorsDAO.read(authorId);
                    if (readAuthor != null) {
                        System.out.println("Autor lido: " + readAuthor.getFirstName() + " " + readAuthor.getLastName());
                    } else {
                        System.out.println("Autor não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Operação de atualização de Authors");
                    System.out.println("Insira o ID do autor que deseja atualizar:");
                    int authorIdForUpdate = Integer.parseInt(scanner.nextLine());

                    Authors authorToUpdate = authorsDAO.read(authorIdForUpdate);
                    if (authorToUpdate != null) {
                        System.out.println("Insira o novo primeiro nome do autor:");
                        String newFirstName = scanner.nextLine();
                        System.out.println("Insira o novo sobrenome do autor:");
                        String newLastName = scanner.nextLine();

                        authorToUpdate.setFirstName(newFirstName);
                        authorToUpdate.setLastName(newLastName);

                        int updateCount = authorsDAO.update(authorToUpdate);
                        if (updateCount > 0) {
                            System.out.println("Autor atualizado com sucesso!");
                        } else {
                            System.out.println("Falha ao atualizar o autor.");
                        }
                    } else {
                        System.out.println("Autor não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Operação de exclusão de Authors");
                    System.out.println("Insira o ID do autor que deseja excluir:");
                    int authorIdForDeletion = Integer.parseInt(scanner.nextLine());

                    int deleteCount = authorsDAO.delete(authorIdForDeletion);
                    if (deleteCount > 0) {
                        System.out.println("Autor excluído com sucesso!");
                    } else {
                        System.out.println("Falha ao excluir o autor.");
                    }
                    break;
                case 5:
                    // Operação de listagem de Authors
                    List<Authors> authorsList = authorsDAO.list();
                    if (!authorsList.isEmpty()) {
                        System.out.println("Listagem de Autores:");
                        for (Authors author : authorsList) {
                            System.out.println("ID: " + author.getAuthorsID() + " Nome: " + author.getFirstName() + " " + author.getLastName());
                        }
                    } else {
                        System.out.println("Nenhum autor encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (true);
    }

    private static void showAuthorsMenuOptions() {
        System.out.println("\nMenu de Authors:");
        System.out.println("1. Inserir");
        System.out.println("2. Ler");
        System.out.println("3. Atualizar");
        System.out.println("4. Deletar");
        System.out.println("5. Listar");
        System.out.println("0. Voltar");
    }

    private static void titlesMenu(Scanner scanner) {
        int option;
        TitlesDAO titlesDAO = new TitlesDAO();

        do {
            showTitlesMenuOptions();
            option = getUserOption(scanner);

            switch (option) {
                case 1:
                    System.out.println("Operação de inserção de Titles");
                    System.out.println("Insira o ISBN do título:");
                    int isbn = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o título:");
                    String title = scanner.nextLine();
                    System.out.println("Insira o número da edição:");
                    String editionNumber = scanner.nextLine();
                    System.out.println("Insira os direitos autorais:");
                    String copyright = scanner.nextLine();

                    Titles newTitle = new Titles(isbn, title, editionNumber, copyright);
                    titlesDAO.insert(newTitle);
                    break;
                case 2:
                    System.out.println("Operação de leitura de Titles");
                    System.out.println("Insira o ISBN do título:");
                    int titleISBN = Integer.parseInt(scanner.nextLine());
                    Titles readTitle = titlesDAO.read(titleISBN);
                    if (readTitle != null) {
                        System.out.println("Título lido: " + readTitle.getTitle());
                    } else {
                        System.out.println("Título não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Operação de atualização de Titles");
                    System.out.println("Insira o ISBN do título que deseja atualizar:");
                    int titleISBNForUpdate = Integer.parseInt(scanner.nextLine());

                    Titles titleToUpdate = titlesDAO.read(titleISBNForUpdate);
                    if (titleToUpdate != null) {
                        System.out.println("Insira o novo título:");
                        String newTitlee = scanner.nextLine();
                        System.out.println("Insira o novo número da edição:");
                        String newEditionNumber = scanner.nextLine();
                        System.out.println("Insira o novo ano de direitos autorais:");
                        String newCopyright = scanner.nextLine();

                        titleToUpdate.setTitle(newTitlee);
                        titleToUpdate.setEditionNumber(newEditionNumber);
                        titleToUpdate.setCopyright(newCopyright);

                        titlesDAO.update(titleToUpdate);
                        System.out.println("Título atualizado com sucesso!");
                    } else {
                        System.out.println("Título não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Operação de exclusão de Titles");
                    System.out.println("Insira o ISBN do título que deseja excluir:");
                    int titleISBNForDeletion = Integer.parseInt(scanner.nextLine());

                    titlesDAO.delete(titleISBNForDeletion);
                    System.out.println("Título excluído com sucesso!");
                    break;
                case 5:
                    // Operação de listagem de Titles
                    List<Titles> titlesList = titlesDAO.list();
                    if (!titlesList.isEmpty()) {
                        System.out.println("Listagem de Títulos:");
                        for (Titles titles : titlesList) {
                            System.out.println("ISBN: " + titles.getISBN() + ", Título: " + titles.getTitle());
                        }
                    } else {
                        System.out.println("Nenhum título encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (true);
    }

    private static void showTitlesMenuOptions() {
        System.out.println("\nMenu de Titles:");
        System.out.println("1. Inserir");
        System.out.println("2. Ler");
        System.out.println("3. Atualizar");
        System.out.println("4. Deletar");
        System.out.println("5. Listar");
        System.out.println("0. Voltar");
    }
    
    private static void authorTitleMenu(Scanner scanner) {
        int option;
        AuthorTitleDAO authorTitleDAO = new AuthorTitleDAO();

        do {
            showAuthorTitleMenuOptions();
            option = getUserOption(scanner);

            switch (option) {
                case 1:
                    System.out.println("Associar Autor ao Título");
                    System.out.println("Insira o ID do Autor:");
                    int authorID = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o ISBN do Título:");
                    int titleISBN = Integer.parseInt(scanner.nextLine());

                    authorTitleDAO.associateAuthorTitle(authorID, titleISBN);
                    break;

                case 2:
                    System.out.println("Desassociar Autor do Título");
                    System.out.println("Insira o ID do Autor:");
                    int authorIDForDisassociation = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o ISBN do Título:");
                    int titleISBNForDisassociation = Integer.parseInt(scanner.nextLine());

                    authorTitleDAO.disassociateAuthorTitle(authorIDForDisassociation, titleISBNForDisassociation);
                    break;

                case 3:
                    System.out.println("Recuperar Títulos por Autor");
                    System.out.println("Insira o ID do Autor:");
                    int authorIDForTitles = Integer.parseInt(scanner.nextLine());

                    List<Titles> titlesByAuthor = authorTitleDAO.getTitlesByAuthor(authorIDForTitles);
                    if (!titlesByAuthor.isEmpty()) {
                        System.out.println("Títulos:");
                        for (Titles title : titlesByAuthor) {
                            System.out.println("ISBN: " + title.getISBN() + " Título: " + title.getTitle());
                        }
                    } else {
                        System.out.println("Nenhum título encontrado para o autor.");
                    }
                    break;

                case 4:
                    System.out.println("Recuperar Autores por Título");
                    System.out.println("Insira o ISBN do Título:");
                    int titleISBNForAuthors = Integer.parseInt(scanner.nextLine());

                    List<Authors> authorsByTitle = authorTitleDAO.getAuthorsByTitle(titleISBNForAuthors);
                    if (!authorsByTitle.isEmpty()) {
                        System.out.println("Autores:");
                        for (Authors author : authorsByTitle) {
                            System.out.println("ID: " + author.getAuthorsID() + " Nome: " + author.getFirstName() + " " + author.getLastName());
                        }
                    } else {
                        System.out.println("Nenhum autor encontrado para o título.");
                    }
                    break;

                case 0:
                    System.out.println("Retornando ao menu principal...");
                    return;

                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        } while (true);
    }

    private static void showAuthorTitleMenuOptions() {
        System.out.println("\nMenu Autor-Título:");
        System.out.println("1. Associar Autor ao Título");
        System.out.println("2. Desassociar Autor do Título");
        System.out.println("3. Recuperar Títulos por Autor");
        System.out.println("4. Recuperar Autores por Título");
        System.out.println("0. Voltar");
    }
}