package es.joseljg.aemet_pruebas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
//----------------------------------------------------
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response;
//----------------------------------------------------
public class MainActivity extends AppCompatActivity {

    public  static final String KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwZXBlbHVpczc2QGdtYWlsLmNvbSIsImp0aSI6ImZhNjJmMTM3LWZkM2ItNDBiYi1iNGE4LWNlMWVhNzYwNTk4NiIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjc4ODgyMjYzLCJ1c2VySWQiOiJmYTYyZjEzNy1mZDNiLTQwYmItYjRhOC1jZTFlYTc2MDU5ODYiLCJyb2xlIjoiIn0.B5aM0aKUcSZ5FfdvBSgBF4A7wbF3zn5N_M-QbC1RxyI";
    public  static final String URL_PETICION1 = "https://opendata.aemet.es/opendata/api/valores/climatologicos/inventarioestaciones/todasestaciones/?api_key="+ KEY;
    private TextView txt_respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_respuesta = (TextView) findViewById(R.id.txt_respuesta);

        try {
            hacer_llamada_al_aemet(URL_PETICION1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void hacer_llamada_al_aemet(String URL_PETICION) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL_PETICION)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String myResponse = response.body().string();
                String descripcion = "";
                String estado = "";
                String datos =  "";
                String metadatos =  "";
                try {
                    JSONObject json_respuesta = new JSONObject(myResponse);
                    descripcion = json_respuesta.getString("descripcion");
                    estado = json_respuesta.getString("estado");
                    metadatos =  json_respuesta.getString("datos");
                    datos =  json_respuesta.getString("datos");
                    obtener_datos_aemet(datos);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
            }
        });
    }
//----------------------------------------------------------------------------------------------------------------
    void obtener_datos_aemet(String URL_PETICION) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL_PETICION)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                // transformo los datos json obtenidos a un arrayList<Estacion> estaciones
               // mas información aquí: https://www.javatpoint.com/how-to-convert-json-array-to-arraylist-in-java
                String myResponse = "";
                ArrayList<Estacion> estaciones = new ArrayList<Estacion>();
                try {
                    myResponse = response.body().string();
                    JSONArray jsonArray = new JSONArray(myResponse);
                    if (jsonArray != null) {
                        for (int i=0;i<jsonArray.length();i++){
                            jsonArray.get(i);
                            Gson gson= new Gson();
                            Estacion e1 = gson.fromJson(jsonArray.get(i).toString(),Estacion.class);
                           Log.i("json", "estacion " + i + " : "+ e1.toString());
                           System.out.println("estacion " + i + " i: " + e1.toString());
                            estaciones.add(e1);
                        }
                    }
                    // aquí tendrías que llamar al adaptador del recyclerview como en firebase, añadiendo los datos asíncronos recibidos
                    // adaptador_recyclerview.setListaEstaciones(estaciones);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                String finalMyResponse = myResponse;
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String texto = "";
                        for (Estacion es: estaciones )
                        {
                                texto = texto + es.getNombre() + "\n\n";
                        }
                        txt_respuesta.setText(texto);
                        // txt_respuesta.setText(finalMyResponse);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
            }
        });
    }
}