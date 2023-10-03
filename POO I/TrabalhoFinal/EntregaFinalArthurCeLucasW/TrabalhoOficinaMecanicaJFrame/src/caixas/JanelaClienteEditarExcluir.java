package caixas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import lib.*;
import lib.interfaces.BotoesUteis;
import pessoa.Cliente;
import pessoa.ClienteDAO;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;
import veiculo.Carro;
import veiculo.CarroDAO;

public class JanelaClienteEditarExcluir extends JFrame implements BotoesUteis{
    private String nome, indexCarroSelecionado, dataNascString, dataCadastroString, dataUltimaCompraString;
    private Botao confirm, voltar, excluir, listar, cadastrar, editar, apagar, pesquisar;
    private CampoInput nomeInput, cpfInput, dataNascimentoInput, telefoneInput, carroInput, dataCadastroInput, dataUltimaCompraInput;
    private Lista lista;
    private JComboBox carroBox;
    private int indexLista, indexCarroBox;
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    
    private Cliente cliente;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private CarroDAO carroDAO = new CarroDAO();
    private ArrayList<Cliente> clientes = ClienteDAO.getArrayListCliente();
    private ArrayList<Carro> carros = CarroDAO.getArrayListCarro();
    
    
    public JanelaClienteEditarExcluir(String name, int index){
        //Instancia do ClienteDAO compartilhado por endereço de memória
        clienteDAO.getClienteDAO();
        cliente = (Cliente) clienteDAO.retornaUm(name);
        indexLista = index;
        
        // JFRAME
        setTitle("Editar Cliente");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajustar o tamanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela
        
        setInterfaceVisual();

        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
    }

    //Formulário
    private void setInterfaceVisual (){
            transformaDataEmString();
        
            setLabel("Edite um cliente", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
            
            add(nomeInput = setLabelInput("Nome: ", cliente.getNome(), 100));
            add(cpfInput = setLabelInput("CPF: ", cliente.getCpf(), 150));
            add(dataNascimentoInput = setLabelInput("Data de nascimento: ", dataNascString, 200));
            add(telefoneInput = setLabelInput("Telefone: ", cliente.getTelefone(), 250));

            add(dataCadastroInput = setLabelInput("Data do Cadastro", dataCadastroString, 350));
            add(dataUltimaCompraInput = setLabelInput("Última Compra", dataUltimaCompraString, 400));
            
            dataCadastroInput.setFocusable(false);
            dataUltimaCompraInput.setFocusable(false);
                        
            DefaultComboBoxModel<String> modelCarro = new DefaultComboBoxModel<>();

            int i=0;
            for (Carro item : carros) {
                modelCarro.addElement(item.getPlaca());
                if(item.equals(cliente.getCarro())){
                    indexCarroBox = i;
                }
                i++;
            }

            add(carroBox = setBox("Carro: ", modelCarro, 300));

            carroBox.setSelectedIndex(indexCarroBox);

            carroBox.addActionListener(e -> {
                indexCarroBox = carroBox.getSelectedIndex();
            });
            
            //BOTOES
            confirmar();
            excluir();
            voltar();
    }

    //Inicio das Ferramentas
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
    
    private void transformaDataEmString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        dataNascString = cliente.getDataNascimento().format(formatter);
        dataCadastroString = cliente.getDataCadastro().format(formatter);
        
        if(cliente.getUltimaCompra() == null){
            dataUltimaCompraString = "Não existe";
        } else {
            dataUltimaCompraString = cliente.getUltimaCompra().format(formatter);
        }
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
    
    //Botao de excluir
    private void excluir(){
        excluir = setButton("Excluir", 500, 500, 150, 50, 18);
        excluir.setCor(Color.BLACK, Color.RED);
        add(excluir);
        
        excluir.addActionListener((e) -> { 
            UIManager.put("OptionPane.yesButtonText", "Excluir");
            UIManager.put("OptionPane.noButtonText", "Cancelar");

            int confirmDialogResult = JOptionPane.showConfirmDialog(
                    null,
                    ("Deseja excluir o cliente '" + cliente.getNome() + "' ?"),
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );   

            if( confirmDialogResult == JOptionPane.YES_OPTION) {
                boolean retorno = clienteDAO.excluir(cliente);
                
                if(retorno){
                    TrabalhoOficinaMecanica.setCondicao("Listar");
                    dispose();
                    new JanelaCliente();
                    JOptionPane.showMessageDialog(null, "O cliente '" + cliente.getNome() +"' foi excluido!");
                } else {
                    JOptionPane.showMessageDialog(null, "O cliente não foi excluido!");
                }
            }
        });
    }
    
    //Botao de confirmar
    private void confirmar(){
        confirm = setButton("Salvar", 350, 500, 150, 50, 18);
        confirm.setCor(Color.BLACK, Color.GREEN);
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
                    if(!item.equals(cliente)){
                        if(item.getCpf().equals(cpfInput.getText())){
                        verificaDados = false;
                            alertaVerifique("CPF"); 
                        }
                    }
                }
            }
              
            
            
            
            
            if(verificaDados){
                UIManager.put("OptionPane.yesButtonText", "Editar");
                UIManager.put("OptionPane.noButtonText", "Cancelar");
                
                String nome = nomeInput.getText();
                String cpf = cpfInput.getText();

                String textoData = dataNascimentoInput.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                LocalDate dataNascimento = LocalDate.parse(textoData, formatter);

                String telefone = telefoneInput.getText();
                
                int confirmDialogResult = JOptionPane.showConfirmDialog(
                            null,
                            ("Deseja editar o cliente '" + nome + "' ?"),
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION
                );
            
                if( confirmDialogResult == JOptionPane.YES_OPTION) {
                    Carro carro = carroDAO.retornaUm(indexCarroSelecionado);
                    LocalDate atual = LocalDate.now();
                    
                    Cliente clienteTemp = new Cliente(atual, carro, nome, cpf, dataNascimento, telefone);
                    
                    boolean retorno = clienteDAO.editar(clienteTemp, cliente);
                    
                    if(retorno){
                        TrabalhoOficinaMecanica.setCondicao("Listar");
                        dispose();
                        new JanelaCliente();
                        JOptionPane.showMessageDialog(null, "O cliente '" + nome +"' foi editado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "O cliente não foi editado!");
                    }
                }
            } 
        });
    }
    
    //Botao de voltar
    @Override
    public void voltar() {
        voltar = setButton("Voltar", 350, 550, 300, 50, 18);
        voltar.setCor(Color.BLACK, Color.LIGHT_GRAY);
        add(voltar);
        
        voltar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            dispose();
            new JanelaCliente();
        });
    }

}