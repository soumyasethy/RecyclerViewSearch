package com.soumyasethy.mapprr.views;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.soumyasethy.mapprr.R;
import com.soumyasethy.mapprr.model.contributers.Contributer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * `
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 31/12/17.
 */

class ContrbuterAdapter implements ListAdapter {

    private ArrayList<Contributer> contributerList;

    public ContrbuterAdapter(ArrayList<Contributer> contributerList, Context context) {
        this.contributerList = contributerList;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return contributerList.size();
    }

    @Override
    public Object getItem(int i) {
        return contributerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView contributer_avatar = (ImageView) view.findViewById(R.id.contributer_avatar);
        Picasso.with(view.getContext())
                .load(contributerList.get(i).getAvatarUrl())
                .transform(new CircleTransform())
                .placeholder(R.drawable.github)
                .error(R.drawable.github)
                .into(contributer_avatar);

        TextView contributer_name = (TextView) view.findViewById(R.id.contributer_name);
        contributer_name.setText(contributerList.get(i).getLogin());
        return view;

    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
