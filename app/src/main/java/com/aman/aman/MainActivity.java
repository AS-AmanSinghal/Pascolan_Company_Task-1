package com.aman.aman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public MainAdapter adapter;
    public static List<Aman> listData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        adapter = new MainAdapter(MainActivity.this, listData);
        recyclerView.setAdapter(adapter);
        getSupportActionBar().hide();
        //getData();
        fetchData process=new fetchData(new Interface()
        {
            @Override
            public void onDataFetch()
            {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        Collections.shuffle(listData);
                        adapter.notifyDataSetChanged();
                    }
                });
//                adapter.notifyDataSetChanged();
            }
        });
        process.execute();
        Log.d("DART", String.valueOf(listData.size()));

    }
}
