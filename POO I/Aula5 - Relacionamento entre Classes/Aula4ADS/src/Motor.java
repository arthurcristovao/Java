/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mk
 */
public class Motor {
    
    private String marca;
    private int potencia;
    private double litragem;

    public Motor(String marca, int potencia, double litragem) {
        this.marca = marca;
        this.potencia = potencia;
        this.litragem = litragem;
    }
    
    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the potencia
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * @param potencia the potencia to set
     */
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    /**
     * @return the litragem
     */
    public double getLitragem() {
        return litragem;
    }

    /**
     * @param litragem the litragem to set
     */
    public void setLitragem(double litragem) {
        this.litragem = litragem;
    }

    @Override
    public String toString() {
        return "Motor{" + "marca=" + marca + ", potencia=" + potencia + ", litragem=" + litragem + '}';
    }
    
    
    
}
