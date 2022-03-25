package comun;

/**
 * Clase encargada de ordenar y recibir las operaciones aritméticas
 *
 * @author Diego Alexander Hernández Silvestre
 * @author Renatto Esteban Guzman Sosa
 * @author Adrian Fulladolsa Palma
 * @author David Jonathan Aragon Vasquez
 */

public class OperacionesAritmeticas implements IResultadoOperacion {

    String key;
    String result;
    boolean evaluacion;


    /**
     * Imprime el resultado de la operacion de una manera ordenada y limpia.
     * @return resultado de la operacion
     */
    @Override
    public String perzonalizarOperacion() {
       String resultado = "El resultado de la operacion " + key + " es: " + result;
       return resultado;
    }


    /**
     * Anade el resultado según su tipo de operación
     * @param key Tipo de operacion
     * @param result Resultado de la operacion
     */
    @Override
    public void aniadirResultado(String key, String result) {
        this.key = key;
        this.result = result;
    }

    /**
     * Obtiene la evaluacion
     * @return Evaluacion
     */
    @Override
    public String getResult() {
        return this.result;
    }

    /**
     * Anade el resultado según su tipo de operación
     * @param key Tipo de operacion
     * @param result Resultado de la operacion
     */
    public void aniadirResultado(String key, String result, boolean evaluacion) {
        this.key = key;
        this.result = result;
        this.evaluacion = evaluacion;
    }

    /**
     * Obtiene la evaluacion
     * @return Evaluacion
     */
    @Override
    public boolean getEvaluacion() {
        return evaluacion;
    }

}
