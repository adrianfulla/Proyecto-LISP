package comun;

public interface IResultadoOperacion {
    public String perzonalizarOperacion();
    public void aniadirResultado(String key, String result);
    public void aniadirResultado(String key, String result, boolean evaluacion);
    public boolean getEvaluacion();
}
