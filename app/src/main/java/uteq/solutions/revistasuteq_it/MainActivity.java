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
import java.util.HashMap;
import java.util.Map;

import Adaptadores.AdaptadorRevista;
import models.Revista;

public class MainActivity extends AppCompatActivity {
public ListView lstrevista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstrevista = (ListView)findViewById(R.id.lstrevista);

        //View header = getLayoutInflater().inflate(R.layout., null);
        //lstOpciones.addHeaderView(header);

        lstrevista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent intent = new Intent(MainActivity.this, actVolumenes.class);

                Bundle b = new Bundle();
                b.putString("id",((Revista)a.getItemAtPosition(position)).id);
                intent.putExtras(b);

                startActivity(intent);

            }
        });


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://revistas.uteq.edu.ec/ws/journals.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        JSONObject JSONlista = null;
                        try {

                            JSONArray JSONRevista= new JSONArray(response);

                            ArrayList<Revista> lstRevistaA = Revista.JsonObjectsBuild(JSONRevista);

                            AdaptadorRevista adapatorRevista = new AdaptadorRevista(getApplicationContext(), lstRevistaA);

                            lstrevista.setAdapter(adapatorRevista);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "ya no vale", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}