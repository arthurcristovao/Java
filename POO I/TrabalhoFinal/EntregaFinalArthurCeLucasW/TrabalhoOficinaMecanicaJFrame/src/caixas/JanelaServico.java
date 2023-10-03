package caixas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import lib.*;
import lib.interfaces.*;
import pessoa.Cargo;
import pessoa.Cliente;
import pessoa.ClienteDAO;
import pessoa.Funcionario;
import pessoa.FuncionarioDAO;

import servico.Servico;
import servico.ServicoDAO;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;


public class JanelaServico extends JFrame implements BotoesUteis{
    private String condicao = TrabalhoOficinaMecanica.getCondicao();
    private String nome;
    private Botao cadastrar, listar, voltar, home, pesquisar, confirm, editar;
    private CampoInput nomeInput;
    private Lista listaCod, listaCl, listaVt;
    private JComboBox boxC, boxA, boxM;
    private Servico servico;
    private int indexListaC = -1, indexListaA = -1, indexListaM = -1;
    ClienteDAO clienteDAO = new ClienteDAO();
    ServicoDAO servicoDAO = new ServicoDAO();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private ArrayList<Servico> servicos = ServicoDAO.getArrayListServico();
    private ArrayList<Cliente> clientes = ClienteDAO.getArrayListCliente(); 
    private ArrayList<Funcionario> funcionarios = FuncionarioDAO.getArrayListFuncionario();
    private ArrayList<Integer> posicaoAtendente = new ArrayList<>();
    private ArrayList<Integer> posicaoMecanico = new ArrayList<>();

    private final int windowWidth = 1000;
    private final int windowHeight = 700;

    public JanelaServico(){
        setTitle("Serviço");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajusta o tadmanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela

        if(condicao.isEmpty()){
            principal();
        } else if(condicao.equals("Listar")){
            listar();
        } else if(condicao.equals("Cadastrar")){
            cadastrar();
        }

        //Limpa condição
        TrabalhoOficinaMecanica.setCondicao("");
        
        //BOTOES
        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaPrincipal();
            dispose();
        });
        paginaPrincipal();   
        
        //JFRAME
        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
    }

//Ferramentas -- inicio
    private void setLabel(String msgEtiqueta, int x,int y, int width, int height, int letterSize, int alinhamento){
        add(new Etiqueta(msgEtiqueta, x, y, width, height, letterSize, alinhamento));
    }
    
    private CampoInput setLabelInput(String msgEtiqueta, String msgCampoInput, int y){
        add(new Etiqueta(msgEtiqueta, 180, y, 200, 30, 18, SwingConstants.RIGHT));
        return new CampoInput(msgCampoInput, 400, y, 350, 30, 15);
    }

    private RadioButton setRadioButton(String msgRadioButton, int x,int y){
        return new RadioButton(msgRadioButton, x, y, 150, 30, 18);
    }
   
    private Botao setButton(String mensagem, int x, int y, int width, int height, int letterSize){
        return new Botao(mensagem, x, y, width, height, letterSize);
    }
