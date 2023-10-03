/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 08220186
 */
public class Porta {
    double altura;
    double largura;
    double espessura;
    boolean estado;
    String cor;
    
//    Porta(){
//        this.altura = 0.0;
//        this.largura = 0.0;
//        this.espessura = 0.0;
//        this.estado = false;
//        this.cor = null;
//    }
    

    //Podemos ter varios construtores nos quais voce define qual vai usar pelos parametros que instanciam um objeto.
    //Construtor tem sempre o nome da classe
    //O this constructor sempre vai do construtor com mais argumentos pro com menos argumentos
    
    Porta( double altura, double largura, double espessura, String cor){
        this(altura, largura, altura); //Chamada de construtor
        this.cor = cor;
    }
    
    //Organiza pelos parametros de entrada, distingue e faz a escolha automatica de construtor
    //Cuidar para não gerar um sobrecarga de construtor
    Porta( double altura, double largura, double espessura, String cor, boolean estado){
        this(altura, largura, espessura, cor);
        this.estado = estado;
    }
    
    //O construtor menor sempre carrega com ele as informações que serão repetidas nos demais construtores.
    Porta( double altura, double largura, double espessura){
        this.altura = altura;
        this.largura = largura;
        this.espessura = espessura;
        this.cor = "Natural";
    }
    
    void abrir(){
        if(this.estado == false ){
            this.estado = true;
        } else {
            System.out.println("A porta ja esta aberta");
        }
    }
        
    void fechar(){
        if(this.estado == true ){
            this.estado = false;
        } else {
            System.out.println("A porta ja esta fechada");
        }
    }
    
    void pintar(String novaCor){
        this.cor = novaCor;
    }
    
    Porta clonar(){
        //opção mais usual
        //Porta x = new Porta(this.altura, this.espessura, this.largura, this.cor, this.estado);
        
        //Existe essa opção, normalmente usada quando há uma instancia vazia
        Porta x = new Porta();
        x.altura = this.altura;
        x.cor = this.cor;
        x.espessura = this.espessura;
        x.estado = this.estado;
        x.largura = this.largura;
        
        return x;
    }
    
    void pintarLote (Porta[] lote, String cor){
        for(int i = 0; i < lote.length; i++){
            lote[i].cor = cor;
        }
    }
    
}
