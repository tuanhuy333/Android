package com.example.viewpaper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.viewpaper.Model.hocsinh;
import com.example.viewpaper.R;

import java.util.List;

public class Adapter_viewpager extends PagerAdapter {

    List<hocsinh> models;
    int layout;
    private Context context;

    public Adapter_viewpager(List<hocsinh> models, int layout, Context context) {
        this.models = models;
        this.layout = layout;
        this.context = context;
    }


    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, container, false);


        ImageView img_hs;
        TextView txt_ten, txt_lop;

        img_hs = view.findViewById(R.id.img_hs);
        txt_ten = view.findViewById(R.id.txt_tenhs);
        txt_lop = view.findViewById(R.id.txt_lophs);

        img_hs.setImageResource(models.get(position).getId());
        txt_ten.setText(models.get(position).getTen());
        txt_lop.setText(models.get(position).getLop());



        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
