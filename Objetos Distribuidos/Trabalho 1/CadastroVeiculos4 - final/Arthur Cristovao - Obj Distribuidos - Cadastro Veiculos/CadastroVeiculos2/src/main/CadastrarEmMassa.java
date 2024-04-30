package main;

import fabricacao.*;
import fabricante.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import veiculo.*;

public class CadastrarEmMassa {
    
    public static void cadastrarVeiculoEmMassa() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        for (int i = 1; i <= 1000; i++) {
            String nome = "Veiculo " + i;
            String cor = "Cor " + i;
            String modelo = "Modelo " + i;

            Veiculo veiculo = new Veiculo(nome, cor, modelo);
            veiculoDAO.insert(veiculo);

            try {
                TimeUnit.MILLISECONDS.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void cadastrarFabricanteEmMassa() {
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        for (int i = 1; i <= 1000; i++) {
            String nome = "Fabricante " + i;
            String paisOrigem = "Pais " + i;

            Fabricante fabricante = new Fabricante(nome, paisOrigem);
            fabricanteDAO.insert(fabricante);

            try {
                TimeUnit.MILLISECONDS.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void cadastrarFabricacaoEmMassa() {
        FabricacaoDAO fabricacaoDAO = new FabricacaoDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        FabricanteDAO fabricanteDAO = new FabricanteDAO();

        ArrayList<Veiculo> veiculos = veiculoDAO.list();
        ArrayList<Fabricante> fabricantes = fabricanteDAO.list();

        if (veiculos.isEmpty() || fabricantes.isEmpty()) {
            System.out.println("Não há veículos ou fabricantes cadastrados para criar fabricações.");
            return;
        }

        for (int i = 0; i < 1000; i++) {
            Veiculo veiculo = veiculos.get(i);
            Fabricante fabricante = fabricantes.get(i); 

            LocalDate dataFabricacao = LocalDate.now().minusDays(i); 

            Fabricacao fabricacao = new Fabricacao(veiculo, fabricante, dataFabricacao, "Brasil");

            fabricacaoDAO.insert(fabricacao);

            try {
                TimeUnit.MILLISECONDS.sleep(2000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
