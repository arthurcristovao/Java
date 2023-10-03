package servico;

import java.util.ArrayList;
import java.util.Iterator;
import lib.interfaces.DAO;
import pessoa.Cliente;
import pessoa.Funcionario;

public class ServicoDAO implements DAO {
    private static ArrayList<Servico> cadastro = new ArrayList<>();
    ServicoDAO servicoDAO;

    public Servico getServicoPorIndex(int index){
        return cadastro.get(index);
    }
    
    public static ArrayList<Servico> getArrayListServico(){
        return cadastro;
    }
    
    public void setServicoDAO(ServicoDAO servico){
        this.servicoDAO = servico;
    }
    
    public ServicoDAO getServicoDAO(){
        return servicoDAO;
    }
    
    public int getSize(){
        return cadastro.size();
    }
    
    @Override
    public boolean inserir(Object obj) {
        if (obj != null) {
            Servico servico = (Servico) obj;
            cadastro.add(servico);
            return true;
        }
        return false;
    }
    @Override
    public boolean excluir(Object obj) {
        if (obj != null) {
            Servico servico = (Servico) obj;
            cadastro.remove(servico);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Servico> pesquisar(Object obj) {
        ArrayList<Servico> temp = new ArrayList<>();
        if (obj !=null){
            String nome = (String) obj;
            for(Servico item : cadastro){
                if(item.getCliente().getNome().toUpperCase().indexOf(nome.toUpperCase()) != -1){
                    temp.add(item);
                }
            }
            return temp;
        }
        return null;
    }

    public Servico retornaUm(Object obj){
        if (obj !=null){
            int id = retornaIntSeTrue(obj);
            if(id!=-1){
                for (int i =0; i< cadastro.size(); i++){
                    Servico servico = (Servico) cadastro.get(i);
                    if (servico.getCodServico() == id){
                        return servico;
                    }
                }
            }
        }

        return null;
    }

    public boolean verifiServico(Object obj){
        if (obj !=null){
            int id = retornaIntSeTrue(obj);
            if(id!=-1){
                for (int i =0; i< cadastro.size(); i++){
                    Servico servico = (Servico) cadastro.get(i);
                    if (servico.getCodServico() == id){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //item -- inicio
    public boolean inserirItem(Object obj, Object obj2) {
        try {
            Servico servico = (Servico) obj;
            ServicoItem servicoItem = (ServicoItem) obj2;
            servico.setPeca(servicoItem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeItem(Object obj, Object obj2) {
        try {
            Servico servico = (Servico) obj;
            ServicoItem servicoItem = (ServicoItem) obj2;
            servico.getPecas().remove(servicoItem);
            servico.calculaTotalServico();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //item -- fim

    @Override
    //Em obj está a Pessoa com os dados novos exceto o nome
    public boolean editar(Object obj, Object obj1) {
        try{
            Servico servicoAntigo = (Servico) obj;
            Servico servicoNovo = (Servico) obj1;
            servicoAntigo.setCliente(servicoNovo.getCliente());
            servicoAntigo.setAtendente(servicoNovo.getAtendente());
            servicoAntigo.setMecanico(servicoNovo.getMecanico());
            return true;
        }catch (Exception e) {
            return false;
        }
        
    }
        
    public void listar(){
        Iterator it = cadastro.iterator();
        while (it.hasNext()){
            //System.out.println(it.next());
        }
        
    }

    private int retornaIntSeTrue(Object ob){
        try{
            return Integer.parseInt((String) ob);
        } catch (NumberFormatException e) {
            return -1;
        } catch (ClassCastException e) {
            return (int) ob;
        }
    }
}
