package comun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interprete {
    private HashMap<String, Integer> myVars;

    public Interprete(){
        myVars = new HashMap<String, Integer>();
    }

    public IResultadoOperacion suma(String expresion){
        Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        int total = 0;

        while (matcher.find()){
            total += Integer.parseInt(matcher.group().trim());
        }

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" suma ", "" + total);
        return resultado;
    }

    public IResultadoOperacion resta(String expresion){
        Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        int total = 0;
        boolean ingreso = true;
        while (matcher.find()){
            if (ingreso){
                total = Integer.parseInt(matcher.group().trim());
                ingreso = false;
            } else {
                total = total - Integer.parseInt(matcher.group().trim());
            }
        }

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" resta ", "" + total);
        return resultado;
    }


    public IResultadoOperacion multiplicacion(String expresion){
        Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        int total = 0;
        boolean ingreso = true;
        while (matcher.find()){
            if (ingreso){
                total = Integer.parseInt(matcher.group().trim());
                ingreso = false;
            } else {
                total = Integer.parseInt(matcher.group().trim())*total;
            }
        }

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" multiplicacion ", "" + total);
        return resultado;
    }

    public IResultadoOperacion division(String expresion){
        Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        float total = 0;
        boolean ingreso = true;
        while (matcher.find()){
            if (ingreso){
                total = Integer.parseInt(matcher.group().trim());
                ingreso = false;
            } else {
                total = total/Integer.parseInt(matcher.group().trim());
            }
        }

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" division ", "" + total);
        return resultado;
    }

    public IResultadoOperacion Operate(String expresion){
        int operacion = SintaxScanner.getState(expresion);

        switch (operacion){
            case 1:
                return suma(expresion);
            case 2:
                return resta(expresion);
            case 3:
                return multiplicacion(expresion);
            case 4:
                return division(expresion);
            default:

                IResultadoOperacion resultadoError = new IResultadoOperacion() {
                    @Override
                    public String perzonalizarOperacion() {
                        String valor = "[ERROR]: Expresion invalida";
                        return valor;
                    }

                    @Override
                    public void aniadirResultado(String key, String result) {

                    }
                };
                return resultadoError;
        }
    }
}
