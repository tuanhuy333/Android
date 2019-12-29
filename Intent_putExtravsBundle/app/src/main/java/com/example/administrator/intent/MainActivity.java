package com.example.administrator.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_chuoi,btn_so,btn_mangchuoi,btn_doituong,btn_sendbundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();

        //PUTEXTRA()

        //click btn_truyenchuoi
//        btn_chuoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Activity2.class);
//                intent.putExtra("ma_sp","JEAN123");
//                startActivity(intent);
//
//            }
//        });
        //click btn_truyenso
//        btn_so.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Activity2.class);
//               intent.putExtra("so_trangsach",400);
//               startActivity(intent);
//            }
//        });
        //click btn_mangchuoi
//        btn_mangchuoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Activity2.class);
//                //khoi tao mamng
//                String[] mangLoaiao={"kaki","somi","sweater","hoodie","thun"};
//                intent.putExtra("cacloaiao",mangLoaiao);
//                startActivity(intent);
//            }
//        });
        //click btn_doituong
//        btn_doituong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(MainActivity.this,Activity2.class);//khai toa intent
//
//                hocsinh hs=new hocsinh(123,"Tuấn Huy",7.5);//tao hoc sinh
//
//                intent.putExtra("thongtin_hs",hs); //gan doi tuong vao intent
//
//                startActivity(intent); //bat dau intent
//
//
//            }
//        });

        //BUNDLE
        btn_sendbundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Activity2.class);
                Bundle bundle=new Bundle();//bundle để bó các dữ liệu bao gồm số,chuỗi ,mảng,đối tượng lại
                                            //"và được đặt vào intent qua putExtra("dulieu",bundle)
                bundle.putString("tensp","Áo off white");//bundle chuoi
                bundle.putInt("sotrangsach",300);//bundle so

                String [] mangloaiquan={"short","kaki","jogger","jean"};
                bundle.putStringArray("cacloaiquan",mangloaiquan);//bundle mang chuoi

                //bundle doi tuong
                hocsinh hs=new hocsinh(13,"Thanh",8.8);
                bundle.putSerializable("thongtin_hs",hs);

                intent.putExtra("dulieu",bundle);//đặt vào intent qua putExtra()
                startActivity(intent);//bat dau chay intent
            }
        });

    }
    public void anhxa(){
        btn_chuoi=(Button)findViewById(R.id.btn_truyenChuoi);
        btn_so=(Button)findViewById(R.id.btn_truyenSo);
        btn_mangchuoi=(Button)findViewById(R.id.btn_truyenMangChuoi);
        btn_doituong=(Button)findViewById(R.id.btn_truyenDoituong);

        //bundle
        btn_sendbundle=(Button)findViewById(R.id.btn_sendbundle);
    }
}
