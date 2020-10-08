/**
 * Delgado Ramírez, José David
 * Franco García, Hugo Israel
 * Practica_2.java
 * 28/09/2020
 * Descripcion:
 * En base a un txt se compara el CODOP contra el TABOP para encontrar las relaciones
 * y los datos que se asocian a este.
 */
package Practica_2;

import java.io.IOException;
import Tools.arrayContainer;
import Tools.Identificador;

/**
 *
 * @author José David Delgado Ramírez
 * @author Hugo Israel Franco García
 */
public class Practica_2 {
    public static void main(String[] args) throws IOException{
        /**
         * Creacion de objetos
         */
        arrayContainer ar = new arrayContainer();
        Identificador id = new Identificador();
        
        id.runner("src/TXT/P2ASM.txt", "src/TXT/tabop.txt");    // Carga de metodo de precarga a la comparacion
        ar.finder();    // Llamado al metodo de comparacion
        // Ciclo for para recorrer el resultado final de la comparacion
        for (int i = 0; i < ar.getRESULT().size(); i++) 
            System.out.println(ar.getRESULT().get(i));            
        
    }   // Fin de main
}
