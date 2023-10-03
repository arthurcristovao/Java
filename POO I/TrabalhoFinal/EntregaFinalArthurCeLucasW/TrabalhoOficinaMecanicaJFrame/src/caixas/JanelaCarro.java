
package caixas;

import caixas.*;
import lib.interfaces.BotoesUteis;
import pessoa.ArCond;
import pessoa.Cargo;
import pessoa.Funcionario;
import pessoa.FuncionarioDAO;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.Console;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import lib.*;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;
import veiculo.Carro;
import veiculo.CarroDAO;
import veiculo.Cor;
import veiculo.Veiculo;


public class JanelaCarro extends JFrame implements BotoesUteis{
    private Carro carro;
    private Botao cadastrar, listar, voltar, home, pesquisar, confirma, editar;
    private CampoInput nomeInput, marcaInput, modeloInput, anoFabricacaoInput, placaInput, numPortasInput;
    private RadioButton sim, nao;
    private JComboBox corBox;
    private int indexCorBox = -1;
    private String condicao = TrabalhoOficinaMecanica.getCondicao(), nome;
    private Lista listaPlaca, listaModelo, listaMarca;
    private ArrayList<Cor> cores = new ArrayList<>();
    private ArCond arCond;
    CarroDAO carroDAO = new CarroDAO();

