package veiculo;

import java.util.ArrayList;
import java.util.Iterator;
import lib.interfaces.DAO;


public class CarroDAO implements DAO{
    private static ArrayList<Carro> cadastro = new ArrayList<>();
    CarroDAO veiDAO;

    public Carro getCarroPorIndex(int index){
        return cadastro.get(index);
    }
    
    public static ArrayList<Carro> getArrayListCarro(){
        return cadastro;
    }
    
    public void setCarroDAO(CarroDAO f){
        this.veiDAO = f;
    }
    
    public CarroDAO getCarroDAO(){
        return veiDAO;
    }
    
    public int getSize(){
        return cadastro.size();
    }
    
    @Override
    public boolean inserir(Object obj) {
        
        if (obj != null) {
            Carro f = (Carro) obj;
            cadastro.add(f);
            return true;
        }
        return false;
    }
    @Override
    public boolean excluir(Object obj) {
        if (obj != null) {
            Carro f = (Carro) obj;
            cadastro.remove(f);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Carro> pesquisar(Object obj) {
        ArrayList<Carro> temp = new ArrayList<>();
        if (obj !=null){
            String placa = (String) obj;
            for (int i =0; i< cadastro.size(); i++){
                Carro carro = (Carro) cadastro.get(i);
                if ((carro.getPlaca().indexOf(placa.toUpperCase())) != -1){
                    temp.add(carro);
                }
            }
            return temp;
        }
        return null;
    }

    public Carro retornaUm(Object obj){
        if (obj !=null){
            String placa = (String) obj;
            for (int i =0; i< cadastro.size(); i++){
                Carro carro = (Carro) cadastro.get(i);
                if ((carro.getPlaca().equals(placa))){
                    return carro;
                }
            }
        }

        return null;
    }

    public boolean verifiCarro(Object obj){
        if (obj !=null){
            String placa = (String) obj;
            for (int i =0; i< cadastro.size(); i++){
                Carro carro = (Carro) cadastro.get(i);
                if ((carro.getPlaca().equals(placa))){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean editar(Object velho, Object novo) {
        try {
            Carro carroNovo = (Carro) novo;
            Carro carroVelho = (Carro) velho;
            int index = cadastro.indexOf(carroVelho);
            cadastro.set(index, carroNovo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
        
    public void listar(){
        Iterator it = cadastro.iterator();
        while (it.hasNext()){
            //System.out.println(it.next());
        }
        
    }
    
}
