package comun;

/**
 * Interfaz para las operaciones aritméticas
 *
 * @author Diego Alexander Hernández Silvestre
 * @author Renatto Esteban Guzman Sosa
 * @author Adrian Fulladolsa Palma
 * @author David Jonathan Aragon Vasquez
 */
public interface IResultadoOperacion {
    public String perzonalizarOperacion();
    public void aniadirResultado(String key, String result);
    public String getResult();
    public void aniadirResultado(String key, String result, boolean evaluacion);
    public boolean getEvaluacion();
}
