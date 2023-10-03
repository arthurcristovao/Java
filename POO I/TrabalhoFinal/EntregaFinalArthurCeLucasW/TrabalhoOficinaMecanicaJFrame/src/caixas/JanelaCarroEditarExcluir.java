
package caixas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lib.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import lib.interfaces.BotoesUteis;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import trabalhooficinamecanica.TrabalhoOficinaMecanica;
import pessoa.*;
import veiculo.*;


public class JanelaCarroEditarExcluir extends JFrame implements BotoesUteis{   
    private Botao voltar, home, confirmar, excluir;
    private Etiqueta marca, modelo, anoFabricacao, cor, placa, arCondicionado, numPortas, titulo;
    private CampoInput marcaInput, modeloInput, anoFabricacaoInput, corInput, placaInput, numPortasInput;
    private JComboBox corBox;
    private RadioButton sim, nao;
    private ArCond arCond;

    private int indexCorBox = -1;
    
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    CarroDAO carroDAO = new CarroDAO();
    private Carro carro;
    private ArrayList<Carro> carros = CarroDAO.getArrayListCarro();
    private ArrayList<Cor> cores = new ArrayList<>();
    
    
    public JanelaCarroEditarExcluir(String placa) {
        carroDAO.getCarroDAO();
        carro = carroDAO.retornaUm(placa);
        // JFRAME
        setTitle("Carro");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajusta o tamanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela

        // Informações
        cabecalho();
        
        carro();
        
        // BOTOES
        
        confirmar();
        excluir();
         
        // JFRAME
        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
    }

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
    
    private void cabecalho(){
        titulo = new Etiqueta("Carro - Editar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        add(titulo);
    }

    private void carro(){
        int i=0;
        DefaultComboBoxModel<String> modelCor = new DefaultComboBoxModel<>();

        for(Cor item : Cor.values()){
            modelCor.addElement(item.toString());
            this.cores.add(item);
            if(item.equals(carro.getCor())){
                indexCorBox = i;
            }
            i++;
        }

        add(marcaInput = setLabelInput("Marca: ", carro.getMarca(), 100));
        add(modeloInput = setLabelInput("Modelo: ", carro.getModelo(), 150));
        add(anoFabricacaoInput = setLabelInput("Ano: ", Integer.toString(carro.getAnoFabricacao()), 200));
        add(corBox = setBox("Cor: ", modelCor, 250));
        add(placaInput = setLabelInput("Placa: ", carro.getPlaca(), 300));
        add(numPortasInput = setLabelInput("Nº Portas: ", Integer.toString(carro.getNumeroPortas()), 350));

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

        if(carro.isArCondicionado()){
            sim.setSelected(true);
        } else {
            nao.setSelected(true);
        }

        corBox.setSelectedIndex(indexCorBox);

        corBox.addActionListener(e -> {
            indexCorBox = corBox.getSelectedIndex();
        });

        confirmar();
        voltar();
    }
    
    private void confirmar(){
        confirmar = setButton("Salvar", 350, 500, 150, 50, 18);
        confirmar.setCor(Color.BLACK, Color.GREEN);
        add(confirmar);

        confirmar.addActionListener((e) -> {
            Boolean verificaDados = true;

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
                for(Carro item : carros){
                    if(!carro.equals(item)){
                    if(item.getPlaca().equals(placaInput.getText().toUpperCase())){
                        verificaDados = false;
                        alertaVerifique("Placa");
                    }
                }
                }
            }
            
            if(verificaDados){
                UIManager.put("OptionPane.yesButtonText", "Editar");
                UIManager.put("OptionPane.noButtonText", "Cancelar");

                int confirmDialogResult = JOptionPane.showConfirmDialog(
                        null,
                        ("Deseja editar o carro '" + carro.getPlaca() + "' ?"),
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );   

                if( confirmDialogResult == JOptionPane.YES_OPTION) {
                    String marca = marcaInput.getText().toUpperCase();
                    String modelo = modeloInput.getText().toUpperCase();
                    int anoFabricacao = Integer.parseInt(anoFabricacaoInput.getText());
                    Cor cor = cores.get(indexCorBox);
                    String placa = placaInput.getText().toUpperCase();
                    int numPortas = Integer.parseInt(numPortasInput.getText());           
                    boolean arCondicionado;
                    
                    if(sim.isSelected() == true){
                        arCondicionado = true;
                    } else {
                        arCondicionado = false;
                    }

                    boolean retorno = carroDAO.editar(carro, new Carro(numPortas, arCondicionado, marca, modelo, anoFabricacao, placa, cor));
                    
                    if(retorno){
                        TrabalhoOficinaMecanica.setCondicao("Listar");
                        dispose();
                        new JanelaCarro();
                        JOptionPane.showMessageDialog(null, "O carro '" + carro.getPlaca() +"' foi editado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "O carro não foi editado!");
                    }
                }
            }
        });
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
                    ("Deseja excluir o carro '" + carro.getPlaca() + "' ?"),
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );   

            if( confirmDialogResult == JOptionPane.YES_OPTION) {
                boolean retorno = carroDAO.excluir(carro);
                if(retorno){
                    TrabalhoOficinaMecanica.setCondicao("Listar");
                    dispose();
                    new JanelaCarro();
                    JOptionPane.showMessageDialog(null, "O carro '" + carro.getPlaca() +"' foi excluido!");
                } else {
                    JOptionPane.showMessageDialog(null, "O carro não foi excluido!");
                }
            }
        });
    }
    
    @Override
    public void voltar() {
        voltar = new Botao("Voltar", 350, 550, 300, 50, 18);
        voltar.setCor(Color.BLACK, Color.LIGHT_GRAY);
        add(voltar);
        voltar.addActionListener((ActionEvent e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            new JanelaCarro();
            dispose();
        });
    }
}
