package comun;

import java.util.Scanner;

public class DriverProgram {
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        Interprete i = new Interprete();
        SintaxScanner ss = new SintaxScanner();
        String programa;
        boolean salir=false;
        System.out.println("""
                ██████╗░██████╗░░█████╗░██╗░░░██╗███████╗░█████╗░████████╗░█████╗░░░░░░░██╗░░░░░██╗░██████╗██████╗░
                ██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗╚══██╔══╝██╔══██╗░░░░░░██║░░░░░██║██╔════╝██╔══██╗
                ██████╔╝██████╔╝██║░░██║░╚████╔╝░█████╗░░██║░░╚═╝░░░██║░░░██║░░██║█████╗██║░░░░░██║╚█████╗░██████╔╝
                ██╔═══╝░██╔══██╗██║░░██║░░╚██╔╝░░██╔══╝░░██║░░██╗░░░██║░░░██║░░██║╚════╝██║░░░░░██║░╚═══██╗██╔═══╝░
                ██║░░░░░██║░░██║╚█████╔╝░░░██║░░░███████╗╚█████╔╝░░░██║░░░╚█████╔╝░░░░░░███████╗██║██████╔╝██║░░░░░
                ╚═╝░░░░░╚═╝░░╚═╝░╚════╝░░░░╚═╝░░░╚══════╝░╚════╝░░░░╚═╝░░░░╚════╝░░░░░░░╚══════╝╚═╝╚═════╝░╚═╝░░░░░""");
        System.out.println("");
        System.out.println("Bienvenido al mejor interprete de Lisp en Java :D");
        System.out.println("""
                Para poder utilizar este interprete, solo deberas escribir tu código como si estuvieras codificando 
                en Lisp. Solo asegurate de que la sintaxis y la estructura de tu codigo se encuentran bien, así que
                sin más que agregar, puedes comenzar a programar: """);
        while (!salir){
            System.out.println("");
            System.out.println("Ingresa una nueva expresion: [NOTA - Si deseas salir, ingresa el numero 1]");
            System.out.println("[EXPRESION]");
            programa = s.nextLine();
            if (programa.equals("1")){
                salir = true;
            } else{
                System.out.println("");
                System.out.println("[RESULTADO]");
                System.out.println(i.Operate(programa).perzonalizarOperacion());
            }
        }
    }
}