//Ferramentas -- fim

    private void principal(){
        setLabel("Oficina Mecânica Parafuso Solto", 0, 50, windowWidth, 30, 25, SwingConstants.CENTER);
        setLabel("Serviço", 0, 100, windowWidth, 30, 20, SwingConstants.CENTER);
        
        add(cadastrar = setButton("Cadastrar", 350, 180, 300, 50, 18));
        add(listar = setButton("Listar", 350, 280, 300, 50, 18));
        
        cadastrar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Cadastrar");
            dispose();
            new JanelaServico();
        });
        listar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            dispose();
            new JanelaServico();
        });
    }

    private void listar(){
        setLabel("Serviço - Listar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        add(nomeInput = setLabelInput("Pesquise pelo nome cliente: ", "", 100));
        add(pesquisar = new Botao("Pesquisar", 750, 100, 120, 30, 15));
        
        DefaultListModel<String> modelCod = new DefaultListModel<>();
        DefaultListModel<String> modelCliente = new DefaultListModel<>();
        DefaultListModel<String> modelValorT = new DefaultListModel<>();

        pesquisar.addActionListener((ActionEvent e) -> {
            editar.setEnabled(false);
            editar.setCor(Color.BLACK, Color.DARK_GRAY);
            nome = nomeInput.getText();
            ArrayList<Servico> ss = servicoDAO.pesquisar(nome);
            if( modelCod != null){
                modelCod.clear();
                modelCliente.clear();
                modelValorT.clear();
                for (Servico item : ss) {
                    modelCod.addElement(Integer.toString(item.getCodServico()));
                    modelCliente.addElement(item.getCliente().getNome());
                    modelValorT.addElement(Double.toString(item.getValorTotalServico()));
                }
            }
        }); 

        for (Servico item : servicos) {
            modelCod.addElement(Integer.toString(item.getCodServico()));
            modelCliente.addElement(item.getCliente().getNome());
            modelValorT.addElement(Double.toString(item.getValorTotalServico()));
        }

        setLabel("Código", 200, 170, 200, 30, 20, SwingConstants.CENTER);
        setLabel("Cliente", 400, 170, 200, 30, 20, SwingConstants.CENTER);
        setLabel("R$ Total", 600, 170, 200, 30, 20, SwingConstants.CENTER);

        listaCod = new Lista(modelCod, 200, 200, 200, 200, 18);
        listaCl = new Lista(modelCliente, 400, 200, 200, 200, 18);
        listaVt = new Lista(modelValorT, 600, 200, 200, 200, 18);

        add(listaCod);
        add(listaCl);
        add(listaVt);

        listaCod.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaCl.setSelectedIndex(listaCod.getSelectedIndex());
                editar.setEnabled(true);
                editar.setCor(Color.BLACK, Color.YELLOW);
            }
        });

        listaCl.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaVt.setSelectedIndex(listaCl.getSelectedIndex());
            }
        });

        listaVt.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
               listaCod.setSelectedIndex(listaVt.getSelectedIndex()); 
            }
        });

        editarBotao();
        editar.addActionListener((ActionEvent e) -> {
            String id = listaCod.getSelectedValue();
            dispose();
            new JanelaServicoEditarExcluir(id);
        });
    }

    private void cadastrar(){
        setLabel("Serviço - Cadastrar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        
        setLabel("Cliente", 300, 100, 200, 30, 18, SwingConstants.CENTER);
        setLabel("Atendente", 300, 200, 200, 30, 18, SwingConstants.CENTER);
        setLabel("Mecanico", 300, 300, 200, 30, 18, SwingConstants.CENTER);
        
        DefaultComboBoxModel<String> modelCliente = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> modelAtendente = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> modelMecanico = new DefaultComboBoxModel<>();

        for (Cliente item : clientes) {
            modelCliente.addElement(item.getNome());
        }

        int i = 0;
        
        for(Funcionario item : funcionarios) {
            if(item.getCargo().equals(Cargo.ATENDENTE)){
                modelAtendente.addElement(item.getNome());
                posicaoAtendente.add(i);
            } else if(item.getCargo().equals(Cargo.MECANICO)){
                modelMecanico.addElement(item.getNome());
                posicaoMecanico.add(i); 
            }
            i++;
        }

        boxC = new ComboBox(modelCliente, 500, 100, 200, 30, 18);
        boxA = new ComboBox(modelAtendente, 500, 200, 200, 30, 18);
        boxM = new ComboBox(modelMecanico, 500, 300, 200, 30, 18);

        add(boxC);
        add(boxA);
        add(boxM);

        boxC.setSelectedIndex(-1);
        boxA.setSelectedIndex(-1);
        boxM.setSelectedIndex(-1);

        boxC.addActionListener(e -> {
            indexListaC = boxC.getSelectedIndex();
            alteracaoBotoes();
        });
        boxA.addActionListener(e -> {
            indexListaA = boxA.getSelectedIndex();
            alteracaoBotoes();
        });
        boxM.addActionListener(e -> {
            indexListaM = boxM.getSelectedIndex();
            alteracaoBotoes();
        });

        botoesBaixo();
        confirmar();
        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaServico();
            dispose();
        });
    }

    private void alteracaoBotoes(){
        if(boxC.getSelectedIndex()>=0 & boxA.getSelectedIndex()>=0 & boxM.getSelectedIndex()>=0){
            confirm.setEnabled(true);
            confirm.setCor(Color.BLACK, Color.GREEN);
        }
    }

    private void confirmar(){        
        confirm.addActionListener((ActionEvent e) -> {
            LocalDate dataServico = LocalDate.now();

            UIManager.put("OptionPane.yesButtonText", "Cadastrar");
            UIManager.put("OptionPane.noButtonText", "Cancelar");
            
            int confirmDialogResult = JOptionPane.showConfirmDialog(
                        null,
                        ("Deseja cadastrar um novo serviço?"),
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
            );
        
            if( confirmDialogResult == JOptionPane.YES_OPTION) {
                Cliente cliente = clienteDAO.getClientePorIndex(indexListaC);
                Funcionario atendente = funcionarioDAO.getFuncionarioPorIndex(posicaoAtendente.get(indexListaA));
                Funcionario mecanico = funcionarioDAO.getFuncionarioPorIndex(posicaoMecanico.get(indexListaM));

                Servico servTemp = new Servico(dataServico, cliente, atendente, mecanico);
                
                boolean retorno = servicoDAO.inserir(servTemp);
                
                if(retorno){
                    dispose();
                    new JanelaServicoEditarExcluir(Integer.toString(servTemp.getCodServico()));
                    JOptionPane.showMessageDialog(null, "O serviço '" + servTemp.getCodServico() +"' foi cadastrado!");
                } else {
                    JOptionPane.showMessageDialog(null, "O serviço não foi cadastrado!");
                }
            }
        });
    }

    @Override
    public void voltar() {
        voltar = new Botao("Voltar", 350, 550, 300, 50, 18);
        voltar.setCor(Color.BLACK, Color.LIGHT_GRAY);
        add(voltar);
    }

    private void paginaPrincipal() {
        home = new Botao("Home", 0, 0, 100, 50, 18);
        home.setCor(Color.BLACK, Color.LIGHT_GRAY);
        add(home);
        home.addActionListener((ActionEvent e) -> {
            new JanelaPrincipal();
            dispose();
        });
    }

    private void editarBotao() {
        editar = new Botao("Editar", 350, 490, 300, 50, 18);
        editar.setEnabled(false);
        editar.setCor(Color.BLACK, Color.DARK_GRAY);
        add(editar);
    }

    private void botoesBaixo() {
        confirm = new Botao("Criar", 350, 490, 300, 50, 18);
        confirm.setEnabled(false);
        confirm.setCor(Color.BLACK, Color.DARK_GRAY);
        add(confirm);
    }
}
