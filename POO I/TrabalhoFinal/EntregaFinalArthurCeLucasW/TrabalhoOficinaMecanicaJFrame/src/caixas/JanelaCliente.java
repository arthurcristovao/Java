package caixas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lib.*;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import lib.interfaces.BotoesUteis;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;
import pessoa.*;
import veiculo.Carro;
import veiculo.CarroDAO;

public class JanelaCliente extends JFrame implements BotoesUteis{
    private Cliente cliente, c;
    private String nome, indexCarroSelecionado;
    private String condicao = TrabalhoOficinaMecanica.getCondicao();
    private Botao confirm, voltar, home, listar, cadastrar, editar, apagar, pesquisar;
    private CampoInput nomeInput, cpfInput, dataNascimentoInput, telefoneInput;
    private Lista listaNome, listaCPF;
    private JComboBox carroBox;
    private int indexCarroBox = -1;
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    ClienteDAO clienteDAO = new ClienteDAO();
    CarroDAO carroDAO = new CarroDAO();
    private ArrayList<Cliente> clientes = ClienteDAO.getArrayListCliente();
    private ArrayList<Carro> carros = CarroDAO.getArrayListCarro();
    
    public JanelaCliente() {
        //Instancia do ClienteDAO compartilhado por endereço de memória
        clienteDAO.getClienteDAO();
        // JFRAME
        setTitle("Cliente");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajustar o tamanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela
        
        if(condicao.isEmpty()){
            principal();
        } else if(condicao.equals("Listar")){
            listar();
        } else if(condicao.equals("Cadastrar")){
            cadastrar();
        }

        paginaPrincipal();
         
        // JFRAME
        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
        TrabalhoOficinaMecanica.setCondicao("");
    }
    
    //Inicio das ferramentas
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

