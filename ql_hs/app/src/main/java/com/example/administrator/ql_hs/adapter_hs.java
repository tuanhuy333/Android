package com.example.administrator.ql_hs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapter_hs extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<hocsinh> hocsinhList;

    public adapter_hs(MainActivity context, int layout, List<hocsinh> hocsinhList) {
        this.context = context;
        this.layout = layout;
        this.hocsinhList = hocsinhList;
    }

    @Override
    public int getCount() {
        return hocsinhList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class viewHolder{
        TextView txt_ten;
        ImageView img_del;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView == null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView=inflater.inflate(layout,null);

            //anh xa
            holder =new viewHolder();

            holder.txt_ten=(TextView)convertView.findViewById(R.id.txt_tenhs);
            holder.img_del=(ImageView)convertView.findViewById(R.id.img_del);

            //set vao convertView
            convertView.setTag(holder);


        }
        else{
            holder=(viewHolder)convertView.getTag();
        }

        final hocsinh hs=hocsinhList.get(position);

        holder.txt_ten.setText(hs.getTen());

        //xoa
        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.xoa(hs.getId());
            }
        });



        return convertView;
    }
}
