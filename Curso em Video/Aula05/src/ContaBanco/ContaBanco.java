/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ContaBanco;


/**
 *
 * @author Arthur
 */
//---> CC == CONTA CORRENTE ---> CP == CONTA POUPANÇA

public class ContaBanco {
    public int numConta;
    protected String tipo;
    private String nome;
    private double saldo;
    private boolean status;

    public ContaBanco(double saldo, boolean status) {
        this.saldo = saldo;
        this.status = status;
    }
    
    public void abrirConta(String tipo){
        
        if(tipo == "CC"){
            setTipo(tipo);
            setStatus(true);
            this.saldo = 50;
        } else if ( tipo == "CP"){
            setTipo(tipo);
            setStatus(true);
            this.saldo = 150;
        } else {
            System.out.println("Você Digitou " + tipo + ". Essa opção está indísponivel.");
        }
    }
    
    public void fecharConta(){
        if(this.saldo < 0){
            System.out.println("Essa conta está com débitos ativos.");
        } else if ( this.saldo > 0){
            System.out.println("Essa conta está com dinheiro guardado.");
        } else {
            setStatus(false);
            System.out.println("Sua conta foi fechada. ");
        }
    }
    
    public void depositar(double valor){
        if( this.status == true ){   
            setSaldo(getSaldo() + valor);
            String resultado = String.format("%.2f", valor); //Adiciona 2 caracteres decimais.
            System.out.println("Depositado o valor de R$ " + resultado + ".");
        }
    }
    
    public void sacar(double valor){
        if( this.status == true && this.saldo > valor){
            this.saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente. ");
        }
    }
    
    public void pagarMensal(){
        
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ContaBanco{" + "numConta=" + numConta + ", tipo=" + tipo + ", nome=" + nome + ", saldo=" + saldo + ", status=" + status + '}';
    }
    
    
}
