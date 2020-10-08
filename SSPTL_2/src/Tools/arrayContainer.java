/**
 * Delgado Ramírez, José David
 * Franco García, Hugo Israel
 * arrayContainer.java
 * 28/09/2020
 * Descripcion:
 * En base a un txt se compara el CODOP contra el TABOP para encontrar las relaciones
 * y los datos que se asocian a este.
 */
package Tools;

import java.util.ArrayList;

/**
 *
 * @author José David Delgado Ramírez
 * @author Hugo Israel Franco García
 */
public class arrayContainer {
    // Creacion de arraylist para cargar las isntrucciones
    private static ArrayList<String> INST = new ArrayList<String>();
    private static ArrayList<String> TABOP = new ArrayList<String>();
    private static ArrayList<String> RESULT = new ArrayList<String>();
    /**
     * Metodo para retornar la arrayList con el resultado final
     */
    public ArrayList<String> getRESULT() {
        return RESULT;
    }   // Fin de metodo
    
    /**
     * Metodo para retornar la arrayList con el conjunto de instrucciones
     */
    public ArrayList<String> getINST() {
        return INST;
    }   // Fin de metodo
    
    /**
     * Funcion para dar los valores a la arraylist
     * @param INST -Arraylist con el conjunto de instrucciones
     */
    public static void setINST(ArrayList<String> INST) {
        arrayContainer.INST = INST;
    }   // Fin de metodo
    
    /**
     * Metodo para retornar la arrayList con el TABOP
     */
    public ArrayList<String> getTABOP() {
        return TABOP;
    }   // Fin de metodo
    /**
     * Funcion para dar el valor a la arraylist del Tabop
     * @param TABOP -Carga de insrtrucciones en el TABOP
     */
    public static void setTABOP(ArrayList<String> TABOP) {
        arrayContainer.TABOP = TABOP;
    }   // Fin de funcion
    /**
     * Funcion para buscar los valores que se encuentran relacionados directamente
     * con el TABOP y añade solo los encontrados
     */
    public void finder(){
        // Ciclo for para recorrer el set de instrucciones principales
        for (int i = 0; i < INST.size(); i++) {
            boolean paso = false;   // Variable para reconocer el final
            if(INST.get(i).startsWith("CODOP=")) {  // Si contiene el identificador
                String[] tCODOP = INST.get(i).split("= ");  // Realiza un split a partir de ese punto
                // for para hacer el recorrido del TABOP
                for (int j = 0; j < TABOP.size(); j++) {
                    if(tCODOP[1].toUpperCase().equals(TABOP.get(j))){  // Si la posicion del codop concuerda con el TABOP
                        paso=true;
                        if(!correct(j, i+1)){   // Condicional para detectar la funcion
                            break;  // Rompe el ciclo
                        }else { // Si no
                            for (int k = j; k < (j+6); k++)     // for para añadir el bloque de instrucciones que tiene
                               RESULT.add(TABOP.get(k));
                       }    // Fin de else
                   }    // Fin de if Upper
                   if(tCODOP[1].toLowerCase().equals(TABOP.get(j))){  // Si la posicion del codop concuerda con el TABOP
                       paso=true;
                        if(!correct(j, i+1)){   // Condicional para detectar la funcion
                            break;  // Rompe el ciclo
                        }else { // Si no
                            for (int k = j; k < (j+6); k++) // for para añadir el bloque de instrucciones que tiene
                               RESULT.add(TABOP.get(k));
                       }    // Fin de else
                    }   // Fin de if
                   else if(j==TABOP.size()-1 && paso != true){ // Si no da con ninguno
                       RESULT.add("Error de CODOP!!!");
                       System.out.println(tCODOP[1]);
                       break;
                   }    // Fin de else
                }   // Fin de for de TABOP
            }
        }   // Fin de for de instruccion principal
    }   // Fin de metodo
    /**
     * Funcion booleana que indica si se encuentra una irregularidad en el operando, 
     * devuelve false si se encuentra con alguna
     * @param i -Pivote para la lista de partida del TABOP
     * @param index -Indice de donde comienza a leer
     */
    public static boolean correct(int index, int i){
        boolean check = true;
        String[] tOperando = INST.get(i).split("= ");   // Separa apartir de
                // Ciclo for para recorrer solo con los que tienen operando de cada CODOP
                for (int j = 1; j < TABOP.size(); j+=6) {
                    
                    if(!tOperando[1].contains("NULL") && TABOP.get(index+1).startsWith("No Operando")) {   // Entro en el que cuando no lleva, tiene operando
                        System.out.println("Entro en el que cuando no lleva, tiene operando " + index);
                        check= false;
                        break;  // Sale del ciclo for del tabop
                    } else if(tOperando[1].contains("NULL") && TABOP.get(index+1).startsWith("Operando")) {   // Si no Entro en el que cuando no lleva, tiene operando
                        System.out.println("Entro en el que cuando lleva, no tiene operando " + index);
                        check=false;
                        //System.out.println(TABOP.get(j-1));
                        break;  // Cierre del ciclo for de tabop
                    }   // Fin de else
                }   // Fin de for Tabop
                return check;
    }
}   // Fin de clase
