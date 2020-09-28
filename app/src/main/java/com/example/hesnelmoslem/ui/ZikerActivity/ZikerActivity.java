package com.example.hesnelmoslem.ui.ZikerActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hesnelmoslem.R;
import com.example.hesnelmoslem.ui.soundActivity.SoundActivity;
import com.example.hesnelmoslem.data.MenuActivity.ZikerConten;
import com.example.hesnelmoslem.data.ZikerItem;
import com.example.hesnelmoslem.ui.MenuActivity.MenuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ZikerActivity extends AppCompatActivity implements View.OnClickListener , ZikerItemAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ZikerItemAdapter zikerItemAdapter;
    private Button btn_reload;
    private ProgressBar progressBar;
    private RequestQueue requestQueue;
    private ZikerConten zikerConten;
    private ImageView btn_play_all_azkar_sounds;

    public static final String AUDIO_URL_EXTRA="com.example.hesnelmoslem.ui.ZikerActivity.AUDIO_URL_EXTRA";
    public static final String TEXT_TITLE_EXTRA="com.example.hesnelmoslem.ui.ZikerActivity.TEXT_TITLE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziker);
        recyclerView = findViewById(R.id.recyclerView_zikers);
        btn_reload=findViewById(R.id.btn_retry_ziker);
        progressBar=findViewById(R.id.progressBar2);
        btn_play_all_azkar_sounds=findViewById(R.id.btn_play_all_sounds);
        btn_play_all_azkar_sounds.setOnClickListener(this);

        zikerItemAdapter = new ZikerItemAdapter();
        zikerItemAdapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(zikerItemAdapter);
        btn_reload.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(this);
        zikerConten=(ZikerConten)getIntent().getSerializableExtra(MenuActivity.MENU_ITEM_EXTRA);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        parsJason();


    }

    @Override
    public void onBackPressed() {
        finish();
    }


    private void parsJason()
    {

       String[] subStringsTextUrl=zikerConten.getTextUrl().split(":");
        zikerConten.setTextUrl("https:"+subStringsTextUrl[1]);
        String[] subStringsAudioUrl=zikerConten.getAudio_url().split(":");
        zikerConten.setAudio_url("https:"+subStringsAudioUrl[1]);


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, zikerConten.getTextUrl(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONArray jsonArray=response.getJSONArray(zikerConten.getTitle());
                            List<ZikerItem> zikerItems=new ArrayList<>();
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                                 zikerItems.add(new ZikerItem(jsonObject.getInt("ID"),
                                         jsonObject.getString("ARABIC_TEXT"),
                                     jsonObject.getString("LANGUAGE_ARABIC_TRANSLATED_TEXT"),
                                     jsonObject.getString("TRANSLATED_TEXT"),
                                     jsonObject.getInt("REPEAT"),
                                     jsonObject.getString("AUDIO")));
                            }

                            zikerItemAdapter.setZikerItems(zikerItems);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ZikerActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btn_reload.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ZikerActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retry_ziker:
            progressBar.setVisibility(View.VISIBLE);
            btn_reload.setVisibility(View.GONE);
            parsJason();
            break;
            case R.id.btn_play_all_sounds:
                Intent intent=new Intent(ZikerActivity.this, SoundActivity.class);
                intent.putExtra(ZikerActivity.AUDIO_URL_EXTRA,zikerConten.getAudio_url());
                startActivity(intent);

                break;
        }

    }

    @Override
    public void onItemClick(ZikerItem zikerItem) {

        Intent intent=new Intent(ZikerActivity.this, SoundActivity.class);
        intent.putExtra(ZikerActivity.AUDIO_URL_EXTRA,zikerItem.getAudio());
        startActivity(intent);
        //TODO implementing sound player activity
    }
}