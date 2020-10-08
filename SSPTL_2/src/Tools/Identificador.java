/**
 * Delgado Ramírez, José David
 * Franco García, Hugo Israel
 * Identificador.java
 * 28/09/2020
 * Descripcion:
 * En base a un txt se compara el CODOP contra el TABOP para encontrar las relaciones
 * y los datos que se asocian a este.
 */
package Tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author José David Delgado Ramírez
 * @author Hugo Israel Franco García
 */
public class Identificador {
    // Variables globales
    
    // Creacion de arraylist para cargar las isntrucciones
    private static ArrayList<String> INST = new ArrayList<String>();    
    private static ArrayList<String> TABOP = new ArrayList<String>();
    // Boleano para la salida del programa
    private static boolean exit = false;
    // Variables para la etiqueta, CODOP y operando
    private static String etq = "ETIQUETA= ";
    private static String CODOP = "CODOP= ";
    private static String op = "OPERANDO= ";
    
    /**
     * @param txt Direccion de donde se tomara el txt para la lectura
     */
    private static void reader(String txt) throws FileNotFoundException, IOException{
        String line;    // Variable para leer temporalmente las lineas del txt
        int contAux = 0;    // Contador para auxiliar en la suma de operandos
        // Lectores para cargar el .txt
        FileReader fr = new FileReader(txt);
        BufferedReader br = new BufferedReader(fr);
        // Ciclo while que pasa linea por linea el documento
        while((line = br.readLine())!= null && exit == false){
                String[] instructionSet = line.split("\t"); // Creacion de arreglo con split para cargar el numero de instrucciones
                for (int i = 0; i < instructionSet.length; i++) {
                    contAux++;  // Incremento dfel primer ciclo
                    if(instructionSet[i].startsWith(";") && instructionSet[i].length() < 80) {  // Condicional para los comentarios
                        INST.add(instructionSet[i]);
                    }
                    else if(instructionSet[i].matches("^\\p{Alpha}*\\w*") && (i == 0 && instructionSet[i].length() <= 8)) {  // Condicional para etiquetas
                        if(instructionSet[i].isEmpty()){    // Si se encuentra la primera posicion vacia
                            INST.add(etq + "NULL");   // Adicion de null
                        } else {
                            INST.add(etq + instructionSet[i]);    // Adicion de instruccion
                        }
                    }   // Fin de condicional para etiquetas
                    else if(instructionSet[i].matches("(\\p{Alpha}*)|(\\p{Alpha}*.{0,1}\\p{Alpha}*)|(\\p{Alpha}*)|(\\p{Alpha}*.{0,1}\\p{Alpha}*)")  // Condicional para CODOP
                            && ((i == 1) && instructionSet[i].length() <= 5) ){
                        if((instructionSet[i].equals("end") || instructionSet[i].equals("End") || instructionSet[i].equals("END"))) {   // Condicional para si encuentra un END
                            exit=true;
                        }else   // Continua con los CODOP
                            if(instructionSet.length == 2) {    // Si se ubica en la parte 2
                                if(instructionSet[i].isEmpty()) {   // Si no contiene CODOP
                                    INST.add(CODOP + "Error de CODOP");
                                } else {    // Caso contrario
                                    INST.add(CODOP + instructionSet[i]);
                                    INST.add(op + "NULL");
                                    INST.add("\n");
                                }
                            } else{ // Si no
                                if(instructionSet[i].isEmpty()) {   // Si esta vacio
                                    INST.add(CODOP + "Error de CODOP");
                                }else
                                    INST.add(CODOP + instructionSet[i]);  // Adicion de CODOP
                            }   // Fin de else
                    }   // Fin de condicional de CODOP
                    else if(i==2) {  // COndicional para los operandos
                        if(instructionSet[i].isEmpty() && contAux == 1){    // Si solo tiene un OPERADOR
                            INST.add(op + "NULL");
                            contAux = 0;    // Reinicio de contador auxiliar
                        } else {
                            INST.add(op + instructionSet[i]); // Añade el operando
                        }
                        INST.add("\n");   // Salto de linea en el bloque de instruccion
                    } else{ // En caso de no topar con alguna de las instrucciones en la linea
                        switch(i){  // Switch case para cada posicion de la tabla
                            case 0: // Si esta al inicio o en comentario
                                if(instructionSet[i].startsWith(";"))   // Identificar el comentario
                                    INST.add("Error de comentario");
                                 else   // Para el codigo de etiqueta
                                    INST.add(etq + "Error de etiqueta");
                                break;
                            case 1: // Caso de CODOP
                                INST.add(CODOP + "Error de CODOP");
                                break;
                            case 2: // Caso de Operando
                                INST.add(op + "Error de Operando");
                                break;
                            default:    // Caso para cuando no hay nada en relacion
                                System.out.println("Missing target");
                                break;
                        }   // Fin de switch
                    }   // Fin de else
            }   // Fin de for
        }   // Fin de while
        
        fr.close(); // Cierre de archivo
    }   // Fin de metodo
    /**
     * Funcion para leer el TABOP y guardarlo en una arrayList
     * @param txt -Fuente de donde se carga el TABOP
     */
    private static void readerTabop(String txt) throws FileNotFoundException, IOException{
        String line;    // Variable para leer temporalmente las lineas del txt
        // Lectores para cargar el .txt
        FileReader fr = new FileReader(txt);
        BufferedReader br = new BufferedReader(fr);
        // Ciclo while que pasa linea por linea el documento
        while((line = br.readLine())!= null){
            String[] tabop = line.split("\t");
            for (int i = 0; i < tabop.length; i++) {
                switch(i){
                    case 0:
                        TABOP.add(tabop[i]);
                        break;
                    case 1:
                        TABOP.add(tabop[i]);
                        break;
                    case 2:
                        TABOP.add(tabop[i]);
                        break;
                    case 3:
                        TABOP.add(tabop[i]);
                        break;
                    case 4:
                        TABOP.add(tabop[i]);
                        break;
                    case 5:
                        TABOP.add(tabop[i]);
                        break;
                    default:
                        System.out.println("Missing Target!!!");
                        break;
                }
            }
        }
        fr.close(); // Cierre de archivo
    }   // Fin de funcion
    /**
     * Funcion boleana para detectar un numero dentro de un string
     * @param line -String para la lectura de linea de texto
     * @return True en caso de contener un numero o False en caso 
     * de que no lo tenga
     */
    private static boolean numberCheck(String line) {
        boolean flag = false;   // Bandera 
        for(char c : line.toCharArray()){
            if(Character.isDigit(c)){   // En caso de que lo contenga
                 flag = true;   // Cambia la bandera
                 break; // Rompe el ciclo
            } else  // Si no
                 flag = false;  // Solo retorna
        }   // Fin de for
        return flag;    // Devolucion de bandera
    }   // Fin de funcion
    /**
     * Funcion boleana para descubrir si una etiqueta empieza con un numero
     * @param line -Linea de entrada de texto
     * @return True en case de tener un numero al inicio de la etiqueta
     * False en caso de no tener un numero
     */
    private static boolean numberStartCheck(String line) {
        boolean flag = false;   // Bandera
        String[] separator = line.split(" ");   // Split para separar la etiqueta de la impresion
        if(Character.isDigit(separator[1].charAt(0)))   // Revisa si es un digito al inicio de la cadena
            flag = true;
        else 
            flag = false;
        
        return flag;    // Retorno de bandera
    }   // Fin de metodo
    
    /**
     * Metodo para detectar si se encuentra un numero en la arraylist
     * lo cambie como un error del codop
     */
    private static void checker() {
        for (int i = 0; i < INST.size()-1; i++) {
            if(INST.get(i).contains("CODOP= ")) // Si contiene la palabra CODOP
                if(numberCheck(INST.get(i)))    // Si el metodo tiene un numero
                    INST.set(i, "CODOP= Error de CODOP"); // Cambia el valor por un error del CODOP
        }
        for (int i = 0; i < INST.size()-1; i++) {
            if(INST.get(i).contains("ETIQUETA= ")) // Si contiene la palabra CODOP
                if(numberStartCheck(INST.get(i)))    // Si el metodo tiene un numero
                    INST.set(i, "ETIQUETA= Error de Etiqueta"); // Cambia el valor por un error del CODOP
        }
    }   // Fin de metodo
    
    public void runner(String dir1, String dir2) throws IOException {
        reader("src/TXT/P2ASM.txt");
        readerTabop("src/TXT/tabop.txt");
        checker();
        arrayContainer.setINST(INST);
        arrayContainer.setTABOP(TABOP);
    }
}
