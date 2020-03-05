package com.example.search_filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Celeb extends RecyclerView.Adapter implements Filterable {

    Context context;
    int layout;
    List<Celebrity> celebrityList;
    List<Celebrity> celebrityListFiltered;

    public Adapter_Celeb(Context context, int layout, List<Celebrity> celebrityList) {
        this.context = context;
        this.layout = layout;
        this.celebrityList = celebrityList;  //data ban dau
        this.celebrityListFiltered = celebrityList; //data da loc
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            //run on background thread
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    celebrityListFiltered = celebrityList;
                } else {
                    List<Celebrity> list_temp = new ArrayList<>();
                    for (Celebrity item : celebrityList) {
                        if (item.getName().contains(charString)) {
                            list_temp.add(item);
                        }

                    }

                    celebrityListFiltered = list_temp;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = celebrityListFiltered;

                return filterResults;
            }

            //run on UI thread
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                celebrityListFiltered = (ArrayList<Celebrity>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class mViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name, txt_nationality;
        ImageView img_avatar;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);


            txt_name = itemView.findViewById(R.id.name);
            txt_nationality = itemView.findViewById(R.id.nationality);
            img_avatar = itemView.findViewById(R.id.avartar);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        mViewHolder mViewHolder = new mViewHolder(view);

        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Celebrity celeb = celebrityListFiltered.get(position);


        ((mViewHolder) holder).txt_name.setText(celeb.getName());
        ((mViewHolder) holder).txt_nationality.setText(celeb.getNationality());
        ((mViewHolder) holder).img_avatar.setImageResource(celeb.getAvatar());
    }

    @Override
    public int getItemCount() {
        return celebrityListFiltered.size();
    }


}
