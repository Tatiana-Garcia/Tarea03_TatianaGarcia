
package tarea03_tatianagarcia;

import java.util.Scanner;

public class Tarea03_TatianaGarcia {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        String res = "S";
        do{
            int con=0;
            System.out.println("Ingresa tu nombre: ");
            String nombre = leer.next();

            System.out.println("\nJUEGO DE MATRICES\n");
            String matriz[][]= new String[10][10];
            matriz = GenerarMatriz(matriz);
            imprimeMatriz(matriz,0,0);

            System.out.println("¡Hola "+nombre+"! En el siguiente juego,");
            System.out.println("Debes hacer que el robot 'R' entregue \ntodas las cajas representadas con 'C' "
                    + "\nal punto de meta 'O' en un recorrido.\nPero cuidado, hay obstaculos 'X' que "
                    + "\nte causaran un GAME OVER");
            System.out.println("\nComandos: \nU:Arriba\nD:Abajo"
                    + "\nL:Izquierda\nR:Derecha\nG:Recoger cajas\nS:Poner cajas");


            System.out.println("\nIngrese los comandos separados por ',':");
            String cadena = leer.next();
            String array[]= cadena.split(",");
            System.out.println("");
            Recursiva(array,0,matriz,con);
            imprimeMatriz(matriz,0,0);

            System.out.println("Desea intentar de nuevo[s/n]: ");
            res = leer.next();
        }while(res=="S"||res=="s");
        
        
        /*Comandos a probar:
        
        camino correcto:
        U,U,U,U,U,U,U,L,L,L,D,D,D,D,G,L,G,L,G,U,U,U,U,L,L,D,D,D,D,D,D,D,D,R,R,R,R,R,S
        
        camino con cajas restantes:
        U,U,U,U,U,U,U,L,L,L,D,D,D,D,G,L,G,L,G,U,U,U,U,L,L,D,D,D,D,D,D,D,D,D,R,R,R,R,R,S
        
        camino choque de obstaculos:
        U,U,U,U,U,U,U,L,L,D,L,D,D,D,G,L,G,L,G,U,U,U,U,L,L,D,D,D,D,D,D,D,D,R,R,R,R,R,S**/
        
    }
    /**
     * 
     * @param a --> String del arreglo de los comandos del usuario
     * @param p --> posicion del arreglo, para cada comando
     * @param matriz --> Mapa del juego
     * @param con --> contador para saber la cantidad de obstaculos que cruzo el robot
     */
    
    public static void Recursiva(String [] a, int p,String[][]matriz, int con){
        //Recursiva para mostrar y generar el recorrido del robot
        if (p==a.length-1) {//Base
            int j2=0, i2=0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j <10; j++) {
                    if(matriz[i][j].contains("R")){
                        matriz[i][j]=" ";
                        switch(a[p]){
                            case "L":{
                                if(j>0){
                                    if(matriz[i][j-1]=="C"){
                                        matriz[i][j-1]="RC";
                                    }
                                    else if(matriz[i][j-1]=="X"){
                                        matriz[i][j-1]="R";
                                        con++;
                                    }
                                    else if(matriz[i][j-1]=="O"){
                                        matriz[i][j-1]="RO";
                                    }
                                    else{
                                        matriz[i][j-1]="R";
                                    }
                                }
                                matriz[i][j] = "-";
                                break;
                            }
                            case "U":{
                                if(i>0){
                                    if(matriz[i-1][j]=="C"){
                                        matriz[i-1][j]="RC";
                                    }
                                    else if(matriz[i-1][j]=="X"){
                                        matriz[i-1][j]="R";
                                        con++;
                                    }
                                    else if(matriz[i-1][j]=="O"){
                                        matriz[i-1][j]="RO";
                                    }
                                    else{
                                        matriz[i-1][j]="R";
                                    }  
                                }
                                matriz[i][j] = "|";
                                break; 
                            }
                            case "R":{
                                if(j<matriz.length-1){
                                    j2 = j+1;
                                    i2 = i;
                                }
                                matriz[i][j] = "-";
                                break;
                            }
                            case "D":{
                                if(i<matriz.length-1){
                                    j2 = j;
                                    i2 = i+1;
                                }
                                matriz[i][j] = "|";
                                break;
                            }
                            case "G":{
                                if(i==4&&(j>1&&j<5)){
                                    matriz[i][j]= "R";
                                }
                                break;
                            }
                            case "S":{
                                if(i==8&&j==5){
                                    matriz[i][j]= "R";
                                }
                                else{
                                    matriz[i][j]="C";
                                }
                                break;
                            }
                            default:{
                                break;
                            }
                        }//fin del switch
                    }//condicion R
                }
            }//FIN DEL FOR
            switch(a[p]){
                case "R":
                case "D":
                    if(matriz[i2][j2]=="C"){
                        matriz[i2][j2]="RC";
                    }
                    else if(matriz[i2][j2]=="O"){
                        matriz[i2][j2]="RO";
                    }
                    else if(matriz[i2][j2]=="X"){
                        matriz[i2][j2]="R";
                        con++;
                    }
                    else{
                        matriz[i2][j2]="R";
                    }
                    break;
                
            }
            
            if(con==0){
                Validacion(matriz);
            }else{
                System.out.println("\nMision faliida\nHas chocado con uno o mas obstaculos\n");
            }    
        }//Fin de caso base
        else {
            int j2=0, i2=0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j <10; j++) {
                    if(matriz[i][j].contains("R")){
                        matriz[i][j]=" ";
                        switch(a[p]){
                            case "L":{
                                if(j>0){
                                    if(matriz[i][j-1]=="C"){
                                        matriz[i][j-1]="RC";
                                    }
                                    else if(matriz[i][j-1]=="X"){
                                        matriz[i][j-1]="R";
                                        con++;
                                    }
                                    else if(matriz[i][j-1]=="O"){
                                        matriz[i][j-1]="RO";
                                    }
                                    else{
                                        matriz[i][j-1]="R";
                                    }
                                }
                                matriz[i][j] = "-";
                                break;
                            }
                            case "U":{
                                if(i>0){
                                    if(matriz[i-1][j]=="C"){
                                        matriz[i-1][j]="RC";
                                    }
                                    else if(matriz[i-1][j]=="X"){
                                        matriz[i-1][j]="R";
                                        con++;
                                    }
                                    else if(matriz[i-1][j]=="O"){
                                        matriz[i-1][j]="RO";
                                    }
                                    else{
                                        matriz[i-1][j]="R";
                                    }  
                                }
                                matriz[i][j] = "|";
                                break; 
                            }
                            case "R":{
                                if(j<matriz.length-1){
                                    j2 = j+1;
                                    i2 = i;
                                }
                                matriz[i][j] = "-";
                                break;
                            }
                            case "D":{
                                if(i<matriz.length-1){
                                    j2 = j;
                                    i2 = i+1;
                                }
                                matriz[i][j] = "|";
                                break;
                            }
                            case "G":{
                                if(i==4&&(j>1&&j<5)){
                                    matriz[i][j]= "R";
                                }
                                break;
                            }
                            case "S":{
                                if(i==8&&j==5){
                                    matriz[i][j]= "R";
                                }
                                else{
                                    matriz[i][j]="C";
                                }
                                break;
                            }
                            default:{
                                break;
                            }
                        }//fin del switch
                    }//condicion R
                }
            }//FIN DEL FOR
            switch(a[p]){
                case "R":
                case "D":
                    if(matriz[i2][j2]=="C"){
                        matriz[i2][j2]="RC";
                    }
                    else if(matriz[i2][j2]=="O"){
                        matriz[i2][j2]="RO";
                    }
                    else if(matriz[i2][j2]=="X"){
                        matriz[i2][j2]="R";
                        con++;
                    }
                    else{
                        matriz[i2][j2]="R";
                    }
                    break;

            }
            Recursiva(a, p+1,matriz,con);
        }//Parte recursiva
    }//Fin del metodo recursivo

    /**
     * 
     * @param matriz 
     */
    public static void Validacion(String matriz[][]) {
        //Validacion para la deteccion de cajas en la matriz
        int con=0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if(matriz[i][j].contains("C")){
                    con++;
                }
            }
        }
        if (con>0){
            System.out.println("\nMision fallida\nNo se entregaron todas las cajas\n");
        }
        else {
            System.out.println("\nEnhorabuena, has entregado todas las cajas\n");
        }
     
    }
    /**
     * 
     * @param Matriz
     * @return 
     */
    public static String[][] GenerarMatriz(String[][] Matriz){
        //Genera el mapa de la matriz 
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[0].length; j++) {
                //Obstaculos 
                if(i>0&&i<6){
                    if(j==1||j==5||i==5&&(j>0&&j<6)){
                        Matriz[i][j]= "X";
                    }
                    else if(i==4&&(j>1&&j<5)){//Cajas
                        Matriz[i][j]= "C";
                    }
                    else{
                        Matriz[i][j]= " ";
                    }
                }
                else{
                    Matriz[i][j]= " ";
                }
                if(j==7&&i==7){
                    Matriz[i][j]= "R";
                }
                else if(i==8&&j==5){
                    Matriz[i][j]= "O";
                }
            }
        }
        return Matriz;
    }
    /**
     * 
     * @param matriz
     * @param filas
     * @param cols 
     */
    public static void imprimeMatriz(String matriz[][], int filas, int cols) {
        //Imprime la matriz 
        if (filas == matriz.length - 1 && cols == matriz[0].length - 1) {
            System.out.println( "["+matriz[filas][cols] +"]\n");            
        } else {
            if (cols == matriz[0].length-1) {   
                System.out.println( "["+matriz[filas][cols]+"]"); 
                imprimeMatriz(matriz, filas+1, 0);
            } else {
                System.out.print("["+matriz[filas][cols] +"]"); 
                imprimeMatriz(matriz, filas, cols+1);
            }
        }       
    }

}//Fin de la clase
