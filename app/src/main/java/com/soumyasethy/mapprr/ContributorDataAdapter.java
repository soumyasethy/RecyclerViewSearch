package com.soumyasethy.mapprr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soumyasethy.mapprr.model.contributers.Contributer;
import com.soumyasethy.mapprr.views.ContributorsHolder;
import com.soumyasethy.mapprr.views.RepositoryHolder;

import java.util.ArrayList;

public class ContributorDataAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Contributer> mFilteredList;

    public ContributorDataAdapter(ArrayList<Contributer> arrayList) {
        mFilteredList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view =
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contributers_item, viewGroup, false);
        return new RepositoryHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof RepositoryHolder) {
            ((ContributorsHolder) viewHolder).update(mFilteredList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }
}