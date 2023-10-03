
public class Bola{
     String marca;
     double tamanho;
     String tipoEsporte;
     public Bola (String esporte, double tamanho){
         this.tipoEsporte = esporte;
         this.tamanho = tamanho;
     }
     public Bola (String marca, String esporte, double tamanho) {
          this (esporte, tamanho);
          this.marca = marca;
     }
     public Bola (String marca, String esporte){
            this.marca = marca;
            this.tipoEsporte = esporte;
     }
     public void mostrar(){
             System.out.println("Esporte = " + tipoEsporte + ", Marca = " + marca + ", Tamanho = " + tamanho);
     }
}

//O que será exibido ao executar a linha 24 ?
//
//Escolha uma:
//a. Na linha 24 tem um erro e, por causa disso, o programa aborta
//b. Esporte = basquete, Marca = Nike, Tamanho = 12
//c. Na linha 10 tem um erro e, por causa disso, o programa aborta
//d. Esporte = basquete, Marca = Nike, Tamanho = 12.0