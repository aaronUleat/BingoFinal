/*
 * Clase casilla
 * Una casilla contiene numeros que a su vez pueden estar o no tachados
 */
package Modelo;

public class Casilla {
    // Atributos de la clase
    private int numero;
    private boolean tachado;

    // Constructor generico
    public Casilla() {
        numero = 0;
        tachado = false;
    }

    // Constructor con atributos
    public Casilla(int numero, boolean tachado) {
        this.numero = numero;
        this.tachado = tachado;
    }

    // Setters & Getters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isTachado() {
        return tachado;
    }

    public void setTachado(boolean tachado) {
        this.tachado = tachado;
    }

    //El metodo toString esta sobreescrito para mostrar una X si el numero ya fue marcado
    //O el numero si aun no ha sido marcado

    public String toString() {
       if(!tachado){
           return "" + numero;
       }
       else{
           return "X";
       }
    }
}
