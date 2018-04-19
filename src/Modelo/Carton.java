/*
 * Clase carton
 * Un carton contiene una matriz de casillas
 */
package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Carton {
    private Casilla[][] Carton; //Este atributo contiene las casillas
    private boolean ganador;
    
    //Atributos necesarios para que no se repitan los numeros en un carton
    private int[] B;
    private int[] I;
    private int[] N;
    private int[] G;
    private int[] O;
    
    int bcant;
    int icant;
    int ncant;
    int gcant;
    int ocant;
    
    //Constructor generico
    public Carton() {
        this.Carton = new Casilla[5][5];
        ganador = false;
        B = new int[15];
        I = new int[15];
        N = new int[15];
        G = new int[15];
        O = new int[15];
        bcant = 0;
        icant = 0;
        ncant = 0;
        gcant = 0;
        ocant = 0;
        rellenarConjuntos();
    }

    //Constructor con atributos
    public Carton(Casilla[][] Carton, int act, boolean gan) {
        this.Carton = Carton;
        ganador = gan;
    }
    

    //Getters & Setters
    public Casilla[][] getCarton() {
        return Carton;
    }

    public void setCarton(Casilla[][] Carton) {
        this.Carton = Carton;
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }

    //Rellenar los conjuntos B - I - N - G - O con sus respectivos numeros
    public void rellenarConjuntos(){
        for(int i = 0; i < 15; i++){
            B[i] = i+1;
            bcant++;
        }
        for(int i = 0; i < 15; i++){
            I[i] = i+16;
            icant++;
        }
        for(int i = 0; i < 15; i++){
            N[i] = i+31;
            ncant++;
        }
        for(int i = 0; i < 15; i++){
            G[i] = i+46;
            gcant++;
        }
        for(int i = 0; i < 15; i++){
            O[i] = i+61;
            ocant++;
        }
    }
    
    //Llenar bingo
    //Este metodo genera un numero aleatorio entre:
    // 01 y 15 si el puntero esta posicionado en la columna de la B
    // 16 y 30 si el puntero esta posicionado en la columna de la I
    // 31 y 45 si el puntero esta posicionado en la columna de la N
    // 46 y 60 si el puntero esta posicionado en la columna de la G
    // 61 y 75 si el puntero esta posicionado en la columna de la O
    public void llenarBingo(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                int x = 0;
                int y = 0;
                if(j == 0){
                    // Genera valores aleatorios entre 1 y 15
                    x = ThreadLocalRandom.current().nextInt(0, bcant);
                    y = B[x];
                    for(int n = x; n < bcant-1; n++){
                        B[n] = B[n+1];
                    }
                    bcant--;
                }
                if(j == 1){
                    // Genera valores aleatorios entre 1 y 15
                    x = ThreadLocalRandom.current().nextInt(0, icant);
                    y = I[x];
                    for(int n = x; n < icant-1; n++){
                        I[n] = I[n+1];
                    }
                    icant--;
                }
                if(j == 2){
                    // Genera valores aleatorios entre 1 y 15
                    x = ThreadLocalRandom.current().nextInt(0, ncant);
                    y = N[x];
                    for(int n = x; n < ncant-1; n++){
                        N[n] = N[n+1];
                    }
                    ncant--;
                }
                if(j == 3){
                    // Genera valores aleatorios entre 1 y 15
                    x = ThreadLocalRandom.current().nextInt(0, gcant);
                    y = G[x];
                    for(int n = x; n < gcant-1; n++){
                        G[n] = G[n+1];
                    }
                    gcant--;
                }
                if(j == 4){
                    // Genera valores aleatorios entre 1 y 15
                    x = ThreadLocalRandom.current().nextInt(0, ocant);
                    y = O[x];
                    for(int n = x; n < ocant-1; n++){
                        O[n] = O[n+1];
                    }
                    ocant--;
                }

                Casilla cc = new Casilla(y, false);
                Carton[i][j] = cc;
            }
        }
    }
    
    //Limpiar el carton
    public void resetCarton(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                Carton[i][j].setTachado(false);
            }
        }
    }
    
    //
    public void llenarDesdeLista(ArrayList<Integer> lista){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                Casilla cc = new Casilla(lista.get(i * 5 + j), false);
                Carton[i][j] = cc;
            }
        }
    }
    
    //Saber si el carton esta lleno
    public boolean verificarCarton(){
        boolean result = true;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                result = result && Carton[i][j].isTachado();
            }
        }
        if(result){
            ganador = true;
        }
        return result;
    }
    
    //Revisa si el numero indicado esta en el carton y si esta lo tacha
    public void tacharNumero(int num){
        for(int i = 0; i< 5; i++){
            for(int j = 0; j< 5; j++){
                if(num == Carton[i][j].getNumero()){
                    Carton[i][j].setTachado(true);
                }
            }
        }
    }
    

    //El metodo ToString esta sobreescrito para imprimir el carton de bingo de la manera correcta
    // Esta es la funcion
    @Override
    public String toString() {
        String x =" --------------------  \n";
        x +=      "| B | I | N | G | O  | \n";
        x +=      "|--------------------| \n";
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                int lenght = (int)Math.log10(Carton[i][j].getNumero())+1;
                if(lenght < 2){
                    x += "| 0" + Carton[i][j].toString();
                }
                else{
                    x += "| " + Carton[i][j].toString();
                }
            }
            x += " | \n";
        }
        x += " -------------------- \n";
        return x;
    }    
}

