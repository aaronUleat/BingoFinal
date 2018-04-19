/*
 *
 */
package Modelo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Tombola {
    public int[] tombola;
    int tCantidad;
    int tTamano;
    public int[] seleccionados;
    int sCantidad;
    int sTamano;

    public Tombola(int[] tombola, int[] seleccionados) {
        this.tombola = tombola;
        this.seleccionados = seleccionados;
    }

    public Tombola() {
        this.tombola = new int[75];
        this.seleccionados = new int[75];
        tCantidad = 0;
        tTamano = 75;
        sCantidad = 0;
        sTamano = 75;
    }

    public int[] getTombola() {
        return tombola;
    }

    public void setTombola(int[] tombola) {
        this.tombola = tombola;
    }

    public int[] getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(int[] seleccionados) {
        this.seleccionados = seleccionados;
    }

    public int gettCantidad() {
        return tCantidad;
    }

    public void settCantidad(int tCantidad) {
        this.tCantidad = tCantidad;
    }

    public int gettTamano() {
        return tTamano;
    }

    public void settTamano(int tTamano) {
        this.tTamano = tTamano;
    }

    public int getsCantidad() {
        return sCantidad;
    }

    public void setsCantidad(int sCantidad) {
        this.sCantidad = sCantidad;
    }

    public int getsTamano() {
        return sTamano;
    }

    public void setsTamano(int sTamano) {
        this.sTamano = sTamano;
    }
    

    //Este metodo genera la lista de numeros seleccionados
    public void rellenarTombola(){
        for (int i = 0; i < tTamano; i++){
            tombola[i] = i+1;
            tCantidad++;
        }
    }
    
    //Elije una bola de manera aleatoria y la saca de la tombola para que no pueda volver a salir
    public int sacarBola(){
        int resultado = 0;
        int pos = ThreadLocalRandom.current().nextInt(0, tCantidad);
        resultado = tombola[pos];
        for(int i = pos; i < tCantidad-1; i++){
            tombola[i] = tombola[i+1];
        }
        tCantidad--;
        
        if(sCantidad < sTamano){
            seleccionados[sCantidad] = resultado;
            sCantidad++;
        }
        
        return resultado;
    }
    
    // agrega un numero a la lista de bolas que ya fueron seleccionadas
    public void agregarSeleccionados(int a){
        if(sCantidad < sTamano){
            seleccionados[sCantidad] = a;
            sCantidad++;
        }        
    }
    
    // compara un numero con toda la lista para ver si el elemento pertenece al conjunto
    boolean compara(int num){
        boolean resultado = false;
        for(int i = 0; i< tCantidad; i++){
            if(tombola[i] == num){
                resultado = true;
            }
        }
        return resultado;
    }

    //quita un elemento dado de la tombola
    void quitarDelArreglo(int num){
        int pos = 0;
        if(compara(num)){
            for(int i = 0; i < tCantidad; i++){
                if(tombola[i] == num){
                    pos = i;
                }
            }
            for(int i = pos; i < tCantidad-1; i++){
                tombola[i] = tombola[i+1];
            }
            tCantidad--;
        }
    }

    
    //Realiza la operacion de diferencia entre los conjuntos tombola - seleccionados
    public void diferencia(){
        for(int i = 0; i < sCantidad; i++){
            quitarDelArreglo(seleccionados[i]);
        }
    }
    
    @Override
    public String toString() {
        return "Tombola{" + "tombola=" + tombola + ", seleccionados=" + seleccionados + '}';
    }
    
}
