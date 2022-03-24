package comun;

public interface IResultadoOperacion {
    public String perzonalizarOperacion();
    public void aniadirResultado(String key, String result);
    public String getResult();
    public void aniadirResultado(String key, String result, boolean evaluacion);
    public boolean getEvaluacion();
}
