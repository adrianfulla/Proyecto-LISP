package comun;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lee las expresiones en regex
 *
 * @author Diego Alexander Hernández Silvestre
 * @author Renatto Esteban Guzman Sosa
 * @author Adrian Fulladolsa Palma
 * @author David Jonathan Aragon Vasquez
 */
public class SintaxScanner {

    /**
     * Evalua las expresiones regex
     * @param regex esperado de la expresion
     * @param expresion dada por el usuario
     * @return  si se encontró el patrón en la expresión
     */
    public static boolean evaluate(String regex, String expresion) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        return matcher.find();
    }

    /**
     * Separa las partes de la expresión
     * @param programa
     */
    public void separarExpresion(String programa){
        String[] expresion = programa.split(" ");
        for (int i=0; i<expresion.length; i++){
            System.out.println(expresion[i]);
        }
    }

    /**
     * Encuentra la cantidad de paréntesis que se encuentra en una expresion compleja.
     * @param expresion Programa a evaluar
     * @return contador Cantidad de paréntesis que se encuentran en la expresion
     */
    public int veces(String expresion){
        String[] texto = expresion.split("");
        int contador = 0;
        for (int i = 0; i<texto.length; i++){
            if (texto[i].equals("(")){
                contador++;
            }
        }
        return contador;
    }

    public static String regexCom(String n, int veces){
        while (!(veces == 0)){
            veces-= 1;
            n = "("+ n + "|[(].*?[)])";
        }
        return n;
    }




    /**
     * Obtiene el estado segun el regex que utilice la expresion
     * @param expresion Programa a evaluar
     * @return estado
     */
    public static int getState(String expresion) {
        SintaxScanner s = new SintaxScanner();
        int v = s.veces(expresion);
        String a = "[(](([\\/]|[\\*]|[\\+]|[\\-])[ ])*([0-9]+[ ]|[0-9]+[ ]*[)])*[)]";
        if(evaluate("[(]defun (.*?)[ ]([(].*[)])[)]", expresion)){
            return 14;
        } else if(evaluate("[(]cond [(]([(].*?[)]+)[)][)]+", expresion)){
            return 12;
        } else if (evaluate("^[(][ ]*[+][ ]+([a-z]+|-?[0-9]+)([ ]+([a-z]+|-?[0-9]+)[ ]*)*[)]$", expresion)) {
            return 1;
        } else if (evaluate("^[(][ ]*[-][ ]+([a-z]+|-?[0-9]+)([ ]+([a-z]+|-?[0-9]+)[ ]*)*[)]$", expresion)) {
            return 2;
        } else if (evaluate("^[(][ ]*[*][ ]+([a-z]+|-?[0-9]+)([ ]+([a-z]+|-?[0-9]+)[ ]*)*[)]$", expresion)) {
            return 3;
        } else if (evaluate("^[(][ ]*[/][ ]+([a-z]+|-?[0-9]+)([ ]+([a-z]+|-?[0-9]+)[ ]*)*[)]$", expresion)) {
            return 4;
        } else if (evaluate("^[(][ ]*('|quote)([(].*[)])[ ]*[)]$", expresion)) {
            return 5;
        } else if (evaluate("^[(][ ]*setq([ ]+[a-z]+[ ]+-?[0-9]+)*+[ ]*[)]$", expresion)) {
            return 6;
        } else if (evaluate("[(][ ]*(list|setq)[ ]*([a-z]|-?[0-9])?[ ]*((['][(]([a-z]+.?|-?[0-9]+.?)+([)].))+|(['][a-z].+|[']-?[0-9].+)[)])", expresion)) {
            return 7;
        } else if (evaluate("^[(][ ]*[<][ ]+([a-z]+|-?[0-9]+)([ ]+([a-z]+|-?[0-9]+)[ ]*)*[)]$", expresion)) {
            return 9;
        } else if (evaluate("^[(][ ]*[>][ ]+([a-z]+|-?[0-9]+)([ ]+([a-z]+|-?[0-9]+)[ ]*)*[)]$", expresion)) {
            return 10;
        } else if (evaluate("^[(][ ]*atom[ ](['].|-?[0-9]+|['][(]*.*[)]*[)]*)[ ]*[)]$", expresion)) {
            return 8;
        } else if (evaluate("^[(][ ]*(?:equal|[=])[ ](.|-?[0-9]+|[(]*.*[)]*[)]*)[ ]*[)]$", expresion)){
            return 11;
        } else if (evaluate("[(](?:[\\/]|[\\*]|[\\+]|[\\-]).*([(](?:[\\/]|[\\*]|[\\+]|[\\-]).*?[)]).*[)]", expresion)){
            return 13;
        } else if(evaluate("(?:[(](.*?)[ ]([(]*.*[)]*)[)])", expresion)){
            return 15;
        }else if(evaluate("([(][ ]*-?[0-9]+[ ]*[)]|[ ]*[0-9]+[ ]*)", expresion)){
            return 16;
        }else {
            return 0;
        }
    }
}
