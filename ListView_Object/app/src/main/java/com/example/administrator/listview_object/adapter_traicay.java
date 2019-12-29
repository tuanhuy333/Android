package com.example.administrator.listview_object;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapter_traicay extends BaseAdapter {

    private Context context;
    private int layout;
    private List<traicay> traicayList;

    public adapter_traicay(Context context, int layout, List<traicay> traicayList) {
        this.context = context;
        this.layout = layout;
        this.traicayList = traicayList;
    }

    @Override
    public int getCount() { //số dòng để thể hiện đối tượng
        return traicayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=inflater.inflate(layout,null);

        TextView txtten=(TextView) convertView.findViewById(R.id.txt_mota);
        TextView txtmota=(TextView) convertView.findViewById(R.id.txt_ten);
        ImageView img=(ImageView) convertView.findViewById(R.id.img);

        //gán giá trị
        traicay traicay=traicayList.get(position);

        txtmota.setText(traicay.getMota());
        txtten.setText(traicay.getTen());
        img.setImageResource(traicay.getHinh());

        return convertView;
    }
}
