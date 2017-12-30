package com.soumyasethy.mapprr.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.soumyasethy.mapprr.R;
import com.soumyasethy.mapprr.model.Repository;

public class RepositoryActivity extends AppCompatActivity {

  private static final String REPO = "repo";
  private Repository repo;



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
    Toast.makeText(this, "Found "+repo, Toast.LENGTH_LONG).show();

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
  }

  public static void launch(Context context, Repository repository) {
    Intent i  = new Intent(context,RepositoryActivity.class);
    i.putExtra(REPO, repository);
    context.startActivity(i);
  }

  public void setRepositories(Repository repository) {
    //
  }
}
