package caixas;

import trabalhooficinamecanica.TrabalhoOficinaMecanica;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import lib.*;
import pessoa.*;
import produto.*;
import veiculo.*;

public class JanelaPrincipal extends JFrame {
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    private final int windowWidth50 = windowWidth / 2;
    private final int containerWidth = 200;
    private final int containerEspaco = (windowWidth - containerWidth*2)/3;
    private final int containerEspaco2 = containerEspaco*2 + containerWidth;
    
    
    public JanelaPrincipal (){
        
        setTitle("Oficina Mecânica Parafuso Solto --> Arthur Cristovão e Lucas Weber  --> IFRS/POOI");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajusta o tadmanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela
        
        TrabalhoOficinaMecanica.setCondicao("");
        
        
        Etiqueta titulo = new Etiqueta("Oficina Mecânica Parafuso Solto", 0, 50, windowWidth, 30, 25, SwingConstants.CENTER);
        add(titulo);

        Etiqueta subTitulo = new Etiqueta("Arthur Cristovão e Lucas Weber", 0, 80, windowWidth, 30, 12, SwingConstants.CENTER);
        add(subTitulo);
        
        //COLUNA 1
        
        Etiqueta cadastro = new Etiqueta("Cadastro", containerEspaco, 150, containerWidth, 30, 20, SwingConstants.CENTER);
        add(cadastro);
        
        Botao carro = new Botao("Carro", containerEspaco, 220, containerWidth, 50, 18); 
        add(carro);
        
        carro.addActionListener((ActionEvent e) -> {
            new JanelaCarro();
            dispose();
        });
        
        
        Botao funcionario = new Botao("Funcionário", containerEspaco, 300, containerWidth, 50, 18); 
        add(funcionario);
        
        funcionario.addActionListener((ActionEvent e) -> {
            new JanelaFuncionario();
            dispose();
        });
        
        Botao cliente = new Botao("Cliente", containerEspaco, 380, containerWidth, 50, 18); 
        add(cliente);
        cliente.addActionListener((ActionEvent e) -> {
            new JanelaCliente();
            dispose();
        });

        Botao produto = new Botao("Produto", containerEspaco, 460, containerWidth, 50, 18); 
        add(produto);
        produto.addActionListener((ActionEvent e) -> {
            new JanelaProduto();
            dispose();
        });

        
        //COLUNA 2
        Etiqueta atividade = new Etiqueta("Atividade", containerEspaco2, 150, containerWidth, 30, 20, SwingConstants.CENTER);
        add(atividade);
        
        Botao servico = new Botao("Serviço", containerEspaco2, 300, containerWidth, 50, 18); 
        add(servico);
        servico.addActionListener((ActionEvent e) -> {
            new JanelaServico();
            dispose();
        });
   
        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
    }
}
