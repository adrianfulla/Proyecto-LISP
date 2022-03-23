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

    public IResultadoOperacion quote(String expresion){
        Pattern pattern = Pattern.compile("[(][ ]*('|quote)([(].*[)])[ ]*[)]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        String texto = "";
        while (matcher.find()) {
            texto = matcher.group(2);
        }
        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" quote ", "" + texto);
        return resultado;
    }

    public IResultadoOperacion setq(String expresion) {
        Pattern pattern = Pattern.compile("[ ]*+[a-z]+", Pattern.CASE_INSENSITIVE); //
        Matcher matcher = pattern.matcher(expresion);
        String varName = "";
        Integer varValue = 0;
        ArrayList<String> varNames = new ArrayList();
        while (matcher.find()) {
            varName = matcher.group().trim();
            if (!varName.equals("setq")){
                varNames.add(varName);
            }
        }

        pattern = Pattern.compile("[ ]+[0-9]+[ ]*", Pattern.CASE_INSENSITIVE); //
        matcher = pattern.matcher(expresion);
        ArrayList<Integer> varValues = new ArrayList();
        while (matcher.find()) {
            varValue = Integer.parseInt(matcher.group().trim());
            varValues.add(varValue);
        }

        if (varNames.size()==varValues.size()){
            for (int i=0;i<varNames.size(); i++){
                myVars.put(varNames.get(i),varValues.get(i));
            }
        }

        //Using anonymous Inner class
        IResultadoOperacion assigmentResult = new IResultadoOperacion() {

            String key = "";
            String value = "";

            @Override
            public String perzonalizarOperacion() {
                return "Variable: " + key + " asignada con valor: " + value;

            }

            @Override
            public void aniadirResultado(String key, String result) {
                this.key = key;
                this.value = result;
            }

        };

        assigmentResult.aniadirResultado(varName, varValue.toString());

        return assigmentResult;

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
            case 5:
                return quote(expresion);
            case 6:
                return setq(expresion);
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
