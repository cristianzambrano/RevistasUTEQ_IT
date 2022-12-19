package models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revista {

    public String portada;
    public String name;
    public String id;



    public Revista(JSONObject a) throws JSONException {
        portada =  a.getString("portada").toString() ;

        name =  a.getString("name").toString() ;
        id = a.getString("journal_id").toString();

    }

    public static ArrayList<Revista> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Revista> revistas = new ArrayList<>();

        for (int i = 0; i < datos.length() && i<20; i++) {
            revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return revistas;
    }
}