package trabalhooficinamecanica;

import caixas.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import pessoa.*;
import produto.*;
import servico.*;
import veiculo.*;;

public class TrabalhoOficinaMecanica {
    private static String condicao;
    
    public static void main(String[] args) {
        addDados();
        new JanelaPrincipal();
    };

    public static void addDados(){
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dataFormatada = dataAtual.format(formatter);
        LocalDate dataSemBarras = LocalDate.parse(dataFormatada, formatter);
        
        //FUNCIONARIO
        Funcionario f1 = new Funcionario(Cargo.MECANICO, 1200,  "JOSÉ", "123365980", dataSemBarras, "123 456 789");
        Funcionario f2 = new Funcionario(Cargo.ATENDENTE, 1200,  "MARIA", "123365981", dataSemBarras, "123 456 789");
        Funcionario f3 = new Funcionario(Cargo.MECANICO, 1200,  "GABRIEL", "123365982", dataSemBarras, "123 456 789");
        Funcionario f4 = new Funcionario(Cargo.ATENDENTE, 1200,  "RAFA", "1233659843", dataSemBarras, "123 456 789");
        FuncionarioDAO funDAO = new FuncionarioDAO();
        funDAO.inserir(f1); funDAO.inserir(f2); funDAO.inserir(f3); funDAO.inserir(f4);
        funDAO.setFunDAO(funDAO);

        //CARRO
        Carro c1 = new Carro(4, true, "FORD", "KA", 2019, "AAA2019", Cor.PRATA);
        Carro c2 = new Carro(4, false, "FORD", "FIESTA", 2009, "AAA2009", Cor.AMARELO);
        Carro c3 = new Carro(2, true, "FIAT", "MOBI", 2023, "AAA2023", Cor.BRANCO);
        Carro c4 = new Carro(4, true, "FORD", "KA", 2019, "AAA2018", Cor.PRATA);
        Carro c5 = new Carro(4, false, "FORD", "FIESTA", 2009, "AAA2008", Cor.AMARELO);
        Carro c6 = new Carro(2, true, "FIAT", "MOBI", 2023, "AAA2022", Cor.BRANCO);
        veiculo.CarroDAO carDAO = new veiculo.CarroDAO();
        carDAO.inserir(c1); carDAO.inserir(c2); carDAO.inserir(c3);
        carDAO.inserir(c4); carDAO.inserir(c5); carDAO.inserir(c6);
        carDAO.setCarroDAO(carDAO);

        //CLIENTE
        Cliente cl1 = new Cliente(LocalDate.now(), c1, "Jorge", "123", dataSemBarras, "5199999999");
        Cliente cl2 = new Cliente(LocalDate.now(), c2, "Joana", "456", dataSemBarras, "5188888888");
        Cliente cl3 = new Cliente(LocalDate.now(), c3, "Jasques", "789", dataSemBarras, "5177777777");
        pessoa.ClienteDAO clienteDAO = new pessoa.ClienteDAO();
        clienteDAO.inserir(cl1);
        clienteDAO.inserir(cl2);
        clienteDAO.inserir(cl3);

        //PRODUTO
        Produto p1 = new Produto("Parafuso", "ciser", "Aperto", "un", 2018, 2.0);
        Produto p2 = new Produto("Porca", "ciser", "Aperto", "un",  2018, 1.0);
        Produto p3 = new Produto("Arruela", "ciser", "Aperto", "un", 2018, 0.5);
        produto.ProdutoDAO produtoDAO = new produto.ProdutoDAO();
        produtoDAO.inserir(p1);
        produtoDAO.inserir(p2);
        produtoDAO.inserir(p3);

        //SERVICO
        servico.ServicoDAO servicoDAO = new servico.ServicoDAO();
        servicoDAO.inserir(new Servico(dataSemBarras, cl1, f2, f1, p1, 1.0));
        servicoDAO.inserir(new Servico(dataSemBarras, cl2, f4, f3, p2, 2.0));

    }
    
    public static String getCondicao(){
        return condicao;
    }
    
    public static void setCondicao(String condicao){
        TrabalhoOficinaMecanica.condicao = condicao;
    }
}
