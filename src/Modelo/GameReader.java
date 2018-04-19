/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class GameReader {
    String archivoBola;
    String archivoJugador;

    public GameReader() {
        archivoBola = "Bolas.txt";
        archivoJugador = "Bingo.txt";
    }

    public GameReader(String archivoBola, String archivoJugador) {
        this.archivoBola = archivoBola;
        this.archivoJugador = archivoJugador;
    }

    public String getArchivoBola() {
        return archivoBola;
    }

    public void setArchivoBola(String archivoBola) {
        this.archivoBola = archivoBola;
    }

    public String getArchivoJugador() {
        return archivoJugador;
    }

    public void setArchivoJugador(String archivoJugador) {
        this.archivoJugador = archivoJugador;
    }

    public ArrayList<Integer> recuperarCartones() throws IOException{
        //Leyendo cartones
        ArrayList<Integer> buffer = new ArrayList<>();
        try {
            File f = new File(archivoJugador);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                buffer.add(Integer.parseInt(readLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
       
    public int[] recuperarBolas() throws IOException{
        //Leyendo bolas que ya salieron
        int[] temp = new int[75];
        int cantidad = 0;
        try {
            File f = new File(archivoBola);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                if(cantidad < 75){
                    temp[cantidad] = Integer.parseInt(readLine);
                    cantidad++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  
        return temp;
    }
}

