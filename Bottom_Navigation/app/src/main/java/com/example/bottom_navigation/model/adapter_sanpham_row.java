package com.example.bottom_navigation.model;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottom_navigation.MainActivity;
import com.example.bottom_navigation.R;
import com.example.bottom_navigation.fragment.OnClickItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.bottom_navigation.fragment.sanpham_fragment.database;

public class adapter_sanpham_row extends RecyclerView.Adapter {
    private Context context; //bối cảnh
    private int layout;
    private List <sanpham> sanphamList;

    public SQLite sqLite;


    public adapter_sanpham_row(Context context, int layout, List<sanpham> sanphamList) {
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


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final sanpham sp=sanphamList.get(i);

        String hoa_chu_dau=sp.getTen_sp().substring(0,1).toUpperCase()+sp.getTen_sp().substring(1);
        ((viewHolder)viewHolder).txt_ten.setText(hoa_chu_dau);

        ((viewHolder)viewHolder).txt_gianhap.setText(sp.getGia_nhapvao()+"");
        ((viewHolder)viewHolder).txt_giaban.setText(sp.getGia_ban()+"");
        ((viewHolder)viewHolder).txt_soluong.setText(sp.getSoluong_sp()+"");
        ((viewHolder)viewHolder).txt_donvi.setText(sp.getDonvi_tinh());
        // ((viewHolder)viewHolder).txt_gianhap.setText(sp.getGia_nhapvao()+"");
        //show hinh anh ra
        byte[] Hinhanh=sp.getHinh_sp();
        Bitmap bitmap= BitmapFactory.decodeByteArray(Hinhanh,0,Hinhanh.length);
        ((viewHolder) viewHolder).hinh_sp.setImageBitmap(bitmap);


        //sự kiện click vào card view
        ((viewHolder)viewHolder).container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        //xoa san pham
        ((viewHolder)viewHolder).xoa_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xoa trong database
                database.delete_sanpham(sanphamList.get(i).getMa_sp()); //xóa trong database


                //xóa trong danh sách
                sanphamList.remove(i);

                //lưu thay đổi data trong List
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanphamList.size();
    }


    //viewHolder
    public class viewHolder extends RecyclerView.ViewHolder {
        CardView container;
        ImageView hinh_sp,xoa_sp;
        TextView txt_ten,txt_gianhap,txt_giaban,txt_soluong,txt_donvi;

        public viewHolder(@NonNull View itemView) {

            super(itemView);
            container=(CardView) itemView.findViewById(R.id.container_row) ;
            hinh_sp=(ImageView)itemView.findViewById(R.id.hinh_sanpham_row);
            txt_ten=(TextView)itemView.findViewById(R.id.ten_sanpham_row) ;
            txt_gianhap=(TextView)itemView.findViewById(R.id.gianhapvao_row);
            txt_giaban=(TextView)itemView.findViewById(R.id.giaban_row);
            txt_soluong=(TextView)itemView.findViewById(R.id.soluong_row);
            txt_donvi=(TextView)itemView.findViewById(R.id.donvitinh_row);
            xoa_sp=(ImageView)itemView.findViewById(R.id.xoa_sp);



        }


    }

    public void setAfterFiltered(List<sanpham> FilterList){

        sanphamList=new ArrayList<>();
        sanphamList.addAll(FilterList);

        notifyDataSetChanged();


    }
}

