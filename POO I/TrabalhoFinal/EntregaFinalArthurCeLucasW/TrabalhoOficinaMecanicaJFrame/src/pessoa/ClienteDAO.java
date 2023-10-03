package pessoa;

import java.util.ArrayList;
import java.util.Iterator;
import lib.interfaces.DAO;

public class ClienteDAO implements DAO{
    
    private static ArrayList<Cliente> cadastro = new ArrayList<>();
    ClienteDAO clienteDAO;

    public Cliente getClientePorIndex(int index){
        return cadastro.get(index);
    }
    
    public static ArrayList<Cliente> getArrayListCliente(){
        return cadastro;
    }
    
    public void setClienteDAO(ClienteDAO c){
        this.clienteDAO = c;
    }
    
    public ClienteDAO getClienteDAO(){
        return clienteDAO;
    }
    
    public int getSize(){
        return cadastro.size();
    }
    
    @Override
    public boolean inserir(Object obj) {
        
        if (obj != null) {
            Cliente c = (Cliente) obj;
            cadastro.add(c);
            return true;
        }
        return false;
    }
    @Override
    public boolean excluir(Object obj) {
        if (obj != null) {
            Cliente c = (Cliente) obj;
            cadastro.remove(c);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Cliente> pesquisar(Object obj) {
        ArrayList<Cliente> cliente = new ArrayList<>();
        if (obj !=null){
            String nome = (String) obj;
            for (int i =0; i< cadastro.size(); i++){
                Cliente c = (Cliente) cadastro.get(i);
                if ((c.getNome().toUpperCase().indexOf(nome.toUpperCase())) != -1){
                    cliente.add(c);
                }
            }
            return cliente;
        }
        return null;
    }

    @Override
    //Em obj está a Pessoa com os dados novos exceto o nome
    public boolean editar(Object obj, Object obj2) {
        if (obj !=null){
            Cliente cNovo = (Cliente) obj;
            Cliente cVelho = (Cliente) obj2;
           int index = cadastro.indexOf(cVelho);
           cadastro.set(index, cNovo);
           return true;
        }
        return false;
    }

    public Object retornaUm(Object obj) {
        if (obj !=null){
            String nome = (String) obj;
            for (int i =0; i< cadastro.size(); i++){
                Cliente c = (Cliente) cadastro.get(i);
                if (nome.equals(c.getNome())){
                    return c;
                }
            }
        }
        return null;
    }
        
    public void listar(){
        Iterator it = cadastro.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        
    }
    
}
