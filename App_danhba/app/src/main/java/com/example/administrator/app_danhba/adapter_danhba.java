package com.example.administrator.app_danhba;


import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.CALL_PHONE;

public class adapter_danhba extends RecyclerView.Adapter{


    Context context;
    int layout;
    List<row_danhba> list_rowdanhba;




    public adapter_danhba(Context context, int layout, List<row_danhba> list_rowdanhba) {
        this.context = context;
        this.layout = layout;
        this.list_rowdanhba = list_rowdanhba;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView hinh_lienlac;
        TextView ten_lienlac,sdt_lienlac;
        ImageView hinh_xoa;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hinh_lienlac=(ImageView)itemView.findViewById(R.id.hinh_lienlac);
            ten_lienlac=(TextView)itemView.findViewById(R.id.ten_lienlac);
            sdt_lienlac=(TextView)itemView.findViewById(R.id.sdt_lienlac);

            hinh_xoa=(ImageView)itemView.findViewById(R.id.hinh_xoa);
            container=(RelativeLayout)itemView.findViewById(R.id.container);
        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View viewDanhba=LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);

        ViewHolder viewHolder=new ViewHolder(viewDanhba);

        return viewHolder;
    }




    //troi buoc noi dung cua tung thanh phan trong recycle view
    //và cũng là hàm xử lí các sự kiện setText , setOnClickListener cho các thành phần trong row
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        final row_danhba row_danhba=list_rowdanhba.get(i);


        //set hieu ung animation
        ((ViewHolder)viewHolder).container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.scale));

        ((ViewHolder)viewHolder).hinh_lienlac.setAnimation(AnimationUtils.loadAnimation(context,R.anim.animation_listview));


        //ép kiểu
        //gán thông tin khi hiển thị recycler view
        ((ViewHolder) viewHolder).ten_lienlac.setText(row_danhba.getTen_lienlac());
        ((ViewHolder) viewHolder).sdt_lienlac.setText(row_danhba.getSdt_lienlac());

        //gán hình
        //chuyển mảng byte[] thành bitmap
        byte[] Hinhanh=row_danhba.getHinh_lienlac();
        Bitmap bitmap=BitmapFactory.decodeByteArray(Hinhanh,0,Hinhanh.length);
        ((ViewHolder) viewHolder).hinh_lienlac.setImageBitmap(bitmap);






        //xử lí sự kiện click
        ((ViewHolder)viewHolder).container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,insert_danhba.class);
                //gửi những thông tin của danh bạ qua màn hình chỉnh sửa
                Bundle bundle=new Bundle();
                bundle.putInt("id",list_rowdanhba.get(i).getMa_dongdanhba());
                bundle.putString("ten_lienlac",list_rowdanhba.get(i).getTen_lienlac());
                bundle.putString("sdt",list_rowdanhba.get(i).getSdt_lienlac());
                //gửi hình byte[] vào bundle
                bundle.putByteArray("hinh",list_rowdanhba.get(i).getHinh_lienlac());
                intent.putExtra("data_send",bundle);

                context.startActivity(intent);
            }
        });


//        //click vào nút xóa thì truyền tên qua MainActivity (Call Back)
        ((ViewHolder)viewHolder).hinh_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               MainActivity.database.delete_danhba(list_rowdanhba.get(i).getMa_dongdanhba()); //xóa trong database

               list_rowdanhba.remove(i); //phải xóa từ danh sách luôn (vì recycler view hiển thị danh sách mà)
               notifyDataSetChanged();

            }
        });



    }
    @Override
    public int getItemCount() {
        return list_rowdanhba.size();
    }

    public void setAfterFiltered(List<row_danhba> FilterList){

        list_rowdanhba=new ArrayList<>();
        list_rowdanhba.addAll(FilterList);

        notifyDataSetChanged();


    }



}
