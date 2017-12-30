package com.soumyasethy.mapprr.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soumyasethy.mapprr.R;
import com.soumyasethy.mapprr.Webview;
import com.soumyasethy.mapprr.model.Repository;
import com.squareup.picasso.Picasso;

public class RepositoryActivity extends AppCompatActivity {

  private static final String REPO = "repo";
    private static final String WEB_URL = "web_url";
    TextView projectLink, description;
    ImageView avatar;
  private Repository repo;

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
  }
}
