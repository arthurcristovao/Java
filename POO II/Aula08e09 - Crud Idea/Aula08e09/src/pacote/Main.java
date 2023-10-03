package pacote;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IdeiaDAO ideiaDAO = new IdeiaDAO();

        // Inserir uma nova ideia
        Ideia novaIdeia = new Ideia("Ideia 5", "Está é um descrição", Urgencia.NAO_URGENTE);
        ideiaDAO.inserir(novaIdeia);

        // Deletar uma ideia pelo ID 
        //int idParaDeletar = 1;
        //ideiaDAO.deletar(idParaDeletar);
         

        // Listar todas as ideias
        List<Ideia> todasIdeias = ideiaDAO.listar();
        for (Ideia ideia : todasIdeias) {
            System.out.println(ideia.toString());
        }
    }
}
