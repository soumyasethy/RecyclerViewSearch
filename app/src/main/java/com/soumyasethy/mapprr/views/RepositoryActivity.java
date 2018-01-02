package com.soumyasethy.mapprr.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soumyasethy.mapprr.ContributorDataAdapter;
import com.soumyasethy.mapprr.JSONResponse;
import com.soumyasethy.mapprr.R;
import com.soumyasethy.mapprr.RequestInterface;
import com.soumyasethy.mapprr.Webview;
import com.soumyasethy.mapprr.model.contributers.Contributer;
import com.soumyasethy.mapprr.model.repository.Repository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryActivity extends AppCompatActivity {

  private static final String REPO = "repo";
    private static final String WEB_URL = "web_url";
    TextView projectLink, description;
    ImageView avatar;
    RecyclerView contribute;
    private Repository repo;
    private ContributorDataAdapter mAdapter;

    public static void launch(Context context, Repository repository) {
        Intent i = new Intent(context, RepositoryActivity.class);
        i.putExtra(REPO, repository);
        context.startActivity(i);
    }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repository);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    initViews();

    Bundle extras = getIntent().getExtras();
    if(extras == null || extras.getSerializable(REPO) == null) {
      finish();
      return;
    }

    repo = (Repository) extras.getSerializable(REPO);

    setRepositories(repo);
      //Toast.makeText(this, "Found "+repo, Toast.LENGTH_LONG).show();

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
  }

  private void initViews() {
    //
      projectLink = (TextView) findViewById(R.id.projectLink);
      description = (TextView) findViewById(R.id.descriptionLink);
      avatar = (ImageView) findViewById(R.id.avatar);
      contribute = (RecyclerView) findViewById(R.id.card_recycler_view);
      contribute.setHasFixedSize(true);
      RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
      contribute.setLayoutManager(layoutManager);

  }

    public void setRepositories(final Repository repository) {
        getSupportActionBar().setTitle(repository.getName());
        projectLink.setText(repository.getHtmlUrl());
        projectLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), Webview.class);
                Bundle b = new Bundle();
                b.putString(WEB_URL, repository.getHtmlUrl());
                i.putExtras(b);
                startActivity(i);


            }
        });

        description.setText(repository.getDescription());
        Picasso.with(avatar.getContext())
                .load(repository.getOwner().getAvatarUrl())
                .transform(new CircleTransform())
                .placeholder(R.drawable.github)
                .error(R.drawable.github)
                .into(avatar);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/repos/soumya4017/SoumyaSethy/")//repository.getContributorsUrl()+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getContributors();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                ArrayList<Contributer> contributorsList = new ArrayList<>(Arrays.asList(jsonResponse.getContributer()));
                mAdapter = new ContributorDataAdapter(contributorsList);
                contribute.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });


  }

}
