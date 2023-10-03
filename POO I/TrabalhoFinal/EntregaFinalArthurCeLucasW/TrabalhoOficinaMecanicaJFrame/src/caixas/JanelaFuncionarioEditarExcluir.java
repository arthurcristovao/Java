
package caixas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import lib.*;
import lib.interfaces.BotoesUteis;
import pessoa.Cargo;
import pessoa.Funcionario;
import pessoa.FuncionarioDAO;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;



public class JanelaFuncionarioEditarExcluir extends JFrame implements BotoesUteis{
    private Botao confirmar, excluir, voltar;
    private CampoInput nomeInput, cpfInput, dataNascimentoInput, telefoneInput, salarioInput;
    private Cargo cargo;
    private RadioButton atendenteRadio, mecanicoRadio;
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    FuncionarioDAO funDAO = new FuncionarioDAO();
    private Funcionario funcionario;
    private ArrayList<Funcionario> funcionarios = FuncionarioDAO.getArrayListFuncionario();
    private String dataString, salario;
    
    public JanelaFuncionarioEditarExcluir(String name) {
        //Instancia do FuncionarioDAO compartilhado por endereço de memória
        funDAO.getFunDAO();
        funcionario = funDAO.retornaUm(name);
        
        // JFRAME
        setTitle("Editar Funcionario");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajustar o tamanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        dataString = funcionario.getDataNascimento().format(formatter);
        salario = Double.toString(funcionario.getSalario());
        setInterfaceVisual();
        
        
        //BOTOES
        confirmar();
        excluir();
        voltar();
        
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
    
    private void setInterfaceVisual(){
        setLabel("Funcionário - Cadastrar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
            
        add(nomeInput = setLabelInput("Nome: ", funcionario.getNome(), 100));
        add(cpfInput = setLabelInput("CPF: ", funcionario.getCpf(), 200));
        add(dataNascimentoInput = setLabelInput("Data de nascimento: ", dataString, 250));
        add(telefoneInput = setLabelInput("Telefone: ", funcionario.getTelefone(), 300));
        add(salarioInput = setLabelInput("Salario: ", salario, 350));
            
        setLabel("Cargo: ", 180, 150, 200, 30, 18, SwingConstants.RIGHT);

        add(atendenteRadio = setRadioButton("Atendente", 400 ,150));
        add(mecanicoRadio = setRadioButton("Mecânico", 550 ,150));

        
        cargo = funcionario.getCargo();
        if(cargo == Cargo.ATENDENTE) atendenteRadio.setSelected(true);
        if(cargo == Cargo.MECANICO) mecanicoRadio.setSelected(true);
        

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

        confirmar = setButton("Salvar", 350, 490, 300, 50, 18);
    }
    
    private void excluir(){
        excluir = setButton("Excluir", 500, 500, 150, 50, 18);
        excluir.setCor(Color.BLACK, Color.RED);
        add(excluir);
        
        excluir.addActionListener((e) -> { 
            UIManager.put("OptionPane.yesButtonText", "Excluir");
            UIManager.put("OptionPane.noButtonText", "Cancelar");

            int confirmDialogResult = JOptionPane.showConfirmDialog(
                    null,
                    ("Deseja excluir o funcionário '" + funcionario.getNome() + "' ?"),
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );   

            if( confirmDialogResult == JOptionPane.YES_OPTION) {
                //exclui o carro do array
                boolean retorno = funDAO.excluir(funcionario);

                //processa o retorno da função
                if(retorno){
                    TrabalhoOficinaMecanica.setCondicao("Listar");
                    dispose();
                    new JanelaFuncionario();
                    JOptionPane.showMessageDialog(null, "O funcionário '" + funcionario.getNome() +"' foi excluido!");
                } else {
                    JOptionPane.showMessageDialog(null, "O funcionário não foi excluido!");
                }    
            }
        });
    }
    
    private void confirmar(){
        confirmar = setButton("Salvar", 350, 500, 150, 50, 18);
        confirmar.setCor(Color.BLACK, Color.GREEN);
        add(confirmar);
        
        confirmar.addActionListener((e) -> {
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
                    if(!funcionario.equals(item)){
                        if(item.getCpf().equals(cpfInput.getText().toUpperCase())){
                            verificaDados = false;
                            alertaVerifique("CPF");
                        }
                    }
                }
            }

            if(verificaDados){
                UIManager.put("OptionPane.yesButtonText", "Editar");
                UIManager.put("OptionPane.noButtonText", "Cancelar");

                int confirmDialogResult = JOptionPane.showConfirmDialog(
                        null,
                        ("Deseja editar o funcionário '" + funcionario.getNome() + "' ?"),
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

                    //cria e edita o carro no Array
                    boolean retorno = funDAO.editar(funcionario, new Funcionario(cargo, salario, nome, cpf, dataNascimento, telefone));
                    
                    //processa o retorno da função
                    if(retorno){
                        TrabalhoOficinaMecanica.setCondicao("Listar");
                        dispose();
                        new JanelaFuncionario();
                        JOptionPane.showMessageDialog(null, "O funcionário '" + funcionario.getNome() +"' foi editado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "O funcionário não foi editado!");
                    }
                }
            }
        });
    }
    
    @Override
    public void voltar() {
        voltar = setButton("Voltar", 350, 550, 300, 50, 18);
        voltar.setCor(Color.BLACK, Color.LIGHT_GRAY);
        add(voltar);
        
        voltar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            dispose();
            new JanelaFuncionario();
        });
    }    
}
