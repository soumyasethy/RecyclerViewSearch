package com.soumyasethy.mapprr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soumyasethy.mapprr.model.repository.Repository;
import com.soumyasethy.mapprr.views.RepositoryHolder;

import java.util.ArrayList;

public class DataAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> //implements Filterable {
{
  private ArrayList<Repository> mFilteredList;

  public DataAdapter(ArrayList<Repository> arrayList) {
    mFilteredList = arrayList;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view =
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
    return new RepositoryHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    if (viewHolder instanceof RepositoryHolder) {
      ((RepositoryHolder) viewHolder).update(mFilteredList.get(i));
    }
  }

  @Override
  public int getItemCount() {
    return mFilteredList.size();
  }
}