package models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Volumen {
    public String cover;
    public String title;
    public String fecha;
    public String doi;



    public Volumen(JSONObject a) throws JSONException {
        cover =  a.getString("cover").toString() ;
        title =  a.getString("title").toString() ;
        fecha = a.getString("date_published").toString();
        doi =  a.getString("doi").toString() ;

    }

    public static ArrayList<Volumen> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Volumen> volumenes = new ArrayList<>();

        for (int i = 0; i < datos.length() && i<20; i++) {
            volumenes.add(new Volumen(datos.getJSONObject(i)));
        }
        return volumenes;
    }
}
