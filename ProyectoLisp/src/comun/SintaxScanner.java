package comun;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SintaxScanner {

    public static boolean evaluate(String regex, String expresion) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        return matcher.find();
    }

    public void separarExpresion(String programa){
        String[] expresion = programa.split(" ");
        for (int i=0; i<expresion.length; i++){
            System.out.println(expresion[i]);
        }
    }

    public static int getState(String expresion) {
        if (evaluate("^[(][ ]*[+][ ]+([a-z]+|[0-9]+)([ ]+([a-z]+|[0-9]+)[ ]*)*[)]$", expresion)) {
            return 1;
        } else if (evaluate("^[(][ ]*[-][ ]+([a-z]+|[0-9]+)([ ]+([a-z]+|[0-9]+)[ ]*)*[)]$", expresion)) {
            return 2;
        } else if (evaluate("^[(][ ]*[*][ ]+([a-z]+|[0-9]+)([ ]+([a-z]+|[0-9]+)[ ]*)*[)]$", expresion)) {
            return 3;
        } else if (evaluate("^[(][ ]*[/][ ]+([a-z]+|[0-9]+)([ ]+([a-z]+|[0-9]+)[ ]*)*[)]$", expresion)) {
            return 4;
        } else if (evaluate("^[(][ ]*('|quote)([(].*[)])[ ]*[)]$", expresion)) {
            return 5;
        } else if (evaluate("^[(][ ]*setq([ ]+[a-z]+[ ]+[0-9])*+[ ]*[)]$", expresion)) {
            return 6;
        } else {
            return 0;
        }
    }
}
