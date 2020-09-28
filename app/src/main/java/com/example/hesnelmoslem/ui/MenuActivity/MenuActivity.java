package com.example.hesnelmoslem.ui.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hesnelmoslem.R;
import com.example.hesnelmoslem.data.MenuActivity.ZikerConten;

import com.example.hesnelmoslem.ui.ZikerActivity.ZikerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener, ZikerAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ZikerAdapter zikerAdapter;
    private Button btnretry;
    private RequestQueue requestQueue;

    public static final String MENU_ITEM_EXTRA="com.example.hesnelmoslem.ui.MenuActivity.MENU_ITEM_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerView = findViewById(R.id.recycler_View_Menu);
        progressBar = findViewById(R.id.progress_bar_menu);
        btnretry = findViewById(R.id.btn_retry);
        btnretry.setOnClickListener(this);
        zikerAdapter = new ZikerAdapter();
        zikerAdapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(zikerAdapter);
        requestQueue = Volley.newRequestQueue(this);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        LoadAzkar();

    }



    @Override
    public void onBackPressed() {
        finish();
    }

    private void LoadAzkar() {
        String url="https://www.hisnmuslim.com/api/en/husn_en.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            List<ZikerConten> zikerContens = new ArrayList<>();
                            JSONArray languageJSonArry = response.getJSONArray("English");

                            for (int index = 0; index < languageJSonArry.length(); index++) {
                                JSONObject zikerContentJSONObject = (JSONObject) languageJSonArry.get(index);
                                ZikerConten zikerConten = new ZikerConten(zikerContentJSONObject.getInt("ID"),
                                        zikerContentJSONObject.getString("TITLE"),
                                        zikerContentJSONObject.getString("AUDIO_URL"),
                                        zikerContentJSONObject.getString("TEXT"));
                                zikerContens.add(zikerConten);

                            }
                            zikerAdapter.setZikerContenList(zikerContens);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        btnretry.setVisibility(View.VISIBLE);
                        Toast.makeText(MenuActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        //Log.d("TAG", "onErrorResponse: "+error.networkResponse.statusCode);


                    }
                });
        requestQueue.add(jsonObjectRequest);


    }


    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        LoadAzkar();
        v.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(ZikerConten zikerItem) {
        Intent intent = new Intent(MenuActivity.this, ZikerActivity.class);
        intent.putExtra(MenuActivity.MENU_ITEM_EXTRA,zikerItem);
        //TODO completing passing zikerconten object to next activity
        startActivity(intent);

    }
}