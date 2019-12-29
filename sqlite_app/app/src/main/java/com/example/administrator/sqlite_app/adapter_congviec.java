package com.example.administrator.sqlite_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapter_congviec extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<congviec> congviecList;

    public adapter_congviec(MainActivity context, int layout, List<congviec> congviecList) {
        this.context = context;
        this.layout = layout;
        this.congviecList = congviecList;
    }

    @Override
    public int getCount() {
        return congviecList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    private class viewHolder{
//        TextView txt_tencongviec;
//        ImageView img_update,img_xoa;
//    }

    public class viewHolder{
        TextView txt_ten;
        ImageView img_edit,img_del;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView == null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder=new viewHolder();

            holder.txt_ten=(TextView)convertView.findViewById(R.id.txttennote);
            holder.img_edit=(ImageView)convertView.findViewById(R.id.imgupdate);
            holder.img_del=(ImageView)convertView.findViewById(R.id.imgdel);

            convertView.setTag(holder);

        }
        else{
            holder=(viewHolder)convertView.getTag();
        }

        final  congviec c=congviecList.get(position);
        holder.txt_ten.setText(c.getTen_congviec());

        //sửa xóa

        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialog_sua(c.getId_congviec(),c.getTen_congviec());

                context.hienthiID(c.getId_congviec());
            }
        });

        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.xoaCV(c.getId_congviec());
            }
        });
        return convertView;
//        viewHolder holder;
//       if(convertView == null){
//           holder=new viewHolder();
//           LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//           convertView=inflater.inflate(layout,null);
//
//           holder.txt_tencongviec=(TextView)convertView.findViewById(R.id.txttennote);
//           holder.img_update=(ImageView)convertView.findViewById(R.id.imgupdate);
//           holder.img_xoa=(ImageView)convertView.findViewById(R.id.imgdel);
//
//           convertView.setTag(holder);
//
//       }
//       else {
//           holder=(viewHolder)convertView.getTag();
//       }
//
//       congviec c=congviecList.get(position);
//
//       holder.txt_tencongviec.setText(c.getTen_congviec());
//
//       return convertView;

    }
}
