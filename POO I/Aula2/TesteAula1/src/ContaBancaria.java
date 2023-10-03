/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 08220186
 */
public class ContaBancaria {
    int agencia;
    int numero;
    int digito;
    double saldo;
    
    void depositar(double valor){
        saldo += valor;
    }
    
    void sacar(double valor){
        saldo -= valor;
    }
    
    void transferir(double valor, ContaBancaria destino){
        this.sacar(valor);
        destino.depositar(valor);
    }
}
