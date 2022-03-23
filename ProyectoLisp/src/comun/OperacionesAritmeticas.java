package comun;

public class OperacionesAritmeticas implements IResultadoOperacion {

    String key;
    String result;

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
}
