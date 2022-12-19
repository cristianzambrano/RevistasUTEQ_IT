package uteq.solutions.revistasuteq_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adaptadores.AdaptadorRevista;
import Adaptadores.AdaptadorVolumen;
import models.Revista;
import models.Volumen;

public class actVolumenes extends AppCompatActivity {
    public ListView lstvolumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_volumenes);

        lstvolumen = (ListView)findViewById(R.id.lstvolumenes);

        Bundle bundle = this.getIntent().getExtras();
        String IDRevista = bundle.getString("id");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+IDRevista;


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        JSONObject JSONlista = null;
                        try {

                            JSONArray JSONVolumen= new JSONArray(response);

                            ArrayList<Volumen> lstvolumenA = Volumen.JsonObjectsBuild(JSONVolumen);

                            AdaptadorVolumen adapatovolumen = new AdaptadorVolumen(getApplicationContext(), lstvolumenA);

                            lstvolumen.setAdapter(adapatovolumen);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(actVolumenes.this, "ya no vale", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}