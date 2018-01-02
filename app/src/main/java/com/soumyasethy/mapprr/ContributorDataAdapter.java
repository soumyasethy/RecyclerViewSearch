package com.soumyasethy.mapprr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soumyasethy.mapprr.model.contributers.Contributer;
import com.soumyasethy.mapprr.model.repository.Repository;
import com.soumyasethy.mapprr.views.ContributorsHolder;
import com.soumyasethy.mapprr.views.RepositoryHolder;

import java.util.ArrayList;

public class ContributorDataAdapter<DATA>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_RERPOSITORY = 1;
    private static final int VIEW_TYPE_CONTRIBUTOR = 2;
    private ArrayList<DATA> mFilteredList;

    public ContributorDataAdapter(ArrayList<DATA> arrayList) {
        mFilteredList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_RERPOSITORY: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                    .contributers_item, viewGroup, false);
                return new RepositoryHolder(view);
            }
            case VIEW_TYPE_CONTRIBUTOR:
            default: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                    .contributers_item, viewGroup, false);
                return new ContributorsHolder(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof RepositoryHolder) {
            ((RepositoryHolder) viewHolder).update((Repository) mFilteredList.get(i));
        }
        if (viewHolder instanceof ContributorsHolder) {
            ((ContributorsHolder) viewHolder).update((Contributer) mFilteredList.get(i));
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mFilteredList.get(position) instanceof Contributer) {
            return VIEW_TYPE_CONTRIBUTOR;
        } else if (mFilteredList.get(position) instanceof Repository) {
            return VIEW_TYPE_RERPOSITORY;
        } else {
            return VIEW_TYPE_RERPOSITORY;
        }

    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }
}