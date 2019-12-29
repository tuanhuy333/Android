package com.example.administrator.volley_getjsonobject_fromphp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapter_user extends BaseAdapter {

    private Context context;
    private int layout;
    private List<user> userList;


    public adapter_user(Context context, int layout, List<user> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView txt_username;
        TextView txt_password;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        //chưa có giao diện
        if(convertView == null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(layout,parent,false); //chuyển xml sang code

            viewHolder=new ViewHolder();

            viewHolder.txt_username=(TextView)convertView.findViewById(R.id.username);
            viewHolder.txt_password=(TextView)convertView.findViewById(R.id.password);


            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        user u=userList.get(position);

        viewHolder.txt_username.setText(u.getUser_name());
        viewHolder.txt_password.setText(u.getPassword());


        return convertView;
    }
}
