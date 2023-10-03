package caixas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import lib.*;
import lib.interfaces.BotoesUteis;
import pessoa.Cargo;
import pessoa.Cliente;
import pessoa.ClienteDAO;
import pessoa.Funcionario;
import pessoa.FuncionarioDAO;
import produto.Produto;
import produto.ProdutoDAO;
import servico.Servico;
import servico.ServicoDAO;
import servico.ServicoItem;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;

public class JanelaServicoEditarExcluir extends JFrame implements BotoesUteis {
    private Botao confirmar, excluir, voltar, adicionaProduto, editaProduto;
    private CampoInput dataServicoInput, carroPlacaInput, carroModeloInput, carroMarcaInput;
    private JComboBox boxC, boxA, boxM;
    private Lista listaProdID, listaProdN, listaProdQ, listaProdPU, listaProdTotal;
    private int indexListaC = -1, indexListaA = -1, indexListaM = -1, indexListaP = -1;
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    private ServicoDAO servicoDAO = new ServicoDAO();
    private Servico servico;
    private ArrayList<Servico> servicos = ServicoDAO.getArrayListServico();
    private ArrayList<Cliente> clientes = ClienteDAO.getArrayListCliente(); 
    private ArrayList<Funcionario> funcionarios = FuncionarioDAO.getArrayListFuncionario();
    private ArrayList<Produto> produtos = ProdutoDAO.getArrayListProduto();
    private ArrayList<Integer> posicaoAtendente = new ArrayList<>();
    private ArrayList<Integer> posicaoMecanico = new ArrayList<>();
    private Integer listaY = 275, listaWidth = 200, listaHeight = 200, fontSize = 18;
    private boolean alteracao = true;

    public JanelaServicoEditarExcluir(String name) {
        servicoDAO.getServicoDAO();
        servico = servicoDAO.retornaUm(Integer.valueOf(name));
        
        // JFRAME
        setTitle("Serviço - Editar");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajustar o tamanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela
        
        setInterfaceVisual(servico);
        
        //BOTOES
        botoesBaixo();
        
        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
    }
    
