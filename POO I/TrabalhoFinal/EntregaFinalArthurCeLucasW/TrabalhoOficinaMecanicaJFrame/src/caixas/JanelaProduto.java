package caixas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import lib.*;
import lib.interfaces.*;
import produto.Produto;
import produto.ProdutoDAO;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;

public class JanelaProduto extends JFrame implements BotoesUteis{
    private String condicao = TrabalhoOficinaMecanica.getCondicao();
    private String nome;
    private Botao cadastrar, listar, voltar, home, pesquisar, confirm, editar;
    private CampoInput nomeInput, marcaInput, modeloInput, unidadeInput, anoFabricacaoInput, precoVendaInput;
    private Lista listaC, listaN, listaP;
    private int indexListaP = -1;
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private ArrayList<Produto> produtos = produtoDAO.getArrayListProduto();

    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    private int alteracao = 0;

    public JanelaProduto(){
        setTitle("Produto");
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
        
        //BOTOES
        paginaPrincipal();   
        
        //JFRAME
        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
        TrabalhoOficinaMecanica.setCondicao("");
    }

    //Tela Principal do Produto
    private void principal(){
        setLabel("Oficina Mecânica Parafuso Solto", 0, 50, windowWidth, 30, 25, SwingConstants.CENTER);
        setLabel("Produto", 0, 100, windowWidth, 30, 20, SwingConstants.CENTER);
        
        add(cadastrar = setButton("Cadastrar", 350, 180, 300, 50, 18));
        add(listar = setButton("Listar", 350, 280, 300, 50, 18));
        
        cadastrar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Cadastrar");
            dispose();
            new JanelaProduto();
        });
        
        listar.addActionListener((e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            dispose();
            new JanelaProduto();
        });

        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaPrincipal();
            dispose();
        });
        
    }

    //Cadastro de Produtos
    private void cadastrar(){
        setLabel("Produto - Cadastrar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        
        add(nomeInput = setLabelInput("Nome: ", null, 100));
        add(marcaInput = setLabelInput("Marca: ", null, 150));
        add(modeloInput = setLabelInput("Modelo: ", null, 200));
        add(unidadeInput = setLabelInput("Unidade: ", null, 250));
        add(anoFabricacaoInput = setLabelInput("Ano Fabricação: ", null, 300));
        add(precoVendaInput = setLabelInput("Preço Venda: ", null, 350));

        confirmar();
        confirm.setCor(Color.WHITE, Color.GREEN);
        confirm.setEnabled(true);      

        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaProduto();
            dispose();
        });
    }

    //Listar os Produtos
    private void listar(){
        editarBotao();
        editar.addActionListener((ActionEvent e) -> {
            new JanelaProdutoEditarExcluir(indexListaP);
            dispose();
        });

        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            new JanelaProduto();
            dispose();
        });

        setLabel("Produto - Listar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        add(nomeInput = setLabelInput("Pesquise pelo nome: ", "", 100));
        add(pesquisar = new Botao("Pesquisar", 750, 100, 120, 30, 15));
        
        DefaultListModel<String> modelC = new DefaultListModel<>();
        DefaultListModel<String> modelN = new DefaultListModel<>();
        DefaultListModel<String> modelP = new DefaultListModel<>();

        for (Produto item : produtos) {
            modelC.addElement(Integer.toString(item.getCodProduto()));
            modelN.addElement(item.getNome());
            modelP.addElement(Double.toString(item.getPrecoVenda()));
        }

        setLabel("Código", 300, 170, 100, 30, 20, SwingConstants.CENTER);
        setLabel("Nome", 400, 170, 200, 30, 20, SwingConstants.CENTER);
        setLabel("R$", 600, 170, 100, 30, 20, SwingConstants.CENTER);

        listaC = new Lista(modelC, 300, 200, 100, 200, 18);
        listaN = new Lista(modelN, 400, 200, 200, 200, 18);
        listaP = new Lista(modelP, 600, 200, 100, 200, 18);
        
        add(listaC);
        add(listaN);
        add(listaP);

        listaC.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaN.setSelectedIndex(listaC.getSelectedIndex());
                listaP.setSelectedIndex(listaC.getSelectedIndex());
                indexListaP = listaP.getSelectedIndex();
                editar.setEnabled(true);
                editar.setCor(Color.BLACK, Color.YELLOW);
            }
        });
        listaN.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaC.setSelectedIndex(listaN.getSelectedIndex());
                listaP.setSelectedIndex(listaN.getSelectedIndex());
            }
        });
        listaP.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listaC.setSelectedIndex(listaP.getSelectedIndex());
                listaN.setSelectedIndex(listaP.getSelectedIndex());
            }
        });
        
        pesquisar.addActionListener((ActionEvent e) -> {
            editar.setEnabled(false);
            editar.setCor(Color.BLACK, Color.DARK_GRAY);

            nome = nomeInput.getText();
            ArrayList<Produto> prod = produtoDAO.pesquisar(nome);
            if(modelC != null && modelN != null && modelP != null){
                modelC.clear();
                modelN.clear();
                modelP.clear();
                if(prod!=null){
                    for(Produto p : prod){
                        modelC.addElement(Integer.toString(p.getCodProduto()));
                        modelN.addElement(p.getNome());
                        modelP.addElement(Double.toString(p.getPrecoVenda()));
                    }
                }
            }   
        });
    }

    //Botão de confirmar
    private void confirmar(){
        confirm = new Botao("Criar", 350, 490, 300, 50, 18);
        confirm.setEnabled(false);
        confirm.setCor(Color.BLACK, Color.DARK_GRAY);
        add(confirm);
        
            
        confirm.addActionListener((ActionEvent e) -> {
            boolean verificaDados = true;

            //verifica se os campos se não estão vazios
            if(!isString(nomeInput.getText())){
                verificaDados = false;
                alertaVerifique("Nome");
            } else if(!isString(marcaInput.getText())){
                verificaDados = false;
                alertaVerifique("Marca");
            } else if(!isString(modeloInput.getText())){
                verificaDados = false;
                alertaVerifique("Modelo");
            } else if(!isString(unidadeInput.getText())){
                verificaDados = false;
                alertaVerifique("Unidade");
            } else if(!isInt(anoFabricacaoInput.getText())){
                verificaDados = false;
                alertaVerifique("Ano de Fabricação");
            } else if(!isDouble(precoVendaInput.getText())){
                verificaDados = false;
                alertaVerifique("Preço de venda");
            } else {
                for(Produto item : produtos){
                    if(item.getNome().toUpperCase().equals(nomeInput.getText().toUpperCase())){
                        verificaDados = false;
                        alertaVerifique("Nome");
                    }
                }
            }
            
            if(verificaDados){
                UIManager.put("OptionPane.yesButtonText", "Cadastrar");
                UIManager.put("OptionPane.noButtonText", "Cancelar");

                int confirmDialogResult = JOptionPane.showConfirmDialog(
                            null,
                            ("Deseja cadastrar um novo produto?"),
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION
                );

                if( confirmDialogResult == JOptionPane.YES_OPTION) {
                    String prod = produtoDAO.addProd(nomeInput.getText(), marcaInput.getText(), modeloInput.getText(), unidadeInput.getText(), anoFabricacaoInput.getText(), precoVendaInput.getText());
                    if(prod.isEmpty()){
                        dispose();
                        new JanelaProduto();
                        JOptionPane.showMessageDialog(null, "O produto foi cadastrado!");
                    }else{
                        JOptionPane.showMessageDialog(null, prod);
                    }

                }
            }
        });
    }

    //Inicio das Ferramentas
    private void setLabel(String msgEtiqueta, int x,int y, int width, int height, int letterSize, int alinhamento){
        add(new Etiqueta(msgEtiqueta, x, y, width, height, letterSize, alinhamento));
    }
    
    private CampoInput setLabelInput(String msgEtiqueta, String msgCampoInput, int y){
        add(new Etiqueta(msgEtiqueta, 180, y, 200, 30, 18, SwingConstants.RIGHT));
        return new CampoInput(msgCampoInput, 400, y, 350, 30, 15);
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
    
    private boolean isInt(String txt){
        try {
            int i = Integer.parseInt(txt);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //Fim das Ferramentas
    
    //Botão de Voltar
    @Override
    public void voltar() {
        voltar = new Botao("Voltar", 350, 550, 300, 50, 18);
        voltar.setCor(Color.BLACK, Color.LIGHT_GRAY);
        add(voltar);
    }

    //Botão Home
    public void paginaPrincipal() {
        home = new Botao("Home", 0, 0, 100, 50, 18);
        home.setCor(Color.BLACK, Color.LIGHT_GRAY);
        add(home);
        home.addActionListener((ActionEvent e) -> {
            new JanelaPrincipal();
            dispose();
        });
    }
    
    //Botão de Editar
    private void editarBotao() {
        editar = new Botao("Editar", 350, 490, 300, 50, 18);
        editar.setEnabled(false);
        editar.setCor(Color.BLACK, Color.DARK_GRAY);
        add(editar);
    }
}
