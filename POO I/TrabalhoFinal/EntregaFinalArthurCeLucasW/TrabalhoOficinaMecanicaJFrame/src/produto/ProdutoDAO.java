package produto;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lib.interfaces.DAO;

public class ProdutoDAO implements DAO {
    private static ArrayList<Produto> cadastro = new ArrayList<>();
    ProdutoDAO produtoDAO;

    public Produto getProdutoPorIndex(int index){
        return cadastro.get(index);
    }
    
    public static ArrayList<Produto> getArrayListProduto(){
        return cadastro;
    }
    
    public void setProdutoDAO(ProdutoDAO produto){
        this.produtoDAO = produto;
    }
    
    public ProdutoDAO getProdutoDAO(){
        return produtoDAO;
    }
    
    public int getSize(){
        return cadastro.size();
    }

    public String addProd(String nomeInput, String marcaInput, String modeloInput, String unidadeInput, String anoFabricacaoInput, String precoVendaInput){
        int anoFab;
        double precoV;
        if(isInteger(anoFabricacaoInput)&&isDouble(precoVendaInput)){
            anoFab = Integer.valueOf(anoFabricacaoInput);
            precoV = Double.valueOf(precoVendaInput);
            if(!(nomeInput.isEmpty()&&marcaInput.isEmpty()&&modeloInput.isEmpty()&&unidadeInput.isEmpty())){
                
                Produto prod = new Produto(nomeInput, marcaInput, modeloInput, unidadeInput, anoFab, precoV);
                this.inserir(prod);
                return "";
            } else {
                return "Erro!\nVerifique os dados!";
            }
        }else {
            return "Erro!\nVerifique os dados!";
        }
    }

    private boolean isDouble(String texto){
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException  e) {
            return false;
        }
    }

    private boolean isInteger(String texto){
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    @Override
    public boolean inserir(Object obj) {
        if (obj != null) {
            Produto produto = (Produto) obj;
            cadastro.add(produto);
            return true;
        }
        return false;
    }
    @Override
    public boolean excluir(Object obj) {
        if (obj != null) {
            Produto produto = (Produto) obj;
            cadastro.remove(produto);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Produto> pesquisar(Object obj) {
        ArrayList<Produto> temp = new ArrayList<>();
        if (obj !=null){
            String nome = (String) obj;
            for (int i =0; i< cadastro.size(); i++){
                Produto produto = (Produto) cadastro.get(i);
                if (produto.getNome().toUpperCase().indexOf(nome.toUpperCase()) != -1){
                    temp.add(produto);
                }
            }
            return temp;
        }
        return null;
    }

    public Produto retornaUm(Object obj){
        if (obj !=null){
            int id = (int) obj;
            for (int i =0; i< cadastro.size(); i++){
                Produto produto = (Produto) cadastro.get(i);
                if (produto.getCodProduto() == id){
                    return produto;
                }
            }
        }

        return null;
    }

    public boolean verifiProduto(Object obj){
        if (obj !=null){
            int id = (int) obj;
            for (int i =0; i< cadastro.size(); i++){
                Produto produto = (Produto) cadastro.get(i);
                if (produto.getCodProduto() == id){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    //Em obj está a Pessoa com os dados novos exceto o nome
    public boolean editar(Object obj, Object obj2) {
        if (obj !=null){
            Produto produtoNovo = (Produto) obj2;
            Produto produtoVelho = (Produto) obj;
           int index = cadastro.indexOf(produtoVelho);
           cadastro.set(index, produtoNovo);
           return true;
        }
        return false;
    }
        
    public void listar(){
        Iterator it = cadastro.iterator();
        while (it.hasNext()){
            //System.out.println(it.next());
        }
        
    }
}