    private void setInterfaceVisual(Servico servico){
        this.servico = servico;
        setLabel("Serviço - Alterar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");    
        add(dataServicoInput = setLabelInput("Data Entrada: ", servico.getDataServico().format(formatter), 0, 100));
        dataServicoInput.setEnabled(false);
        dataServicoInput.setDisabledTextColor(Color.BLACK);
        dataServicoInput.setBackground(Color.LIGHT_GRAY);

        setLabel("Cliente", 100, 140, 200, 30, fontSize, SwingConstants.CENTER);
        setLabel("Atendente", 400, 140, 200, 30, fontSize, SwingConstants.CENTER);
        setLabel("Mecanico", 700, 140, 200, 30, fontSize, SwingConstants.CENTER);
        setLabel("Produtos", 0, 220, windowWidth, 30, fontSize, SwingConstants.CENTER);

        DefaultComboBoxModel<String> modelCliente = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> modelAtendente = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> modelMecanico = new DefaultComboBoxModel<>();

        DefaultListModel<String> modelProdID = new DefaultListModel<>();
        DefaultListModel<String> modelProdN = new DefaultListModel<>();
        DefaultListModel<String> modelProdQ = new DefaultListModel<>();
        DefaultListModel<String> modelProdPU = new DefaultListModel<>();
        DefaultListModel<String> modelProdTotal = new DefaultListModel<>();

        int i=0;
        for (Cliente item : clientes) {
            modelCliente.addElement(item.getNome());
            if(item.equals(servico.getCliente())){
                indexListaC = i;
            }
            i++;
        }

        i=0;
        
        for(Funcionario item : funcionarios) {
            if(item.getCargo().equals(Cargo.ATENDENTE)){
                modelAtendente.addElement(item.getNome());
                posicaoAtendente.add(i);
                if(item.equals(servico.getAtendente())){
                    indexListaA = posicaoAtendente.size()-1;
                }
            }
            if(item.getCargo().equals(Cargo.MECANICO)){
                modelMecanico.addElement(item.getNome());
                posicaoMecanico.add(i);
                if(item.equals(servico.getMecanico())){
                    indexListaM = posicaoMecanico.size()-1;
                } 
            }
            i++;
        }

        i = 0;
        for(ServicoItem item : servico.getPecas()){
            modelProdID.addElement(Integer.toString(item.getProduto().getCodProduto()));
            modelProdN.addElement(item.getProduto().getNome());
            modelProdQ.addElement(Double.toString(item.getQuantidade()));
            modelProdPU.addElement(Double.toString(item.getValorUnit()));
            modelProdTotal.addElement(Double.toString(item.getValorTotal()));
        }

        carroPlacaInput = setLabelInput("Placa: ", clientes.get(indexListaC).getCarro().getPlaca(), 300, 100);
        carroModeloInput = setLabelInput("Modelo: ", clientes.get(indexListaC).getCarro().getModelo(), 600, 100);
        //carroMarcaInput = setLabelInput("Marca: ", clientes.get(indexListaC).getCarro().getMarca(), 100);
        
        //BOX
        boxC = new ComboBox(modelCliente, 100, 170, 200, 30, fontSize);
        boxA = new ComboBox(modelAtendente, 400, 170, 200, 30, fontSize);
        boxM = new ComboBox(modelMecanico, 700, 170, 200, 30, fontSize);
        
        //LISTA PRODUTO
        setLabel("Código", 100, 250, 100, 25, 20, SwingConstants.CENTER);
        setLabel("Nome", 200, 250, 200, 25, 20, SwingConstants.CENTER);
        setLabel("Qnt", 400, 250, 100, 25, 20, SwingConstants.CENTER);
        setLabel("R$ Unitario", 500, 250, 200, 25, 20, SwingConstants.CENTER);
        setLabel("R$ Total", 700, 250, 200, 25, 20, SwingConstants.CENTER);
        
        listaProdID = new Lista(modelProdID, 100, listaY, listaWidth/2, listaHeight, fontSize-2);
        listaProdN = new Lista(modelProdN, 200, listaY, listaWidth, listaHeight, fontSize-2);
        listaProdQ = new Lista(modelProdQ, 400, listaY, listaWidth/2, listaHeight, fontSize-2);
        listaProdPU = new Lista(modelProdPU, 500, listaY, listaWidth, listaHeight, fontSize-2);
        listaProdTotal = new Lista(modelProdTotal, 700, listaY, listaWidth, listaHeight, fontSize-2);

        add(carroPlacaInput);
        add(carroModeloInput);
        add(boxC);
        add(boxA);
        add(boxM);
        add(listaProdID);
        add(listaProdN);
        add(listaProdQ);
        add(listaProdPU);
        add(listaProdTotal);

        carroPlacaInput.setEnabled(false);
        carroModeloInput.setEnabled(false);
        carroPlacaInput.setDisabledTextColor(Color.BLACK);
        carroModeloInput.setDisabledTextColor(Color.BLACK);
        carroPlacaInput.setBackground(Color.LIGHT_GRAY);
        carroModeloInput.setBackground(Color.LIGHT_GRAY);
        
        boxC.setSelectedIndex(indexListaC);
        boxA.setSelectedIndex(indexListaA);
        boxM.setSelectedIndex(indexListaM);
        
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

        listaProdID.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaProdN.setSelectedIndex(listaProdID.getSelectedIndex());
                if(alteracao){
                    editaProduto.setCor(Color.WHITE, Color.ORANGE);
                    editaProduto.setEnabled(true);
                };
                indexListaP = listaProdID.getSelectedIndex();
            }
        });
        listaProdN.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaProdQ.setSelectedIndex(listaProdN.getSelectedIndex());
            }
        });
        listaProdQ.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaProdPU.setSelectedIndex(listaProdQ.getSelectedIndex());
            }
        });
        listaProdPU.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaProdTotal.setSelectedIndex(listaProdPU.getSelectedIndex());
            }
        });
        listaProdTotal.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaProdID.setSelectedIndex(listaProdTotal.getSelectedIndex());
            }
        });

        confirmar = setButton("Salvar", 400, 600, 300, 50, fontSize);
    }

    private void alteracaoBotoes(){
        adicionaProduto.setEnabled(false);
        adicionaProduto.setCor(Color.BLACK, Color.DARK_GRAY);
        editaProduto.setEnabled(false);
        editaProduto.setCor(Color.BLACK, Color.DARK_GRAY);
        confirmar.setEnabled(true);
        confirmar.setCor(Color.BLACK, Color.GREEN);
        alteracao = false;
    }
    
    private void setLabel(String msgEtiqueta, int x,int y, int width, int height, int letterSize, int alinhamento){
        add(new Etiqueta(msgEtiqueta, x, y, width, height, letterSize, alinhamento));
    }
    
    private CampoInput setLabelInput(String msgEtiqueta, String msgCampoInput, int x, int y){
        add(new Etiqueta(msgEtiqueta, x, y, 150, 30, fontSize, SwingConstants.RIGHT));
        return new CampoInput(msgCampoInput, x+150, y, 150, 30, 15);
    }
    
    private RadioButton setRadioButton(String msgRadioButton, int x,int y){
        return new RadioButton(msgRadioButton, x, y, 150, 30, fontSize);
    }
   
    private Botao setButton(String mensagem, int x, int y, int width, int height, int letterSize){
        return new Botao(mensagem, x, y, width, height, letterSize);
    }
    
    private void excluir(){
        
        UIManager.put("OptionPane.yesButtonText", "Excluir");
        UIManager.put("OptionPane.noButtonText", "Cancelar");

        int confirmDialogResult = JOptionPane.showConfirmDialog(
                null,
                ("Deseja excluir o serviço?"),
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );   

        if( confirmDialogResult == JOptionPane.YES_OPTION) {
            
            boolean retorno = servicoDAO.excluir(servico);

            if(retorno){
                TrabalhoOficinaMecanica.setCondicao("Listar");
                dispose();
                new JanelaServico();
                JOptionPane.showMessageDialog(null, "O serviço foi excluido!");
            } else
                JOptionPane.showMessageDialog(null, "O serviço não foi excluido!");
        }
        
    }
    
    private void confirmar(){
        UIManager.put("OptionPane.yesButtonText", "Editar");
        UIManager.put("OptionPane.noButtonText", "Cancelar");
        
        int confirmDialogResult = JOptionPane.showConfirmDialog(
                null,
                ("Deseja editar o serviço '" + servico.getCodServico() + "' ?"),
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );   

        if( confirmDialogResult == JOptionPane.YES_OPTION) {
            Cliente clienteTemp = clientes.get(indexListaC);
            Funcionario atendenteTemp = funcionarios.get(posicaoAtendente.get(indexListaA));
            Funcionario mecanicoTemp = funcionarios.get(posicaoMecanico.get(indexListaM));

            Servico servTemp = new Servico(LocalDate.now(), clienteTemp, atendenteTemp, mecanicoTemp);

            boolean retorno = servicoDAO.editar(servico, servTemp);

            if(retorno){
                dispose();
                new JanelaServicoEditarExcluir(Integer.toString(this.servico.getCodServico()));
                JOptionPane.showMessageDialog(null, "O serviço '" + servico.getCodServico() +"' foi editado!");
            } else {
                JOptionPane.showMessageDialog(null, "O serviço não foi editado!");
            }
        }

    }
    
    @Override
    public void voltar() {};

    private void botoesBaixo() {
        adicionaProduto  = setButton("Adicionar Produto", 200, 475, 300, 50, fontSize);
        editaProduto = setButton("Editar Produto", 500, 475, 300, 50, fontSize);
        confirmar = setButton("Salvar", 350, 550, 150, 50, fontSize);
        excluir = setButton("Excluir", 500, 550, 150, 50, fontSize); 
        voltar = setButton("Voltar", 350, 600, 300, 50, fontSize);      
        
        adicionaProduto.setCor(Color.WHITE, Color.GREEN);
        editaProduto.setCor(Color.BLACK, Color.DARK_GRAY);
        confirmar.setCor(Color.BLACK, Color.DARK_GRAY);
        excluir.setCor(Color.BLACK, Color.RED);
        voltar.setCor(Color.WHITE, Color.LIGHT_GRAY);
        
        add(adicionaProduto);
        add(editaProduto);
        add(confirmar);
        add(excluir);
        add(voltar);

        editaProduto.setEnabled(false);
        confirmar.setEnabled(false);

        adicionaProduto.addActionListener((e) -> {
            new JanelaServicoItem().novoServicoItem(servico);
            dispose();
        });
        editaProduto.addActionListener((e) -> {
            if(indexListaP >= 0){
                new JanelaServicoItem().editarServicoItem(servico, servico.getPecas().get(indexListaP));
                dispose();
            }
        });
        confirmar.addActionListener((e) -> {
            confirmar();
        });
        excluir.addActionListener((e) -> {
            excluir();
        });
        voltar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            dispose();
            new JanelaServico();
        });
    }
}
