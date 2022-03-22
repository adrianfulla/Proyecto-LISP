import java.util.Scanner;

public class DriverProgram {
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        Interprete i = new Interprete();
        String programa;
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
        programa = s.nextLine();
        i.traduccion(programa);
    }
}
