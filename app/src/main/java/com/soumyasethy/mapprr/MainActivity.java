package com.soumyasethy.mapprr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.soumyasethy.mapprr.model.Item;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements AAH_FabulousFragment.Callbacks {
  public static final String BASE_URL = "https://api.github.com/";
  public RecyclerView mRecyclerView;
  FloatingActionButton fab;
  String user = "", sort = "stars", order = "desc";
  private ArrayList<Item> mArrayList;
  private DataAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    fab = (FloatingActionButton) findViewById(R.id.fab);
    initViews();
    loadJSON();
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Filter dialogFrag = Filter.newInstance(sort, order);
        dialogFrag.setParentFab(fab);
        dialogFrag.show(getSupportFragmentManager(), dialogFrag.getTag());
      }
    });

  }

  private void initViews() {
    mRecyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
    mRecyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(layoutManager);
  }

  public void loadJSON() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    RequestInterface request = retrofit.create(RequestInterface.class);
    Call<JSONResponse> call = request.getJSON();
    call.enqueue(new Callback<JSONResponse>() {
      @Override
      public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
        JSONResponse jsonResponse = response.body();
        mArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getItems()));
        mAdapter = new DataAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);
      }

      @Override
      public void onFailure(Call<JSONResponse> call, Throwable t) {
        Log.d("Error", t.getMessage());
      }
    });
  }

  public void loadSEARCH(String user, String sort, String order) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    RequestInterface request = retrofit.create(RequestInterface.class);
    Call<JSONResponse> call = request.getSearch(user, sort, order);
    call.enqueue(new Callback<JSONResponse>() {
      @Override
      public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

        JSONResponse jsonResponse = response.body();
        if (jsonResponse != null) {
          mArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getItems()));
          mAdapter = new DataAdapter(mArrayList);
          mRecyclerView.clearAnimation();
          mRecyclerView.setAdapter(mAdapter);

        }
      }

      @Override
      public void onFailure(Call<JSONResponse> call, Throwable t) {
        Log.d("Error", t.getMessage());
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu_main, menu);
    MenuItem search = menu.findItem(R.id.search);
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
    search(searchView);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    return super.onOptionsItemSelected(item);
  }

  private void search(SearchView searchView) {

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getApplicationContext(), "Query : " + query, 1000).show();
        if (query != null) {
          loadSEARCH(query, sort, order);
          user = query;
        }
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return true;
      }
    });
  }

  @Override
  public void onResult(Object result) {
    if (result.toString().equalsIgnoreCase("swiped_down")) {
      //do something or nothing
    } else {
      if (result.toString().equalsIgnoreCase("stars")) {
        sort = "stars";
      }
      if (result.toString().equalsIgnoreCase("forks")) {
        sort = "forks";
      }
      if (result.toString().equalsIgnoreCase("updated")) {
        sort = "updated";
      }
      if (result.toString().equalsIgnoreCase("desc")) {
        order = "desc";
      }
      if (result.toString().equalsIgnoreCase("asc")) {
        order = "asc";
      }
      loadSEARCH(user, sort, order);

    }

  }

}
