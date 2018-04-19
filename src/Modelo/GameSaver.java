/*
 * Archivos que se van a manejar:
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class GameSaver {
    String archivoCartones;
    String archivoBolas;

    public GameSaver() {
        archivoCartones = "Bingo.txt";
        archivoBolas = "Bolas.txt";
    }

    public GameSaver(String archivoCartones, String archivoBolas) {
        this.archivoCartones = archivoCartones;
        this.archivoBolas = archivoBolas;
    }

    public String getArchivoCartones() {
        return archivoCartones;
    }

    public void setArchivoCartones(String archivoCartones) {
        this.archivoCartones = archivoCartones;
    }

    public String getArchivoBolas() {
        return archivoBolas;
    }

    public void setArchivoBolas(String archivoBolas) {
        this.archivoBolas = archivoBolas;
    }
    
    public void guardarJugador(Jugador c) throws IOException{
        // Guardando Juego
        File file = new File (archivoCartones);
        BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
        out.write(c.getCantidadCartones() + "");
        out.newLine();
        for(int i = 0; i < c.getCantidadCartones(); i++){
            for(int n = 0; n < 5; n++){
                for(int j = 0; j<5; j++){
                    out.write(c.getCartones().get(i).getCarton()[n][j].getNumero() + "");
                    out.newLine();
                }
            }
        }
        out.close();
    }
    
    public void guardarSeleccionados(Tombola t) throws IOException{
        // Guardando lista de bolas que ya salieron
        File file = new File (archivoBolas);
        BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
        for(int i = 0; i < t.getsCantidad(); i++){
            out.write(t.getSeleccionados()[i] + "");
            out.newLine(); 
        }
        out.close();
    }
    

    public String toString() {
        return "GameSaver{" + "archivoCartones=" + archivoCartones + ", archivoBolas=" + archivoBolas + '}';
    }
    
    
}
