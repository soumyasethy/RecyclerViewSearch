package com.soumyasethy.mapprr.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soumyasethy.mapprr.R;
import com.soumyasethy.mapprr.model.contributers.Contributer;
import com.squareup.picasso.Picasso;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 30/12/17.
 */


public class ContributorsHolder extends RecyclerView.ViewHolder {

    TextView contributer_name;
    ImageView contributer_avatar;
    Contributer contributer;

    public ContributorsHolder(View view) {
        super(view);

        contributer_avatar = (ImageView) view.findViewById(R.id.contributer_avatar);
        contributer_name = (TextView) view.findViewById(R.id.contributer_name);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), contributer.getLogin(), Toast.LENGTH_SHORT).show();
                //RepositoryActivity.launch(view.getContext(),contributer);
            }
        });

    }

    public void update(Contributer contributer) {

        if (contributer == null) {
            return;
        } else {
            this.contributer = contributer;
        }

        String url = contributer.getAvatarUrl();
        System.out.println(url);
        Picasso.with(itemView.getContext())
                .load(contributer.getAvatarUrl())
                .transform(new CircleTransform())
                .placeholder(R.drawable.github)
                .error(R.drawable.github)
                .into(contributer_avatar);
        contributer_name.setText(contributer.getLogin());


    }
}
