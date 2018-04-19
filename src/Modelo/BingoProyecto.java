//****************************
// * EXPLICACION DE LOS ARCHIVOS
// * ***************************
// * EL PROGRAMA USA DOS ARCHIVOS
// * Bingo.txt guarda la cantidad de cartones del juego y posteriormente los numeros en orden por carton
// * Bolas.txt guarda los numeros que ya han salido de la tombola
// * Cuando se lee Bingo.txt se meten todos los numeros en una lista de tipo ArrayList
// * El primer elemento corresponde a la cantidad de cartones que se deben construir
// * luego de eso se toman grupos de 25 numeros, que se mapean a una matriz bidimensional
// * Cuando se lee Bolas.txt se guardan todos los numeros que deberian estar tachados en los cartones
// * posteriormente se colocan en el conjunto de seleccionados en la clase Tombola
// * se llena la tombola y luego se comparan ambos conjuntos con el objetivo de quitar todos los numeros que ya fueron seleccionados de la tombola
// * cuando este proceso se termina funciona igual que el jugar.

// *********************************
// REGLAS DEL JUEGO
// *********************************
// Un juego termina si: Algun carton esta lleno o el usuario selecciona la opcion de salir
// Cada vez que el usuario selecciona salir se le advierte que todo progreso no guardado se perdera
// de este modo el usuario podria arrepentirse y devolverse para guardar
// Si se elige la opcion de comenzar un nuevo juego se debe introducir la cantidad de cartones que jugaran
// Si se elige la opcion de cargar juego se usaran la cantidad de cartones que tenga el archivo que fue guardado por ultima vez
// Si el archivo Bingo.txt esta vacio es posible que el programa falle sin embargo el archivo Bolas.txt puede estar vacio
// Eso indicaria que no se ha sacado ninguna bola

// OBSERVACION: En un carton de bingo pueden haber numeros repetidos.

package Modelo;

