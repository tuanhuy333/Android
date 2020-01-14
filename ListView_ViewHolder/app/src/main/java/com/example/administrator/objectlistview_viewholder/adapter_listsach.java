package com.example.administrator.objectlistview_viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adapter_listsach extends BaseAdapter {

    private Context context;
    private int layout;
    private List<sach> list_sach;

    public adapter_listsach(Context context, int layout, List<sach> list_sach) {
        this.context = context;
        this.layout = layout;
        this.list_sach = list_sach;
    }

    @Override
    public int getCount() {
        return list_sach.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class viewHolder {
        TextView txttensach;
        TextView txtgiasach;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView == null) {

            //chuyển đổi layout sang java code
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(layout, parent, false);

            //có convertView thì bắt đầu ánh xạ các thành phần
            holder = new viewHolder();
            holder.txttensach = (TextView) convertView.findViewById(R.id.txttensach);
            holder.txtgiasach = (TextView) convertView.findViewById(R.id.txtgiasach);


            convertView.setTag(holder);

        } else {
            holder = (viewHolder) convertView.getTag();
        }
        sach s = list_sach.get(position);

        //gán các giá trị trong mảng vào viewHolder
        holder.txttensach.setText(s.getTensach());
        holder.txtgiasach.setText(s.getGiasach() + "");
        //holder.avt.setImageResource(sv.getAvt()); hình

        return convertView;
    }
}
