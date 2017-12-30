package com.soumyasethy.mapprr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soumyasethy.mapprr.model.Repository;
import com.soumyasethy.mapprr.views.RepositoryHolder;

import java.util.ArrayList;

public class DataAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> //implements Filterable {
{
  private ArrayList<Repository> mArrayList;
  private ArrayList<Repository> mFilteredList;

  public DataAdapter(ArrayList<Repository> arrayList) {
    mArrayList = arrayList;
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

//    @Override
//    public Filter getFilter() {
//
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//
//                String charString = charSequence.toString();
//
//                if (charString.isEmpty()) {
//
//                    mFilteredList = mArrayList;
//                } else {
//
//                    ArrayList<Repository> filteredList = new ArrayList<>();
//
//                    for (Repository androidVersion : mArrayList) {
//
//                        if (androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getDescription().toLowerCase().contains(charString)) {
//
//                            filteredList.add(androidVersion);
//                        }
//                    }
//
//                    mFilteredList = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = mFilteredList;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                mFilteredList = (ArrayList<Repository>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }


}