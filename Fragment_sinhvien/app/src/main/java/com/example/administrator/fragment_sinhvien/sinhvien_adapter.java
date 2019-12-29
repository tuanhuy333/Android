package com.example.administrator.fragment_sinhvien;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class sinhvien_adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<sinhvien> list_sv;


    @Override
    public int getCount() {
        return list_sv.size();
    }

    public sinhvien_adapter() {
    }

    public sinhvien_adapter(Context context, int layout, List<sinhvien> list_sv) {
        this.context = context;
        this.layout = layout;
        this.list_sv = list_sv;
    }
    public class ViewHolder{
        TextView txtten;
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
        ViewHolder holder;

        if(convertView == null){
            holder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate( layout,null);
            holder.txtten=  (TextView)convertView.findViewById(R.id.txtten);
            convertView.setTag(holder);
        }else {

            holder=(ViewHolder)convertView.getTag();
        }
        sinhvien sv=list_sv.get(position);
        holder.txtten.setText(sv.getTen());
        return convertView;
    }
}
