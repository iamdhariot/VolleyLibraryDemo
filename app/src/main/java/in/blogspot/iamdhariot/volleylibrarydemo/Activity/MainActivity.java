package in.blogspot.iamdhariot.volleylibrarydemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import java.util.List;

import in.blogspot.iamdhariot.volleylibrarydemo.Adapter.HeroAdapter;
import in.blogspot.iamdhariot.volleylibrarydemo.Model.Hero;
import in.blogspot.iamdhariot.volleylibrarydemo.R;

public class MainActivity extends AppCompatActivity {

    // json url to fetch json hero data
    private static final String JSON_URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";

    private ListView listView;
    private ProgressBar progressBar;

    // listView
    private List<Hero> heroList;
    //HeroAdapter
    private HeroAdapter heroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view_heroes);
        progressBar = findViewById(R.id.progress_bar);
        heroList = new ArrayList<>();

        // here we implement the request url for json data
        loaoHeroList();

    }

    private void loaoHeroList() {
        // making the progressBar visible
        progressBar.setVisibility(View.VISIBLE);

        // creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // hiding the progressBar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //getting the whole json object from the response
                    JSONObject obj = new JSONObject(response);
                    // We have the array named heroes inside the json object
                    JSONArray heroesArray = obj.getJSONArray("heroes");
                    // now looping through all the elements of json array
                    for(int i=0; i < heroesArray.length(); i++){
                        //getting the json object of the particular index inside the array
                        JSONObject heroObject = heroesArray.getJSONObject(i);
                        //creating a hero object and giving the values from json object
                        Hero hero = new Hero(heroObject.getString("name"),heroObject.getString("imageurl"));

                        //adding the hero to heroList
                        heroList.add(hero);
                    }

                    //setting the data to adapter
                    adapterSetup(heroList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast the error if occurs
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        //creating a requestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the stringRequest to requestQueue
        requestQueue.add(stringRequest);
    }

    private void adapterSetup(List<Hero> heroList) {
        //HeroAdapter object initialize
        heroAdapter = new HeroAdapter(MainActivity.this,heroList);
        //adding the adapter to listView
        listView.setAdapter(heroAdapter);

    }
}