package caixas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import lib.*;
import lib.interfaces.BotoesUteis;
import produto.Produto;
import produto.ProdutoDAO;
import trabalhooficinamecanica.TrabalhoOficinaMecanica;

public class JanelaProdutoEditarExcluir extends JFrame implements BotoesUteis {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private ArrayList<Produto> produtos = produtoDAO.getArrayListProduto();
    private Produto produto;
    private Botao voltar, confirm, excluir;
    private CampoInput nomeInput, marcaInput, modeloInput, unidadeInput, anoFabricacaoInput, precoVendaInput;

    private final int windowWidth = 1000;
    private final int windowHeight = 700;

    public JanelaProdutoEditarExcluir(int i){
        this.produto = produtos.get(i);

        setTitle("Produto");
        setSize(windowWidth, windowHeight); //define o tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa quando fecha a janela no X
        setResizable(false); //impede de ajusta o tadmanho da janela com o mouse
        setLocationRelativeTo(null); //centraliza a janela no meio da tela

        
        setLabel("Produto - Editar", 0, 40, windowWidth, 30, 22, SwingConstants.CENTER);
        
        add(nomeInput = setLabelInput("Nome: ", produto.getNome(), 100));
        add(marcaInput = setLabelInput("Marca: ", produto.getMarca(), 150));
        add(modeloInput = setLabelInput("Modelo: ", produto.getModelo(), 200));
        add(unidadeInput = setLabelInput("Unidade: ", produto.getUnidade(), 250));
        add(anoFabricacaoInput = setLabelInput("Ano Fabricação: ", Integer.toString(produto.getAnoFabricacao()), 300));
        add(precoVendaInput = setLabelInput("Preço Venda: ", Double.toString(produto.getPrecoVenda()), 350));

        confirmar();

        excluir();


        voltar();
        voltar.addActionListener((ActionEvent e) -> {
            TrabalhoOficinaMecanica.setCondicao("Listar");
            new JanelaProduto();
            dispose();
        });   
        
        //JFRAME
        setLayout(null); //permite a edição do layout do botão
        setVisible(true); // define a visibilidade da tela
    };
    
    //Inicio das Ferramentas
    private void setLabel(String msgEtiqueta, int x,int y, int width, int height, int letterSize, int alinhamento){
        add(new Etiqueta(msgEtiqueta, x, y, width, height, letterSize, alinhamento));
    }
    
    private CampoInput setLabelInput(String msgEtiqueta, String msgCampoInput, int y){
        add(new Etiqueta(msgEtiqueta, 350, y, 150, 30, 18, SwingConstants.RIGHT));
        return new CampoInput(msgCampoInput, 500, y, 150, 30, 15);
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
    
    
    //Botão de Excluir
    private void excluir(){
        excluir = new Botao("Excluir", 525, 490, 250, 50, 18);
        excluir.setEnabled(true);
        excluir.setCor(Color.BLACK, Color.RED);
        add(excluir);
        
        excluir.addActionListener((ActionEvent e) -> {
            UIManager.put("OptionPane.yesButtonText", "Excluir");
            UIManager.put("OptionPane.noButtonText", "Cancelar");

            int confirmDialogResult = JOptionPane.showConfirmDialog(
                    null,
                    ("Deseja excluir o produto '" + produto.getNome() + "' ?"),
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );   

            if( confirmDialogResult == JOptionPane.YES_OPTION) {
                boolean retorno = produtoDAO.excluir(produto);
                if(retorno){
                    TrabalhoOficinaMecanica.setCondicao("Listar");
                    dispose();
                    new JanelaProduto();
                    JOptionPane.showMessageDialog(null, "O produto foi excluido!");
                } else {
                    JOptionPane.showMessageDialog(null, "O produto não foi excluido!");
                }
            }
        });
        
    }
    
    //Botão de Criar
    private void confirmar(){
        confirm = new Botao("Salvar", 225, 490, 250, 50, 18);
        confirm.setEnabled(true);
        confirm.setCor(Color.BLACK, Color.GREEN);  
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
                    if(!produto.equals(item)){
                        if(item.getNome().toUpperCase().equals(nomeInput.getText().toUpperCase())){
                            verificaDados = false;
                            alertaVerifique("Nome");
                        }
                    }
                }
            }
            
            if(verificaDados){
                UIManager.put("OptionPane.yesButtonText", "Cadastrar");
                UIManager.put("OptionPane.noButtonText", "Cancelar");

                int confirmDialogResult = JOptionPane.showConfirmDialog(
                            null,
                            ("Deseja alterar o produto?"),
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION
                );

                if( confirmDialogResult == JOptionPane.YES_OPTION) {
                    boolean retorno = produtoDAO.editar(produto, new Produto(nomeInput.getText(), marcaInput.getText(), modeloInput.getText(), unidadeInput.getText(), Integer.parseInt(anoFabricacaoInput.getText()), Double.parseDouble(precoVendaInput.getText())));
                    
                    if(retorno){
                        TrabalhoOficinaMecanica.setCondicao("Listar");
                        dispose();
                        new JanelaProduto();
                        JOptionPane.showMessageDialog(null, "O produto '" + produto.getCodProduto() +"' foi alterado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "O produto não foi excluido!");
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
    };

}
