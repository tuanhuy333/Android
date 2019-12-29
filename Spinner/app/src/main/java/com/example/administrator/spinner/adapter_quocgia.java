package com.example.administrator.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_quocgia extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<quocgia> list_quocgia;

    public adapter_quocgia(Context context, int layout, ArrayList<quocgia> list_quocgia) {
        this.context = context;
        this.layout = layout;
        this.list_quocgia = list_quocgia;
    }

    @Override
    public int getCount() {
        return list_quocgia.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        private ImageView img_quocgia;
        private TextView txt_ten;
        private TextView txt_dientich;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);

            holder=new ViewHolder();
            holder.img_quocgia=convertView.findViewById(R.id.img_quocgia);
            holder.txt_ten=convertView.findViewById(R.id.txt_tenquocgia);
            holder.txt_dientich=convertView.findViewById(R.id.txt_dientichquocgia);

            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }


        //gán giá trị
        holder.txt_ten.setText(list_quocgia.get(position).getTen());
        holder.txt_dientich.setText(list_quocgia.get(position).getDientich()+"");


        return convertView;
    }
}
