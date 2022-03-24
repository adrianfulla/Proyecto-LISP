package comun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interprete {
    private HashMap<String, Integer> myVars;

    /**
     * Constructor del interprete
     */
    public Interprete(){
        myVars = new HashMap<String, Integer>();
    }

    /**
     * Operación suma sencilla
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
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

    /**
     * Operación resta sencilla
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
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

    /**
     * Operación multiplicacion sencilla
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
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

    /**
     * Operación division sencilla
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
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

    /**
     * Operación quote
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
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

    /**
     * Operación setq
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
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

            @Override
            public void aniadirResultado(String key, String result, boolean evaluacion) {

            }

            @Override
            public boolean getEvaluacion() {
                return false;
            }

        };

        assigmentResult.aniadirResultado(varName, varValue.toString());

        return assigmentResult;

    }

    /**
     * Operación list
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
    public IResultadoOperacion list(String expresion){
        Pattern pattern = Pattern.compile("([']([a-z]+|[0-9]+)|[']([(]([a-z]|[a-z][ ]|[0-9]+|[0-9]+[ ])*[)]))", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        String respuesta = "";
        String[] util;

        while (matcher.find()){
            util = matcher.group().split("'");
            respuesta = respuesta +" "+ util[1];
        }
        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" list ", "(" + respuesta + " )");
        return resultado;
    }

    public IResultadoOperacion menor(String expresion){
        Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        int valor1 = 0;
        int valor2 = 0;
        int contador = 0;
        String respuesta = "";

        while (matcher.find()){
            contador++;
            if(contador == 1){
                valor1 = Integer.parseInt(matcher.group().trim());
            }else if (contador == 2){
                valor2 = Integer.parseInt(matcher.group().trim());
            }
        }
        boolean evaluacion;
        if(valor1 < valor2){
            respuesta = "EL valor "+ valor1 + " es menor a el valor " + valor2;
            evaluacion = true;
        }else {
            respuesta = "EL valor "+ valor2 + " es menor a el valor " + valor1;
            evaluacion = false;
            }

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" menor ", "" + respuesta, evaluacion);
        return resultado;
    }

    public IResultadoOperacion mayor(String expresion){
        Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        int valor1 = 0;
        int valor2 = 0;
        int contador = 0;
        String respuesta = "";

        while (matcher.find()){
            contador++;
            if(contador == 1){
                valor1 = Integer.parseInt(matcher.group().trim());
            }else if (contador == 2){
                valor2 = Integer.parseInt(matcher.group().trim());
            }
        }
        boolean evaluacion;
        if(valor1 > valor2){
            respuesta = "EL valor "+ valor1 + " es mayor a el valor " + valor2;
            evaluacion = true;
        }else {respuesta = "EL valor "+ valor2 + " es mayor a el valor " + valor1;
            evaluacion = false;}

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" menor ", "" + respuesta, evaluacion);
        return resultado;
    }

    /**
     * Operación atom
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
    public IResultadoOperacion atom(String expresion){
        Pattern pattern = Pattern.compile("^[(][ ]*atom[ ](['].|[0-9]+|['][(]*.*[)]*[)]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        String[] texto = null;
        int abierto = 0;
        int cerrado = 0;
        String respuesta = "";
        while (matcher.find()){
            texto = matcher.group(1).trim().split("");
        }

        for (int i=0; i<texto.length; i++){
            if (texto[i].equals("(")){
                abierto += 1;
            }
            if (texto[i].equals(")")) {
                cerrado += 1;
            }
        }
        for (int i=0; i<texto.length; i++){
            if (texto[i].equals("'") && texto[i+1].equals("(") && abierto==cerrado){
                respuesta = "NIL";
            }
            if (abierto==0 && cerrado==0){
                respuesta = "T";
            }
        }

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" atom ", "" + respuesta);
        return resultado;
    }

    public IResultadoOperacion cond(String expresion){
        Pattern pattern = Pattern.compile("[(]cond [(]([(].*[)])[)]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);

        String adentro = null;
        while (matcher.find()){
            adentro = matcher.group();
        }

        pattern = Pattern.compile("([(].*?[)])", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(adentro);

        boolean pExito = false;
        while (matcher.find()) {
            if (pExito) {
                Operate(matcher.group());
            }
            else{
                String param = matcher.group();
                if(Operate(param).getEvaluacion()){ //if param is true
                    pExito = true;
                    continue;
                }
                else
                    break;

            }
        }
        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" cond ", "exitoso");
        return resultado;
    }

    public IResultadoOperacion equals(String expresion){
        Pattern pattern = Pattern.compile("([\"]\\w+[\"]|[0-9]+|list( \\w+)+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        String valor1 = "";
        String valor2 = "";
        int contador = 0;
        String respuesta = "";



        while (matcher.find()){
            contador++;
            if(contador == 1){
                valor1 = matcher.group().trim();
            }else if (contador == 2){
                valor2 = matcher.group().trim();
            }
        }
        boolean evaluacion;
        if(valor1.equals(valor2)){
            respuesta = "EL valor "+ valor1 + " es igual al valor " + valor2;
            evaluacion = true;
        }else {
            respuesta = "EL valor "+ valor2 + " es diferente al valor " + valor1;
            evaluacion = false;
        }

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" menor ", "" + respuesta, evaluacion);
        return resultado;
    }

    /**
     * Método encargado de verificar la operacion a realizar.
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */

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
            case 7:
                return list(expresion);
            case 9:
                return menor(expresion);
            case 10:
                return mayor(expresion);
            case 8:
                return atom(expresion);
            case 11:
                return equals(expresion);
            case 12:
                return cond(expresion);
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

                    @Override
                    public void aniadirResultado(String key, String result, boolean evaluacion) {

                    }

                    @Override
                    public boolean getEvaluacion() {
                        return false;
                    }
                };
                return resultadoError;
        }
    }
}
