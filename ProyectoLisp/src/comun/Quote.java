package comun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Quote {

    public String print(String valor) {
        try{
            return valor;
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String print(float valor) {
        try{
            return String.valueOf(valor);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String print(ArrayList valor) {
        try{
            String res = "";
            for(int i=0; i < valor.size(); i++){
                if(i != 0){
                    res = res + "," + String.valueOf(valor.get(i));
                }
                else{
                    res = String.valueOf(valor.get(i));
                }

            }
            return res;
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String print(HashMap<String, String> valor) {
        try{
            String res = "";
            for(Map.Entry<String,String> entry : valor.entrySet()){
                String k = entry.getKey();
                String v = entry.getValue();
                if(!res.equals("")){
                    res = res + "," + k + "=" + v;
                }
                else{
                    res = k + "=" + v;
                }
            }
            return res;
        }
        catch (Exception e){
            return e.toString();
        }
    }
}

