package com.example.administrator.app_note;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adapter_note extends BaseAdapter {
    private Context context;
    private int layout;
    private List<note>noteList;

    public adapter_note(Context context, int layout, List<note> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
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
        TextView txt_title;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder viewHolder;
        if(convertView == null){
            viewHolder=new viewHolder();

            convertView=LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);

            viewHolder.txt_title=(TextView)convertView.findViewById(R.id.textview_title);

            convertView.setTag(viewHolder);
        }else {
            viewHolder=(viewHolder)convertView.getTag();
        }

        //gán  giá trị trong mảng

        viewHolder.txt_title.setText(noteList.get(position).getTitle_note());




        return convertView;
    }
}
