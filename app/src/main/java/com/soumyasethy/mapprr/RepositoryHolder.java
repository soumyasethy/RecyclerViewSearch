package com.soumyasethy.mapprr;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.soumyasethy.mapprr.model.Item;
import com.squareup.picasso.Picasso;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 30/12/17.
 */


public class RepositoryHolder extends RecyclerView.ViewHolder {

    private TextView tv_name,  full_name, tv_api_level, fork, star, watcher;
    private ImageView avatar;
    private Item item;

    public RepositoryHolder(View view) {
        super(view);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        full_name = (TextView) view.findViewById(R.id. tv_version);
        tv_api_level = (TextView) view.findViewById(R.id.tv_api_level);
        avatar = (ImageView) view.findViewById(R.id.avatar);
        star = (TextView) view.findViewById(R.id.star);
        watcher = (TextView) view.findViewById(R.id.watcher);
        fork = (TextView) view.findViewById(R.id.fork);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), item.getFullName(), 1000).show();
            }
        });

    }
    
    public void update(Item item){

        if(item == null)
            return;
        else
            this.item = item;

        String url = item.getOwner().getAvatarUrl();
        System.out.println(url);
        Picasso.with(itemView.getContext())
                .load(item.getOwner().getAvatarUrl())
                .placeholder(R.drawable.github)
                .error(R.drawable.github)
                .into(avatar);
         tv_name.setText(item.getName());
          full_name.setText(item.getFullName());
         tv_api_level.setText(item.getLanguage());
         watcher.setText(String.valueOf(item.getWatchers()));
         fork.setText(String.valueOf(item.getForks()));
         star.setText(String.valueOf(item.getWatchers()));

    }
}
