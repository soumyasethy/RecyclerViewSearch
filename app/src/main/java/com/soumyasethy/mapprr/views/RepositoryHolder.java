package com.soumyasethy.mapprr.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soumyasethy.mapprr.R;
import com.soumyasethy.mapprr.model.repository.Repository;
import com.squareup.picasso.Picasso;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 30/12/17.
 */


public class RepositoryHolder extends RecyclerView.ViewHolder {

  private TextView tv_name, full_name, tv_api_level, fork, star, watcher;
  private ImageView avatar;
  private Repository repository;

  public RepositoryHolder(View view) {
    super(view);
    tv_name = (TextView) view.findViewById(R.id.tv_name);
    full_name = (TextView) view.findViewById(R.id.tv_version);
    tv_api_level = (TextView) view.findViewById(R.id.tv_api_level);
    avatar = (ImageView) view.findViewById(R.id.avatar);
    star = (TextView) view.findViewById(R.id.star);
    watcher = (TextView) view.findViewById(R.id.watcher);
    fork = (TextView) view.findViewById(R.id.fork);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          //Toast.makeText(view.getContext(), repository.getFullName(), Toast.LENGTH_SHORT).show();
        RepositoryActivity.launch(view.getContext(),repository);
      }
    });

  }

  public void update(Repository repository) {

    if (repository == null) {
      return;
    } else {
      this.repository = repository;
    }

    String url = repository.getOwner().getAvatarUrl();
    System.out.println(url);
    Picasso.with(itemView.getContext())
        .load(repository.getOwner().getAvatarUrl())
            .transform(new CircleTransform())
        .placeholder(R.drawable.github)
        .error(R.drawable.github)
        .into(avatar);
    tv_name.setText(repository.getName());
    full_name.setText(repository.getFullName());
    tv_api_level.setText(repository.getLanguage());
    watcher.setText(String.valueOf(repository.getWatchers()));
    fork.setText(String.valueOf(repository.getForks()));
    star.setText(String.valueOf(repository.getWatchers()));

  }
}
