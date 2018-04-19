/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;


public class Jugador {
    private int cantidadCartones;
    private ArrayList<Carton> Cartones;

    public Jugador() {
        cantidadCartones = 0;
        Cartones = new ArrayList<>();
    }

    public Jugador(int cantidadCartones, ArrayList<Carton> Cartones) {
        this.cantidadCartones = cantidadCartones;
        this.Cartones = Cartones;
    }
    
    //Recupera todos los cartones de un jugador dado un array de numeros en forma unidimensional
    public void recuperarJugador(ArrayList<Integer> data){
        cantidadCartones = data.get(0);
        ArrayList<Integer> temp = new ArrayList<>();
        System.out.print(data.size() + "");
        int iteracion = 0;
        int inicio = 1;
        while(iteracion < cantidadCartones){
            temp.clear();
            for(int i = inicio; i < inicio+25; i++){
                temp.add(data.get(i));
            }
            Carton cc = new Carton();
            cc.llenarDesdeLista(temp);
            Cartones.add(cc);
            iteracion ++;
            inicio += 25;
        }           
    }

    public int getCantidadCartones() {
        return cantidadCartones;
    }

    public void setCantidadCartones(int cantidadCartones) {
        this.cantidadCartones = cantidadCartones;
    }

    public ArrayList<Carton> getCartones() {
        return Cartones;
    }

    public void setCartones(ArrayList<Carton> Cartones) {
        this.Cartones = Cartones;
    }
    
    //Genera una cantidad de cartones para el jugador y los guarda en una lista
    public void generarCartones(int cantidad){
        if(cantidad <= 0){
            System.out.print("La cantidad de cartones debe ser mayor a cero");
        }
        else{
            cantidadCartones = cantidad;
            for(int i = 0; i < cantidadCartones; i++){
                Carton c = new Carton();
                c.llenarBingo();
                Cartones.add(c);
            }
        }
    }

    //Revisa si un numero determinado esta en alguno de los cartones
    public void revisar(int num){
        for(int i = 0; i < cantidadCartones; i++){
            Cartones.get(i).tacharNumero(num);
        }
    }
    
    // Verifica todos los cartones para ver si alguno gano
    public boolean gano(){
        boolean result = false;
        for(int i = 0; i < cantidadCartones; i++){
            result = result || Cartones.get(i).verificarCarton();
        }
        return result;
    } 
    
    // Dado un conjunto de numeros enteros, se revisa si los numeros dados estan en algun carton del jugador
    public void compararConLista(int[] arreglo){
        for(int i = 0; i < arreglo.length; i++){
            revisar(arreglo[i]);
        }
    }
    

    public String toString() {
        return "Jugador{" + "cantidadCartones=" + cantidadCartones + ", Cartones=" + Cartones + '}';
    }
}