    private boolean isDouble(String txt){
        try {
            Double i = Double.parseDouble(txt);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDate(String txt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            LocalDate i = LocalDate.parse(txt, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    //Fim das ferramentas

    //Menu Principal
    private void principal(){
        setLabel("Oficina Mecânica Parafuso Solto", 0, 50, windowWidth, 30, 25, SwingConstants.CENTER);
        setLabel("Cliente", 0, 100, windowWidth, 30, 20, SwingConstants.CENTER);
        add(cadastrar = setButton("Cadastrar", 350, 180, 300, 50, 18));
        add(listar = setButton("Listar", 350, 280, 300, 50, 18));
        
        cadastrar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Cadastrar");
            dispose();
            new JanelaCliente();
        });
        
        listar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            dispose();
            new JanelaCliente();
        });

        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaPrincipal();
            dispose();
        });
    }
    
    //Listar clientes
    private void listar(){
        editarBotao();
        
        setLabel("Cliente - Listar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        add(nomeInput = setLabelInput("Pesquise pelo nome: ", "", 100));
        add(pesquisar = new Botao("Pesquisar", 750, 100, 120, 30, 15));
        
        DefaultListModel<String> modelNome = new DefaultListModel<>();
        DefaultListModel<String> modelCPF = new DefaultListModel<>();

        pesquisar.addActionListener((ActionEvent e) -> {
            editar.setEnabled(false);
            editar.setCor(Color.BLACK, Color.DARK_GRAY);
            nome = nomeInput.getText();
            ArrayList<Cliente> c = clienteDAO.pesquisar(nome);
            if( modelNome != null){
                modelNome.clear();
                modelCPF.clear();
                for (Cliente item : c) {
                    modelNome.addElement(item.getNome());
                    modelCPF.addElement(item.getCpf());
                }
            }
        }); 

        for (Cliente item : clientes) {
            modelNome.addElement(item.getNome());
            modelCPF.addElement(item.getCpf());
        }

        setLabel("Nome", 300, 170, 200, 30, 20, SwingConstants.CENTER);
        setLabel("CPF", 500, 170, 200, 30, 20, SwingConstants.CENTER);

        listaNome = new Lista(modelNome, 300, 200, 200, 200, 18);
        listaCPF = new Lista(modelCPF, 500, 200, 200, 200, 18);

        add(listaNome);
        add(listaCPF);

        listaNome.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaCPF.setSelectedIndex(listaNome.getSelectedIndex());
                editar.setEnabled(true);
                editar.setCor(Color.BLACK, Color.YELLOW);
            }
        });

        listaCPF.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaNome.setSelectedIndex(listaCPF.getSelectedIndex());
            }
        });
        
        editar.addActionListener((ActionEvent e) -> {
            String name = listaNome.getSelectedValue();
            dispose();
            new JanelaClienteEditarExcluir(name, listaNome.getSelectedIndex());
        });
        
        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaCliente();
            dispose();
        });
    }

    //Cadastrar Clientes
    private void cadastrar(){
            setLabel("Cliente - Cadastrar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
            
            add(nomeInput = setLabelInput("Nome: ", "", 100));
            add(cpfInput = setLabelInput("CPF: ", "", 150));
            add(dataNascimentoInput = setLabelInput("Data de nascimento: ", "", 200));
            add(telefoneInput = setLabelInput("Telefone: ", "", 250));
            
            DefaultComboBoxModel<String> modelCarro = new DefaultComboBoxModel<>();

            for (Carro item : carros) {
                modelCarro.addElement(item.getPlaca());
            }

            add(carroBox = setBox("Carro: ", modelCarro, 300));

            carroBox.setSelectedIndex(indexCarroBox);

            carroBox.addActionListener(e -> {
                indexCarroBox = carroBox.getSelectedIndex();
            });
            
            confirmar();
            voltar();
            voltar.addActionListener((ActionEvent e) -> {
                new JanelaCliente();
                dispose();
            });
    }

    //Botão de Criar
    private void confirmar(){
        confirm = new Botao("Criar", 350, 490, 300, 50, 18);
        confirm.setCor(Color.BLACK, Color.green);
        add(confirm);
        
        confirm.addActionListener((ActionEvent e) -> {
            
            
            boolean verificaDados = true;

            //verifica se os campos se não estão vazios
            if(!isString(nomeInput.getText())){
                verificaDados = false;
                alertaVerifique("Nome");
            }  else if(!isString(cpfInput.getText())){
                verificaDados = false;
                alertaVerifique("CPF");
            } else if(!isDate(dataNascimentoInput.getText())){
                verificaDados = false;
                alertaVerifique("Data de Nascimento (DDMMYYYY)");
            } else if(!isString(telefoneInput.getText())){
                verificaDados = false;
                alertaVerifique("Telefone");
            } else if(indexCarroBox < 0){
                verificaDados = false;
                alertaVerifique("Carro");
            } else {
                for(Cliente item : clientes){
                    if(item.getCpf().equals(cpfInput.getText())){
                       verificaDados = false;
                        alertaVerifique("CPF"); 
                    }
                }
            }
            
            if(verificaDados){
                UIManager.put("OptionPane.yesButtonText", "Cadastrar");
                UIManager.put("OptionPane.noButtonText", "Cancelar");
                
                String nome = nomeInput.getText();
                String cpf = cpfInput.getText();

                String textoData = dataNascimentoInput.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                LocalDate dataNascimento = LocalDate.parse(textoData, formatter);

                String telefone = telefoneInput.getText();
                
                int confirmDialogResult = JOptionPane.showConfirmDialog(
                            null,
                            ("Deseja cadastrar o cliente '" + nome + "' ?"),
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION
                );
            
                if( confirmDialogResult == JOptionPane.YES_OPTION) {
                    Carro carro = carros.get(indexCarroBox);
                    LocalDate atual = LocalDate.now();
                    
                    cliente = new Cliente(atual, carro, nome, cpf, dataNascimento, telefone);
                    
                    boolean retorno = clienteDAO.inserir(cliente);

                    if(retorno){
                        dispose();
                        new JanelaCliente();
                        JOptionPane.showMessageDialog(null, "O cliente '" + nome +"' foi cadastrado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "O cliente não foi cadastrado!");
                    }
                }
            } 
            
            
        });
    }
    
    //Botão de Voltar
    @Override
    public void voltar() {
        voltar = new Botao("Voltar", 350, 550, 300, 50, 18);
        voltar.setCor(Color.BLACK, Color.LIGHT_GRAY);
        add(voltar);
    }

    //Botão Home
    private void paginaPrincipal() {
        home = new Botao("Home", 0, 0, 150, 50, 18);
        home.setCor(Color.BLACK, Color.LIGHT_GRAY);
        add(home);
        home.addActionListener((ActionEvent e) -> {
            TrabalhoOficinaMecanica.setCondicao("");
            new JanelaPrincipal();
            dispose();
        });
    }
    
    //Botão Editar
    private void editarBotao() {
        editar = new Botao("Editar", 350, 490, 300, 50, 18);
        editar.setEnabled(false);
        editar.setCor(Color.BLACK, Color.DARK_GRAY);
        add(editar);
    }

}