    private ArrayList<Carro> carros = CarroDAO.getArrayListCarro();
    
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    
    public JanelaCarro (){
        carroDAO.getCarroDAO();
        // JFRAME
        setTitle("Carro");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajusta o tadmanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela

        //Menu
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

    private JComboBox setBox(String msgEtiqueta, DefaultComboBoxModel<String> modelString, int y){
        add(new Etiqueta(msgEtiqueta, 180, y, 200, 30, 18, SwingConstants.RIGHT));
        return new ComboBox(modelString, 400, y, 350, 30, 18);
    }

    private RadioButton setRadioButton(String msgRadioButton, int x,int y){
        return new RadioButton(msgRadioButton, x, y, 150, 30, 18);
    }
   
    private Botao setButton(String mensagem, int x, int y, int width, int height, int letterSize){
        return new Botao(mensagem, x, y, width, height, letterSize);
    }
    
    private void alertaVerifique(String txt){
        JOptionPane.showMessageDialog(null, "Verifique o campo "+ txt +"!");
    }

    private boolean isString(String txt){
        if(txt.isEmpty())
            return false;
        else
            return true;
    }

    private boolean isInt(String txt){
        try {
            int i = Integer.parseInt(txt);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
//Ferramentas -- fim
    private void principal(){
        setLabel("Oficina Mecânica Parafuso Solto", 0, 50, windowWidth, 30, 25, SwingConstants.CENTER);
        setLabel("Carro", 0, 100, windowWidth, 30, 20, SwingConstants.CENTER);
        
        add(cadastrar = setButton("Cadastrar", 350, 180, 300, 50, 18));
        add(listar = setButton("Listar", 350, 280, 300, 50, 18));
        
        cadastrar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Cadastrar");
            dispose();
            new JanelaCarro();
        });
        
        listar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            dispose();
            new JanelaCarro();
        });

        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaPrincipal();
            dispose();
        });
    }
    
    private void listar(){
        setLabel("Carro - Listar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        add(nomeInput = setLabelInput("Pesquise pela placa: ", "", 100));
        add(pesquisar = new Botao("Pesquisar", 750, 100, 120, 30, 15));

        DefaultListModel<String> modelMarca = new DefaultListModel<>();
        DefaultListModel<String> modelModelo = new DefaultListModel<>();
        DefaultListModel<String> modelPlaca = new DefaultListModel<>();

        pesquisar.addActionListener((ActionEvent e) -> {
            editar.setEnabled(false);
            editar.setCor(Color.BLACK, Color.DARK_GRAY);
            nome = nomeInput.getText();
            ArrayList<Carro> f = carroDAO.pesquisar(nome);
            if(modelPlaca != null){
                modelMarca.clear();
                modelModelo.clear();
                modelPlaca.clear();
                for (Carro item : f) {
                    modelMarca.addElement(item.getMarca());
                    modelModelo.addElement(item.getModelo());
                    modelPlaca.addElement(item.getPlaca());
                }
            }   
        });

        for (Carro item : carros) {
            modelMarca.addElement(item.getMarca());
            modelModelo.addElement(item.getModelo());
            modelPlaca.addElement(item.getPlaca());
        }

        setLabel("Marca", 200, 170, 200, 30, 20, SwingConstants.CENTER);
        setLabel("Modelo", 400, 170, 200, 30, 20, SwingConstants.CENTER);
        setLabel("Placa", 600, 170, 200, 30, 20, SwingConstants.CENTER);

        listaMarca = new Lista(modelMarca, 200, 200, 200, 200, 18);
        listaModelo = new Lista(modelModelo, 400, 200, 200, 200, 18);
        listaPlaca = new Lista(modelPlaca, 600, 200, 200, 200, 18);
        
        add(listaMarca);
        add(listaModelo);
        add(listaPlaca);

        listaMarca.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaModelo.setSelectedIndex(listaMarca.getSelectedIndex());
                editar.setEnabled(true);
                editar.setCor(Color.BLACK, Color.YELLOW);
            }
        });

        listaModelo.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaPlaca.setSelectedIndex(listaModelo.getSelectedIndex());
            }
        });

        listaPlaca.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
               listaMarca.setSelectedIndex(listaPlaca.getSelectedIndex()); 
            }
        });

        editarBotao();
        editar.addActionListener((ActionEvent e) -> {
            String name = listaPlaca.getSelectedValue();
            dispose();
            new JanelaCarroEditarExcluir(name);
        });

        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaCarro();
            dispose();
        });
    }

    private void cadastrar(){
        setLabel("Carro - Cadastrar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        DefaultComboBoxModel<String> modelCor = new DefaultComboBoxModel<>();

        for(Cor item : Cor.values()){
            modelCor.addElement(item.toString());
            cores.add(item);
        }

        add(marcaInput = setLabelInput("Marca: ", "", 100));
        add(modeloInput = setLabelInput("Modelo: ", "", 150));
        add(anoFabricacaoInput = setLabelInput("Ano: ", "", 200));
        add(corBox = setBox("Cor: ", modelCor, 250));
        add(placaInput = setLabelInput("Placa: ", "", 300));
        add(numPortasInput = setLabelInput("Nº Portas: ", "", 350));

        setLabel("Ar Condicionado: ", 180, 400, 200, 30, 18, SwingConstants.RIGHT);
        
        add(sim = setRadioButton("Sim", 400 ,400));
        add(nao = setRadioButton("Não", 550 ,400));
        
        sim.addActionListener((ActionEvent e) -> {
            sim.setSelected(true);
            nao.setSelected(false);
            arCond = ArCond.SIM;
        });
        nao.addActionListener((ActionEvent e) -> {
            sim.setSelected(false);
            nao.setSelected(true);
            arCond = ArCond.NAO;
        });

        corBox.setSelectedIndex(indexCorBox);

        corBox.addActionListener(e -> {
            indexCorBox = corBox.getSelectedIndex();
        });

        confirmar();
        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaCarro();
            dispose();
        });
    }

    private void confirmar(){
        confirma = new Botao("Criar", 350, 490, 300, 50, 18);
        confirma.setCor(Color.BLACK, Color.green);
        add(confirma);
        
        confirma.addActionListener((ActionEvent e) -> {
            String marca, modelo, placa;
            int numPortas, anoFabricacao;
            Cor cor;
            boolean arCondicionado;

            boolean verificaDados = true;

            //verifica se os campos se não estão vazios
            if(!isString(marcaInput.getText())){
                verificaDados = false;
                alertaVerifique("Marca");
            } else if(!isString(modeloInput.getText())){
                verificaDados = false;
                alertaVerifique("Modelo");
            } else if(!isInt(anoFabricacaoInput.getText())){
                verificaDados = false;
                alertaVerifique("Ano Fabricação");
            } else if(indexCorBox<0){
                verificaDados = false;
                alertaVerifique("Cor");
            } else if(!isString(placaInput.getText())){
                verificaDados = false;
                alertaVerifique("Placa");
            } else if(!isInt(numPortasInput.getText())){
                verificaDados = false;
                alertaVerifique("Número Portas");
            } else if(sim.isSelected() == false && nao.isSelected() == false){
                verificaDados = false;
                alertaVerifique("Ar Condicionado");
            } else if(sim.isSelected() == true && nao.isSelected() == true){
                verificaDados = false;
                alertaVerifique("Ar Condicionado");
            } else {
                for(Carro c : carros){
                    if(c.getPlaca().equals(placaInput.getText())){
                        verificaDados = false;
                    }
                }
            }

            if(verificaDados) {
                UIManager.put("OptionPane.yesButtonText", "Cadastrar");
                UIManager.put("OptionPane.noButtonText", "Cancelar");
                
                int confirmDialogResult = JOptionPane.showConfirmDialog(
                            null,
                            "Deseja cadastrar um carro?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION
                );
                
                if( confirmDialogResult == JOptionPane.YES_OPTION) {
                    //extrai os dados dos input's
                    marca = marcaInput.getText().toUpperCase();
                    modelo = modeloInput.getText().toUpperCase();
                    anoFabricacao = Integer.parseInt(anoFabricacaoInput.getText());
                    cor = cores.get(indexCorBox);
                    placa = placaInput.getText().toUpperCase();
                    numPortas = Integer.parseInt(numPortasInput.getText());
                    if(sim.isSelected() == true){
                        arCondicionado = true;
                    } else {
                        arCondicionado = false;
                    }
                    
                    //cria e adiciona o carro no Array
                    carro = new Carro(numPortas, arCondicionado, marca, modelo, anoFabricacao, placa, cor);
                    boolean retorno = carroDAO.inserir(carro);
                    if(retorno){
                        dispose();
                        new JanelaCarro();
                        JOptionPane.showMessageDialog(null, "O carro foi criado!");
                    }else
                        JOptionPane.showMessageDialog(null, "O carro não foi criado!");
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
}
