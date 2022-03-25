package comun;

import com.sun.nio.sctp.AbstractNotificationHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Clase encargada de operar las expresiones e interpretar las mismas
 *
 * @author Diego Alexander Hernández Silvestre
 * @author Renatto Esteban Guzman Sosa
 * @author Adrian Fulladolsa Palma
 * @author David Jonathan Aragon Vasquez
 */
public class Interprete {
    private HashMap<String, Integer> myVars;
    private HashMap<String, String> funciones;

    Stack<String> pila = new Stack<>();

    SintaxScanner s = new SintaxScanner();
    /**
     * Constructor del interprete
     */
    public Interprete(){
        myVars = new HashMap<String, Integer>();
        funciones = new HashMap<String,String>();
    }

    /**
     * Operación suma sencilla
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
    public IResultadoOperacion suma(String expresion){
        Pattern pattern = Pattern.compile("([a-z]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        Pattern patternNum = Pattern.compile("(-?[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcherNum = patternNum.matcher(expresion);
        int total = 0;
        String variable = "";

        while (matcher.find()){
            variable = matcher.group().trim();
            total += myVars.get(variable);
        }

        while (matcherNum.find()){
            total += Integer.parseInt(matcherNum.group().trim());
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
        Pattern pattern = Pattern.compile("([a-z]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        Pattern patternNum = Pattern.compile("(-?[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcherNum = patternNum.matcher(expresion);
        String variable = "";
        int total = 0;
        boolean ingreso = true;
        while (matcher.find()){
            variable = matcher.group().trim();
            if (ingreso){
                total = myVars.get(variable);
                ingreso = false;
            } else {
                total = total - myVars.get(variable);
            }
        }

        while (matcherNum.find()){
            if (ingreso){
                total = Integer.parseInt(matcherNum.group().trim());
                ingreso = false;
            } else {
                total = total - Integer.parseInt(matcherNum.group().trim());
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
        Pattern pattern = Pattern.compile("([a-z]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        Pattern patternNum = Pattern.compile("(-?[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcherNum = patternNum.matcher(expresion);
        int total = 0;
        boolean ingreso = true;
        String variable = "";

        while (matcher.find()){
            variable = matcher.group().trim();
            if (ingreso){
                total = myVars.get(variable);
                ingreso = false;
            } else {
                total = myVars.get(variable)*total;
            }
        }

        while (matcherNum.find()){
            if (ingreso){
                total = Integer.parseInt(matcherNum.group().trim());
                ingreso = false;
            } else {
                total = Integer.parseInt(matcherNum.group().trim())*total;
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
        Pattern pattern = Pattern.compile("([a-z]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        Pattern patternNum = Pattern.compile("(-?[0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcherNum = patternNum.matcher(expresion);
        int total = 0;
        String variable = "";
        boolean ingreso = true;
        while (matcher.find()){
            variable = matcher.group().trim();
            if (ingreso){
                total = myVars.get(variable);
                ingreso = false;
            }
            else {
                total = total / myVars.get(variable);
            }
        }
        while (matcherNum.find()){
            if (ingreso){
                total = Integer.parseInt(matcherNum.group().trim());
                ingreso = false;
            }
            else {
                total = total/Integer.parseInt(matcherNum.group().trim());
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
        pattern = Pattern.compile("[ ]+-?[0-9]+[ ]*", Pattern.CASE_INSENSITIVE); //
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
        System.out.println(myVars.toString());

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
            public String getResult() {
                return null;
            }

            @Override
            public void aniadirResultado(String key, String result, boolean evaluacion) {

            }

            @Override
            public boolean getEvaluacion() {
                return false;
            }
            }

            ;

        assigmentResult.aniadirResultado(varName, varValue.toString());

        return assigmentResult;

    }

    /**
     * Operación list
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
    public IResultadoOperacion list(String expresion){
        Pattern pattern = Pattern.compile("([']([a-z]+|-?[0-9]+)|[']([(]([a-z]|[a-z][ ]|-?[0-9]+|-?[0-9]+[ ])*[)]))", Pattern.CASE_INSENSITIVE);
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

    /**
     * Obtiene la menor expresion
     * @param expresion Programa a evaluar.
     * @return resultado operacion
     */
    public IResultadoOperacion menor(String expresion){
        Pattern pattern = Pattern.compile("([a-z]+|-?[0-9]+)", Pattern.CASE_INSENSITIVE);
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

    /**
     * Obtiene la mayor expresion
     * @param expresion Programa a evaluar.
     * @return resultado operacion
     */
    public IResultadoOperacion mayor(String expresion){
        Pattern pattern = Pattern.compile("([a-z]+|-?[0-9]+)", Pattern.CASE_INSENSITIVE);
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
     * Realiza operaciones combinadas
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
    public IResultadoOperacion combinada(String expresion){
        int v = s.veces(expresion);
        Pattern pattern = Pattern.compile("[(](?:[\\/]|[\\*]|[\\+]|[\\-]|.*?).*([(](?:[\\/]|[\\*]|[\\+]|[\\-]|.*?).*?[)]).*[)]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        String match = "";
        String adentro = "";
        while (matcher.find()){
            match = matcher.group(0);
            adentro = matcher.group(1);
        }

        String res = "";
        int i = 0;

        res = Operate(adentro).getResult();
        while(i < v-1){// 0 1 2
            if(i < v-2){// 0 1
                String temp = match.replace(adentro,res);
                System.out.println(match+"/ " +temp +"/ " +adentro +"/ " +res);
                matcher = pattern.matcher(temp);
                while (matcher.find()){
                    match = matcher.group(0);
                    adentro = matcher.group(1);
                    System.out.println(match+"/ " +adentro);
                }
            } else {
                adentro = match.replace(adentro, res);
            }
            res = Operate(adentro).getResult();
            System.out.println(res);
            i++;
        }

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" Operacion Combinada ", "" + res);
        return resultado;
    }

    /**
     * Operación atom
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
    public IResultadoOperacion atom(String expresion){
        Pattern pattern = Pattern.compile("^[(][ ]*atom[ ](['].|-?[0-9]+|['][(]*.*[)]*[)]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE);
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

    /**
     * Operación cond
     * @param expresion Programa a evaluar.
     * @return Resultado operacion
     */
    public IResultadoOperacion cond(String expresion){
        Pattern pattern = Pattern.compile("[(]cond [(]([(].*?[)])[)]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);

        String adentro = null;
        while (matcher.find()){
            adentro = matcher.group(1);
        }

        pattern = Pattern.compile("([(].*?[)]+|t[(].*[)])", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(adentro);
        boolean pExito = false;
        String res = null;
        ArrayList<String> matches = new ArrayList<String>();
        Character t = 't';
        while (matcher.find()) {
            matches.add(matcher.group());
        }

        for(int i = 0; i<matches.size();i++){
            if(pExito){
                if(!(t.equals(matches.get(i).charAt(0)))) {
                    res = Operate(matches.get(i)).getResult();
                }
            }
            else {
                if(Operate(matches.get(i)).getEvaluacion()){
                    pExito = true;
                }
                else if(t.equals(matches.get(i).charAt(0))){
                    res = Operate(matches.get(i).substring(1)).getResult();
                }
                else {
                    res = "fallido";
                }
            }
        }

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" cond ", res);
        return resultado;
    }


    /**
     * Operacion equals, revisa si los valores son iguales o diferentes.
     * @param expresion Programa a evaluar
     * @return Resultado operacion
     */
    public IResultadoOperacion equals(String expresion){
        Pattern pattern = Pattern.compile("([\"]\\w+[\"]|-?[0-9]+|list( \\w+)+)", Pattern.CASE_INSENSITIVE);
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

    public IResultadoOperacion createDefun(String expresion){
        Pattern pattern = Pattern.compile("[(]defun (.*?)[ ]([(].*[)])[)]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        String k = "";
        String v = "";
        while (matcher.find()){
            k = matcher.group(1);
            v = matcher.group(2);
        }
        funciones.put(k,v);

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" creacion de funcion ", "exitosa");
        return resultado;
    }

    public IResultadoOperacion defun(String expresion){
        Pattern pattern = Pattern.compile("[(](.*?)[ ]([(].*[)])[)]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);

        //Variables requeridas
        boolean fCorre = false;
        String funcion = "";
        String paramv = "";
        String res = "";

        //Obtener la funcion y el parametro
        while (matcher.find()) {
            funcion = matcher.group(1).trim();
            paramv = matcher.group(2).trim();
        }
        if(funciones.containsKey(funcion)) {
            pattern = Pattern.compile("([(]cond [(][(].*[)][)]|[(].*?[)])", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(funciones.get(funcion));
            String paramk = "";
            ArrayList<String> matches = new ArrayList<String>();

            while (matcher.find()) {
                matches.add(matcher.group());
            }
            for(int i = 0; i<matches.size();i++){
                if(fCorre){
                    res = Operate(matches.get(i)).getResult();
                    System.out.println(res);
                }
                else {
                    paramk = matches.get(i).replaceAll("([(]|[)])", "").trim();
                    int intparamV = Integer.parseInt(Operate(paramv).getResult());

                    myVars.put(paramk,intparamV);
                    fCorre = true;
                }
            }
        }else
            res = "Funcion no existente";
            System.out.println(res);

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" " + funcion + "", res);
        return resultado;
    }

    public IResultadoOperacion numero(String expresion) {

        String respuesta = expresion.replaceAll("([(]|[)])", "").trim();

        OperacionesAritmeticas resultado = new OperacionesAritmeticas();
        resultado.aniadirResultado(" menor ", "" + respuesta);
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
            case 13:
                return combinada(expresion);
            case 14:
                return createDefun(expresion);
            case 15:
                return defun(expresion);
            case 16:
                return numero(expresion);
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
                    public String getResult() {
                        return null;
                    }

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
