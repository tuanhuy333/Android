package com.example.bottom_navigation.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottom_navigation.R;
import com.example.bottom_navigation.fragment.OnClickItem;
import com.example.bottom_navigation.fragment.home_fragment;

import java.util.ArrayList;
import java.util.List;

public class adapter_sanpham_card extends RecyclerView.Adapter {

    private Context context; //bối cảnh
    private int layout;
    private List <sanpham> sanphamList;



    public adapter_sanpham_card(){}


    public adapter_sanpham_card(Context context, int layout, List<sanpham> sanphamList) {
        this.context = context;
        this.layout = layout;
        this.sanphamList = sanphamList;
          }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
        return new viewHolder(view);
    }
//    private OnClickItem onClickItem; //khai báo interface
//    //lắng nghe sự kiện
 //   public void setOnClickItem(OnClickItem onClickItem){
//        this.onClickItem=onClickItem;
//    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final sanpham sp=sanphamList.get(i);

        String hoa_chu_dau=sp.getTen_sp().substring(0,1).toUpperCase()+sp.getTen_sp().substring(1);
        ((viewHolder)viewHolder).txt_ten.setText(hoa_chu_dau);

        ((viewHolder)viewHolder).txt_gia.setText(sp.getGia_ban()+"");
        ((viewHolder)viewHolder).txt_soluong.setText(sp.getSoluong_sp()+"");
        ((viewHolder)viewHolder).txt_donvi.setText(sp.getDonvi_tinh());
       // ((viewHolder)viewHolder).txt_gianhap.setText(sp.getGia_nhapvao()+"");
        //show hinh anh ra
        byte[] Hinhanh=sp.getHinh_sp();
        Bitmap bitmap= BitmapFactory.decodeByteArray(Hinhanh,0,Hinhanh.length);
        ((viewHolder) viewHolder).hinh_sp.setImageBitmap(bitmap);

        //sự kiện click vào card view
        ((viewHolder)viewHolder).container1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //them vao don hang
                home_fragment.database.InsertDonhang(
                        "Bán lẻ",
                        sp.getTen_sp(),
                        sp.getGia_nhapvao(),
                        sp.getGia_ban(),
                        sp.getSoluong_sp(),
                        sp.getDonvi_tinh(),
                        sp.getHinh_sp()
                );

            }
        });

    }

    @Override
    public int getItemCount() {
        return sanphamList.size();
    }

    //viewHolder
    public class viewHolder extends RecyclerView.ViewHolder{
        CardView container1;
        ImageView hinh_sp;
        TextView txt_ten,txt_gia,txt_soluong,txt_donvi,txt_gianhap;

        public viewHolder(@NonNull View itemView) {

            super(itemView);
            container1=(CardView) itemView.findViewById(R.id.cardview) ;
            hinh_sp=(ImageView)itemView.findViewById(R.id.hinh_sanpham);
            txt_ten=(TextView)itemView.findViewById(R.id.ten_sanpham) ;
            txt_gia=(TextView)itemView.findViewById(R.id.gia_sanpham);
            txt_soluong=(TextView)itemView.findViewById(R.id.soluong_sanpham);
            txt_donvi=(TextView)itemView.findViewById(R.id.donvi_sanpham);







        }
    }

    public void setAfterFiltered(List<sanpham> FilterList){

        sanphamList=new ArrayList<>();
        sanphamList.addAll(FilterList);

        notifyDataSetChanged();


    }
}
