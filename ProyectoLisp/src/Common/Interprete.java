package Common;

public class Interprete {
    public void traduccion(String programa){
        programa.replace(" ","");
        String[] tokens;
        tokens = programa.split("");

        for (String valor: tokens){
            System.out.println(valor);
        }
    }
}
