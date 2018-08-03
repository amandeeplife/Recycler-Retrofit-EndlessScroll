package com.example.amanuel.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.amanuel.myapplication.Adapter.CustomAdapter;
import com.example.amanuel.myapplication.Client.ApiClient;
import com.example.amanuel.myapplication.Client.CatApi;
import com.example.amanuel.myapplication.model.Cat;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private ProgressBar progressBar;
    private GridLayoutManager layoutManager;
    private int page_num = 2;
    private CatApi catApi;
    private boolean isLoding = true;
    private int preivoustotal, visibleItemCount, totalItemCout, pastVisibleItems = 0;
    private int view_threashhold = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.myprogressbar);
        recyclerView = findViewById(R.id.myRecyclerview);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        catApi = ApiClient.getApiClient().create(CatApi.class);
        Call<List<Cat>> call = catApi.getCats(page_num);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                List<Cat> cats = response.body();
                adapter = new CustomAdapter(cats, MainActivity.this);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManager.getChildCount();
                totalItemCout = layoutManager.getItemCount();
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if (dy > 0) {
                    if (isLoding) {
                        if (totalItemCout > preivoustotal) {
                            isLoding = false;
                            preivoustotal = totalItemCout;
                        }
                    }
                    if (!isLoding && (totalItemCout - visibleItemCount <= pastVisibleItems + view_threashhold))
                        page_num += 1;
                    performPagination();
                    isLoding = true;
                }
            }
        });
    }

    private void performPagination() {
        Call<List<Cat>> call2 = catApi.getCats(page_num);
        progressBar.setVisibility(View.VISIBLE);
        call2.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                Toast.makeText(MainActivity.this, "dd", Toast.LENGTH_LONG).show();
                List<Cat> cats = response.body();
                adapter.addMoreCats(cats);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {

            }
        });
    }
}
