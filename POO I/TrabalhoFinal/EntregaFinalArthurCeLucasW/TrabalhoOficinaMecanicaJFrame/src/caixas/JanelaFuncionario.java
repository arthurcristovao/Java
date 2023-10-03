
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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;
import pessoa.*;


public class JanelaFuncionario extends JFrame implements BotoesUteis{
    private Funcionario funcionario;
    private String nome;
    private String condicao = TrabalhoOficinaMecanica.getCondicao();
    private Botao confirm, voltar, home, listar, cadastrar, editar, apagar, pesquisar;
    private RadioButton atendenteRadio, mecanicoRadio;
    private CampoInput nomeInput, cpfInput, dataNascimentoInput, telefoneInput, salarioInput;
    private Lista listaNome, listaCPF;
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    private Cargo cargo;
    FuncionarioDAO funDAO = new FuncionarioDAO();
    private Funcionario f;
    private ArrayList<Funcionario> funcionarios = FuncionarioDAO.getArrayListFuncionario();
    
    public JanelaFuncionario() {
        //Instancia do FuncionarioDAO compartilhado por endereço de memória
        funDAO.getFunDAO();
        // JFRAME
        setTitle("Funcionário");
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
        //Limpa condição
        TrabalhoOficinaMecanica.setCondicao("");
        
        //BOTOES
        paginaPrincipal();
         
        // JFRAME
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
//Ferramentas -- fim
    private void principal(){
        setLabel("Oficina Mecânica Parafuso Solto", 0, 50, windowWidth, 30, 25, SwingConstants.CENTER);
        setLabel("Funcionário", 0, 100, windowWidth, 30, 20, SwingConstants.CENTER);
        add(cadastrar = setButton("Cadastrar", 350, 180, 300, 50, 18));
        add(listar = setButton("Listar", 350, 280, 300, 50, 18));
        
        cadastrar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Cadastrar");
            dispose();
            new JanelaFuncionario();
        });
        
        listar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            dispose();
            new JanelaFuncionario();
        });

        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaPrincipal();
            dispose();
        });
    }

    private void listar(){
        setLabel("Funcionário - Listar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        add(nomeInput = setLabelInput("Pesquise pelo nome: ", "", 100));
        add(pesquisar = new Botao("Pesquisar", 750, 100, 120, 30, 15));
        
        DefaultListModel<String> modelNome = new DefaultListModel<>();
        DefaultListModel<String> modelCPF = new DefaultListModel<>();

        for (Funcionario item : funcionarios) {
            modelNome.addElement(item.getNome());
            modelCPF.addElement(item.getCpf());
        }

        pesquisar.addActionListener((ActionEvent e) -> {
            editar.setEnabled(false);
            editar.setCor(Color.BLACK, Color.DARK_GRAY);
            nome = nomeInput.getText();
            ArrayList<Funcionario> f = funDAO.pesquisar(nome);
            if(modelNome != null){
                modelNome.clear();
                modelCPF.clear();
                for (Funcionario item : f) {
                    modelNome.addElement(item.getNome());
                    modelCPF.addElement(item.getCpf());
                }
            }   
        });

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

        editarBotao();
        editar.addActionListener((ActionEvent e) -> {
            String name = listaNome.getSelectedValue();
            dispose();
            new JanelaFuncionarioEditarExcluir(name);
        });
        
        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaFuncionario();
            dispose();
        });
    }

    private void cadastrar(){
        setLabel("Funcionário - Cadastrar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
            
        add(nomeInput = setLabelInput("Nome: ", "", 100));
        add(cpfInput = setLabelInput("CPF: ", "", 200));
        add(dataNascimentoInput = setLabelInput("Data de nascimento: ", "", 250));
        add(telefoneInput = setLabelInput("Telefone: ", "", 300));
        add(salarioInput = setLabelInput("Salario: ", "", 350));
        
        setLabel("Cargo: ", 180, 150, 200, 30, 18, SwingConstants.RIGHT);
        
        add(atendenteRadio = setRadioButton("Atendente", 400 ,150));
        add(mecanicoRadio = setRadioButton("Mecânico", 550 ,150));
        
        atendenteRadio.addActionListener((ActionEvent e) -> {
            atendenteRadio.setSelected(true);
            mecanicoRadio.setSelected(false);
            cargo = Cargo.ATENDENTE;
        });
        mecanicoRadio.addActionListener((ActionEvent e) -> {
            atendenteRadio.setSelected(false);
            mecanicoRadio.setSelected(true);
            cargo = Cargo.MECANICO;
        });
        
        confirmar();
        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaFuncionario();
            dispose();
        });
    }

    private void confirmar(){
        confirm = new Botao("Criar", 350, 490, 300, 50, 18);
        confirm.setCor(Color.BLACK, Color.green);
        add(confirm);
        
        confirm.addActionListener((ActionEvent e) -> {
            double salario;
            String nome, cpf, textoData, telefone;
            LocalDate dataNascimento;
            
            boolean verificaDados = true;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

            //verifica se os campos se não estão vazios
            if(!isString(nomeInput.getText())){
                verificaDados = false;
                alertaVerifique("Nome");
            } else if(atendenteRadio.isSelected() == true && mecanicoRadio.isSelected() == true){
                verificaDados = false;
                alertaVerifique("Cargo");
            } else if(atendenteRadio.isSelected() == false && mecanicoRadio.isSelected() == false){
                verificaDados = false;
                alertaVerifique("Cargo");
            } else if(!isString(cpfInput.getText())){
                verificaDados = false;
                alertaVerifique("CPF");
            } else if(!isDate(dataNascimentoInput.getText())){
                verificaDados = false;
                alertaVerifique("Data de Nascimento");
            } else if(!isString(telefoneInput.getText())){
                verificaDados = false;
                alertaVerifique("Telefone");
            } else if(!isDouble(salarioInput.getText())){
                verificaDados = false;
                alertaVerifique("Salario");
            } else {
                for(Funcionario item : funcionarios){
                    if(item.getCpf().equals(cpfInput.getText().toUpperCase())){
                        verificaDados = false;
                        alertaVerifique("CPF");
                    }
                }
            }

            if(verificaDados){
                UIManager.put("OptionPane.yesButtonText", "Cadastrar");
                UIManager.put("OptionPane.noButtonText", "Cancelar");
                
                int confirmDialogResult = JOptionPane.showConfirmDialog(
                            null,
                            ("Deseja cadastrar o funcionário?"),
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION
                );
                
                if( confirmDialogResult == JOptionPane.YES_OPTION) {
                    //extrai os dados dos input's
                    salario = Double.parseDouble(salarioInput.getText());
                    nome = nomeInput.getText().toUpperCase();
                    cpf = cpfInput.getText();
                    textoData = dataNascimentoInput.getText();
                    dataNascimento = LocalDate.parse(textoData, formatter);
                    telefone = telefoneInput.getText();

                    //cria e adiciona o carro no Array
                    boolean retorno = funDAO.inserir(new Funcionario(cargo, salario, nome, cpf, dataNascimento, telefone));

                    if(retorno){
                        dispose();
                        new JanelaFuncionario();
                        JOptionPane.showMessageDialog(null, "O funcionário foi cadastrado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "O funcionário não foi cadastrado!");
                    }
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
        home = new Botao("Home", 0, 0, 150, 50, 18);
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