import Modelo.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BingoProyecto {

    public static void main(String[] args) throws InterruptedException, IOException {
        GameSaver gs = new GameSaver();
        GameReader gr = new GameReader();
        Carton ganador = null;
        boolean salir = false;
        int opcion;
        while(!salir){
            opcion = 0;
            String respuesta = "";
            boolean salirDelJuego = false;
            
            System.out.print("Bienvenido al Bingo!\n");
            System.out.print("--------------------\n\n");
            System.out.print("Seleccione una opcion\n");
            System.out.print("1. Juego nuevo \n");
            System.out.print("2. Cargar juego \n");
            System.out.print("3. Salir \n");
            respuesta = JOptionPane.showInputDialog("Ingrese su opcion \n");
            try{
                opcion = Integer.parseInt(respuesta);
            } catch(Exception e){
                System.out.print("(!) Por favor ingrese un numero \n");
            }
            
            if(opcion < 4 && opcion > 0){
                switch(opcion){
                    case 1:
                        //TODA LA LOGICA DEL JUEGO
                        //OPCION 1: Se saca una bola de la tombola y si esta en algun carton se marca con una X
                        //OPCION 2: Imprime todos los cartones disponibles
                        //OPCION 3: Guarda el juego en el estado actual
                        //OPCION 4: Retrocede al menu principal
                        System.out.print("Iniciando el bingo \n");
                        int cantidadCartones = 0;
                        
                        boolean valido = false;
                        do{
                            System.out.print("Ingrese la cantidad de cartones que jugaran \n");
                            try{
                                cantidadCartones = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de Cartones \n"));
                                valido = true;
                            }catch(Exception e){
                                System.out.print("(!) Por favor ingrese un numero \n");
                            }
                        }while(!valido);
                        
                        Jugador pl = new Jugador();
                        Tombola tb = new Tombola();
                        
                        pl.generarCartones(cantidadCartones);
                        tb.rellenarTombola();
                        System.out.print("Cartones listos puede empezar a jugar \n");
                        
                        
                        while(!salirDelJuego){
                            int opc = 0;
                            System.out.print("1. Sacar una bola de la tombola \n");
                            System.out.print("2. Imprimir cartones \n");
                            System.out.print("3. Guardar juego actual \n");
                            System.out.print("4. Salir del juego \n");
                            boolean valid = false;
                            do{
                                try{
                                opc = Integer.parseInt(JOptionPane.showInputDialog("Opcion \n"));
                                if(opc > 0 && opc < 5){
                                    valid = true;
                                }
                                }catch(Exception e){
                                System.out.print("(!) Por favor ingrese un numero valido\n");
                                }
                            }while(!valid);
                            switch(opc){
                                case 1: 
                                    System.out.print("Cantidad de objetos en la tombola: " + tb.gettCantidad() + "\n");
                                    int x = tb.sacarBola();
                                    pl.revisar(x);
                                    System.out.print("Sale la bola con el numero: " + x + "\n");
                                    if(pl.gano()){
                                        for(int i = 0; i < pl.getCantidadCartones(); i++){
                                            if(pl.getCartones().get(i).isGanador()){
                                                ganador = pl.getCartones().get(i);
                                            }
                                        }
                                        
                                        System.out.print("(!) FELICIDADES (!) \n");
                                        System.out.print("Carton ganador: \n");
                                        System.out.print(ganador.toString());
                                        
                                        System.out.print("\n\n EL JUEGO HA FINALIZADO1 \n");
                                        salirDelJuego = true;
                                    }
                                    break;
                                case 2:
                                    for(int i = 0; i < pl.getCantidadCartones(); i++){
                                        System.out.print(pl.getCartones().get(i).toString() + "\n");
                                    }
                                    break;
                                case 3: 
                                    System.out.print("Guardando juego \n");
                                    gs.guardarJugador(pl);
                                    gs.guardarSeleccionados(tb);
                                    break;
                                case 4:
                                    System.out.print("(!) Si sale todo el progreso no guardado se perdera \n");
                                    System.out.print("1. Salir \n");
                                    System.out.print("2. No salir \n");
                                    int op = Integer.parseInt(JOptionPane.showInputDialog("Opcion \n"));
                                    if(op == 1){
                                        salirDelJuego = true;
                                    }
                            }
                            
                        }
                    break;
                    
                    case 2: 
                        System.out.print("Cargando ... \n");
                        System.out.print("Por favor espere \n");
                        
                        //RECUPERAR JUGADAOR
                        Jugador player = new Jugador();
                        ArrayList<Integer> temp = gr.recuperarCartones();
                        player.recuperarJugador(temp); 
                       
                        //RECUPERAR BOLAS
                        Tombola tomb = new Tombola();
                        int [] tempo = gr.recuperarBolas();
                        for(int i = 0; i < tempo.length; i++){
                            tomb.agregarSeleccionados(tempo[i]);
                        }
                        
                        //COMPARAR LAS BOLAS QUE SALIERON CON LAS CASILLAS Y SACAR LAS QUE YA SALIERON DE LA TOMBOLA PARA QUE NO VUELVAN A SALIR
                        tomb.rellenarTombola();
                        tomb.diferencia();
                        player.compararConLista(tomb.getSeleccionados());
                        System.out.print("Juego cargado correctamente \n\n");
                        
                        System.out.print("Cartones listos puede empezar a jugar \n");                   
                        while(!salirDelJuego){
                            int opc = 0;
                            System.out.print("1. Sacar una bola de la tombola \n");
                            System.out.print("2. Imprimir cartones \n");
                            System.out.print("3. Guardar juego actual \n");
                            System.out.print("4. Salir del juego \n");
                            boolean valid = false;
                            do{
                                try{
                                opc = Integer.parseInt(JOptionPane.showInputDialog("Opcion \n"));
                                if(opc > 0 && opc < 5){
                                    valid = true;
                                }
                                }catch(Exception e){
                                System.out.print("(!) Por favor ingrese un numero valido\n");
                                }
                            }while(!valid);
                            switch(opc){
                                case 1: 
                                    System.out.print("Cantidad de objetos en la tombola: " + tomb.gettCantidad() + "\n");
                                    int x = tomb.sacarBola();
                                    player.revisar(x);
                                    System.out.print("Sale la bola con el numero: " + x + "\n");
                                    if(player.gano()){
                                        for(int i = 0; i < player.getCantidadCartones(); i++){
                                            if(player.getCartones().get(i).isGanador()){
                                                ganador = player.getCartones().get(i);
                                            }
                                        }
                                        
                                        System.out.print("(!) FELICIDADES (!) \n");
                                        System.out.print("Carton ganador: \n");
                                        System.out.print(ganador.toString());
                                        
                                        System.out.print("\n\n EL JUEGO HA FINALIZADO1 \n");
                                        salirDelJuego = true;
                                    }
                                    break;
                                case 2:
                                    for(int i = 0; i < player.getCantidadCartones(); i++){
                                        System.out.print(player.getCartones().get(i).toString() + "\n");
                                    }
                                    break;
                                case 3: 
                                    System.out.print("Guardando juego \n");
                                    gs.guardarJugador(player);
                                    gs.guardarSeleccionados(tomb);
                                    break;
                                case 4:
                                    System.out.print("(!) Si sale todo el progreso no guardado se perdera \n");
                                    System.out.print("1. Salir \n");
                                    System.out.print("2. No salir \n");
                                    int op = Integer.parseInt(JOptionPane.showInputDialog("Opcion \n"));
                                    if(op == 1){
                                        salirDelJuego = true;
                                    }
                            }
                            
                        }
                        break;
                    case 3: 
                        
                        System.out.print("Juego terminado \n");
                        salir = true;
                    break;
                }
            }
            else{
                System.out.print("(!) Ingrese un valor valido");
            }
        }
    }    
}

