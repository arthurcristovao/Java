package caixas;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import lib.*;
import lib.interfaces.*;
import produto.*;
import servico.*;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;

public class JanelaServicoItem extends JFrame implements BotoesUteis{
    private String condicao;
    private final int windowWidth = 1000, windowHeight = 700;
    private Botao confirmar, excluir, voltar, seguir;
    private CampoInput listaProdIDInput, listaProdNInput, listaProdQInput, listaProdPUInput, listaProdTotalInput;
    private ProdutoDAO prodDAO = new ProdutoDAO();
    private ArrayList<Produto> produtos = prodDAO.getArrayListProduto();
    private ServicoDAO servicoDAO = new ServicoDAO();
    private ServicoItem servicoItem;
    private Servico servico;

    private int indexListaP = -1;
    private Lista listaProdID, listaProdN, listaProdPV;
        

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

    private void alerta(String txt){
        JOptionPane.showMessageDialog(null, txt);
    }

    public void novoServicoItem(Servico servico){
        this.servico = servico;
        // JFRAME
        setTitle("Serviço - Novo Produto");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajustar o tamanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela
        
        Integer listaY = 100, listaWidth = 200, listaHeight = 400, fontSize = 18; 
        
        setLabel("Selecione um Produto", 400, 50, 200, 30, 22, SwingConstants.CENTER);
        DefaultListModel<String> modelProdID = new DefaultListModel<>();
        DefaultListModel<String> modelProdN = new DefaultListModel<>();
        DefaultListModel<String> modelProdPV = new DefaultListModel<>();

        for(Produto item : produtos){
            modelProdID.addElement(Integer.toString(item.getCodProduto()));
            modelProdN.addElement(item.getNome());
            modelProdPV.addElement(Double.toString(item.getPrecoVenda()));
        }

        setLabel("Código", 300, 75, 100, 25, 18, SwingConstants.CENTER);
        setLabel("Nome", 400, 75, 200, 25, 18, SwingConstants.CENTER);
        setLabel("R$", 600, 75, 100, 25, 18, SwingConstants.CENTER);

        listaProdID = new Lista(modelProdID, 300, listaY, listaWidth/2, listaHeight, fontSize-2);
        listaProdN = new Lista(modelProdN, 400, listaY, listaWidth, listaHeight, fontSize-2);
        listaProdPV = new Lista(modelProdPV, 600, listaY, listaWidth/2, listaHeight, fontSize-2);

        add(listaProdID);
        add(listaProdN);
        add(listaProdPV);

        listaProdID.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaProdN.setSelectedIndex(listaProdID.getSelectedIndex());
                listaProdPV.setSelectedIndex(listaProdID.getSelectedIndex());
                seguir.setCor(Color.WHITE, Color.GREEN);
                seguir.setEnabled(true);
                indexListaP = listaProdID.getSelectedIndex();
            }
        });
        listaProdN.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaProdID.setSelectedIndex(listaProdN.getSelectedIndex());
                listaProdPV.setSelectedIndex(listaProdN.getSelectedIndex());
            }
        });
        listaProdPV.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaProdID.setSelectedIndex(listaProdPV.getSelectedIndex());
                listaProdN.setSelectedIndex(listaProdPV.getSelectedIndex());
            }
        });
     
        add(seguir = setButton("Seguir", 425, 550, 150, 50, 18));
        seguir.setCor(Color.BLACK, Color.DARK_GRAY);
        seguir.setEnabled(false);
        seguir.addActionListener((e) -> {
            ServicoItem novo = new ServicoItem(produtos.get(indexListaP), 0.0, produtos.get(indexListaP).getPrecoVenda());
            
            boolean retorno = servicoDAO.inserirItem(servico, novo);
            
            if(retorno){
                dispose();
                new JanelaServicoItem().editarServicoItem(servico, servico.getPecas().get(servico.getPecas().size()-1));
            } else {
                alerta("Erro ao adicionar um item!");
            }
        });

        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
        voltar();
        voltar.setBounds(425, 600, 150, 50);
        voltar.addActionListener((e) -> {
            new JanelaServicoEditarExcluir(Integer.toString(servico.getCodServico()));
        });
    };
    
    public void editarServicoItem(Servico servico, ServicoItem servicoItem){
        this.servicoItem = servicoItem;
        this.servico = servico;
        // JFRAME
        setTitle("Serviço - Editar Produto");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajustar o tamanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela
        //listaProdID, listaProdN, listaProdQ, listaProdPU, listaProdTotal

        setLabel("Serviço - Editar Produto", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
            
        add(listaProdIDInput = setLabelInput("ID: ", Integer.toString(servicoItem.getProduto().getCodProduto()), 100));
        add(listaProdNInput = setLabelInput("Nome: ", servicoItem.getProduto().getNome(), 150));
        add(listaProdQInput = setLabelInput("Quantidade: ", Double.toString(servicoItem.getQuantidade()), 200));
        add(listaProdPUInput = setLabelInput("Preço Unitario: ", Double.toString(servicoItem.getValorUnit()), 250));
        add(listaProdTotalInput = setLabelInput("Preço Total: ", Double.toString(servicoItem.getValorTotal()), 300));
        
        listaProdIDInput.setEditable(false);
        listaProdNInput.setEditable(false);
        listaProdTotalInput.setEditable(false);

        listaProdQInput.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                listaProdTotalInput.setText(calculaTotalProduto());
            }

            public void insertUpdate(DocumentEvent e) {
                listaProdTotalInput.setText(calculaTotalProduto());
            }

            public void removeUpdate(DocumentEvent e) {
                listaProdTotalInput.setText(calculaTotalProduto());
            }            
        });

        listaProdPUInput.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                listaProdTotalInput.setText(calculaTotalProduto());
            }

            public void insertUpdate(DocumentEvent e) {
                listaProdTotalInput.setText(calculaTotalProduto());
            }

            public void removeUpdate(DocumentEvent e) {
                listaProdTotalInput.setText(calculaTotalProduto());
            }            
        });

        botoesBaixo();
        voltar.addActionListener((e) -> {
            dispose();
            new JanelaServicoEditarExcluir(Integer.toString(servico.getCodServico()));
        });

        confirmar.addActionListener((e) -> {
            salvarEditarServicoItem(Integer.toString(servico.getCodServico()));
        });
        excluir.addActionListener((e) -> {
            UIManager.put("OptionPane.yesButtonText", "Excluir");
            UIManager.put("OptionPane.noButtonText", "Cancelar");

            int confirmDialogResult = JOptionPane.showConfirmDialog(
                    null,
                    ("Deseja excluir o item '" + servicoItem.getProduto().getNome() +"' ?"),
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );   

            if( confirmDialogResult == JOptionPane.YES_OPTION) {

                boolean retorno = servicoDAO.removeItem(servico, servicoItem);

                if(retorno){
                    dispose();
                    new JanelaServicoEditarExcluir(Integer.toString(servico.getCodServico()));
                    JOptionPane.showMessageDialog(null, "O item '" + servicoItem.getProduto().getNome() +"' foi excluido!");
                } else {
                    JOptionPane.showMessageDialog(null, "O item '" + servicoItem.getProduto().getNome() +"' não foi excluido!");
                }
            }
        });

        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
    };

    private String calculaTotalProduto() {
        try {
            confirmar.setCor(Color.BLACK, Color.GREEN);
            confirmar.setEnabled(true);
            return Double.toString(Double.parseDouble(listaProdQInput.getText()) * Double.parseDouble(listaProdPUInput.getText()));
        } catch (NumberFormatException ex) {
            confirmar.setCor(Color.BLACK, Color.DARK_GRAY);
            confirmar.setEnabled(false);
            return null;
        }
    }

    private void salvarEditarServicoItem(String id){
        servicoItem.setQuantidade(Double.parseDouble(listaProdQInput.getText()));
        servicoItem.setValorUnit(Double.parseDouble(listaProdPUInput.getText()));
        servico.calculaTotalServico();
        dispose();
        new JanelaServicoEditarExcluir(id);
    } 

    @Override
    public void voltar() {
        voltar = setButton("Voltar", 350, 600, 300, 50, 18);

        voltar.setCor(Color.BLACK, Color.LIGHT_GRAY);

        add(voltar);
    }

    private void botoesBaixo() {
        confirmar = setButton("Salvar", 350, 450, 150, 50, 18);
        excluir = setButton("Excluir", 500, 450, 150, 50, 18); 
        voltar = setButton("Voltar", 350, 550, 300, 50, 18);      
        
        confirmar.setCor(Color.BLACK, Color.GREEN);
        excluir.setCor(Color.BLACK, Color.RED);
        voltar.setCor(Color.WHITE, Color.LIGHT_GRAY);
        
        add(confirmar);
        add(excluir);
        add(voltar);

        confirmar.setCor(Color.BLACK, Color.DARK_GRAY);
        confirmar.setEnabled(false);

        voltar.addActionListener((e) -> {
            //dispose();
            //new JanelaServico();
        });
    }
}
