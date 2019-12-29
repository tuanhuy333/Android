package com.example.sync_sqlite_mysql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter_row_name extends RecyclerView.Adapter {

    Context context;
    int layout;
    List<Name> nameList;



    public adapter_row_name(Context context, int layout, List<Name> nameList) {
        this.context = context;
        this.layout = layout;
        this.nameList = nameList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_name;
        ImageView img_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name=(itemView).findViewById(R.id.name_txtname);


            img_status=(itemView).findViewById(R.id.name_imgname);


        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Name n=nameList.get(position);

        //txt_name
        ((ViewHolder)holder).txt_name.setText(n.getName());


        //img_sync
        int status_sync=n.getStatus_sync();
        if(status_sync == 0){
            ((ViewHolder)holder).img_status.setImageResource(R.drawable.ic_sync_black_24dp);
        }else {
            ((ViewHolder)holder).img_status.setImageResource(R.drawable.ic_check_black_24dp);
        }

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
