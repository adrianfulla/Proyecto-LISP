package comun;

public class OperacionesAritmeticas implements IResultadoOperacion {

    String key;
    String result;
    boolean evaluacion;

    @Override
    public String perzonalizarOperacion() {
       String resultado = "El resultado de la operacion " + key + " es: " + result;
       return resultado;
    }



    @Override
    public void aniadirResultado(String key, String result) {
        this.key = key;
        this.result = result;
    }

    @Override
    public String getResult() {
        return this.result;
    }
    public void aniadirResultado(String key, String result, boolean evaluacion) {
        this.key = key;
        this.result = result;
        this.evaluacion = evaluacion;
    }

    @Override
    public boolean getEvaluacion() {
        return evaluacion;
    }

}
