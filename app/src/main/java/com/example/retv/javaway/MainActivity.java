package com.example.retv.javaway;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retv.Constantes;
import com.example.retv.R;
import com.example.retv.api.ApiInterface;
import com.example.retv.controller.Controller;
import com.example.retv.model.ValueModel;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static ApiInterface umoriliApi;
    List<LinkedTreeMap> posts;
    EditText editText;
    private RecyclerView recyclerView;
    private LinkedTreeMap ltm;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller controller = new Controller();
        umoriliApi = controller.getApi();
        posts = new ArrayList<>();
        recyclerView = findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);
        refakt();
        editText = findViewById(R.id.editTextTextPersonName);
        editText.setOnKeyListener((view, i, keyEvent) -> {
            double nmb = Double.parseDouble(editText.getText().toString() + ".0");
            adapter.setNum(nmb);
            recyclerView.getAdapter().notifyDataSetChanged();
            return false;
        });
    }

    private void refakt() {
        umoriliApi.getData(Constantes.API_KEY).enqueue(new Callback<ValueModel>() {
            @Override
            public void onResponse(@NonNull Call<ValueModel> call, Response<ValueModel> response) {
                assert response.body() != null;
                ltm = response.body().getCurrency();
                posts.addAll(ltm.values());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<ValueModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
