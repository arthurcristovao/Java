package pessoa;

import java.util.ArrayList;
import java.util.Iterator;
import lib.interfaces.DAO;


public class FuncionarioDAO implements DAO{
    
    private static ArrayList<Funcionario> cadastro = new ArrayList<>();
    FuncionarioDAO funDAO;
 
//    public static ArrayList<Funcionario> getFuncionarios() {
//        return funcionarios;
//    }

    public Funcionario getFuncionarioPorIndex(int index){
        return cadastro.get(index);
    }
    
    public static ArrayList<Funcionario> getArrayListFuncionario(){
        return cadastro;
    }
    
    public void setFunDAO(FuncionarioDAO f){
        this.funDAO = f;
    }
    
    public FuncionarioDAO getFunDAO(){
        return funDAO;
    }
    
    public int getSize(){
        return cadastro.size();
    }
    
    @Override
    public boolean inserir(Object obj) {
        
        if (obj != null) {
            Funcionario f = (Funcionario) obj;
            cadastro.add(f);
            return true;
        }
        return false;
    }
    @Override
    public boolean excluir(Object obj) {
        if (obj != null) {
            Funcionario f = (Funcionario) obj;
            cadastro.remove(f);
            return true;
        }
        return false;
    }

    @Override
    //Em obj está o critério de pesquisa
    //Em obj vai estar o nome da pessoa
    public ArrayList<Funcionario> pesquisar(Object obj) {
        ArrayList<Funcionario> temp = new ArrayList<>();
        if (obj !=null){
            String nome = (String) obj;
            for (int i =0; i< cadastro.size(); i++){
                Funcionario f = (Funcionario) cadastro.get(i);
                if ((f.getNome().toUpperCase().indexOf(nome.toUpperCase())) != -1){
                    temp.add(f);
                }
            }
            return temp;
        }
        return null;
    }

    public Funcionario retornaUm(Object obj){
        if (obj !=null){
            String nome = (String) obj;
            for (int i =0; i< cadastro.size(); i++){
                Funcionario f = (Funcionario) cadastro.get(i);
                if ((f.getNome().equals(nome))){
                    return f;
                }
            }
        }

        return null;
    }

    @Override
    //Em obj está a Pessoa com os dados novos exceto o nome
    public boolean editar(Object obj, Object obj2) {
        if (obj !=null){
            Funcionario fVelho = (Funcionario) obj;
            Funcionario fNovo = (Funcionario) obj2;
           int index = cadastro.indexOf(fVelho);
           cadastro.set(index, fNovo);
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
