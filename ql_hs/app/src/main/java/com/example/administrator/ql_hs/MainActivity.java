package com.example.administrator.ql_hs;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    database_hs database_hs;
    ListView ls;

    ArrayList<hocsinh> arr_hs;
    adapter_hs adapter;

    TextView txt_hienthi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hienthi_data();
    }

    public void hienthi_data(){
        //khoi tao
        database_hs=new database_hs(this,"DB_hs.sqlite",null,1);

        //tao bang
        database_hs.query_Data("create table if not exists hs(id integer primary key autoincrement,ten nvarchar(100),lop nvarchar(50),diem float)");
       // database_hs.query_Data("insert into hs(id,ten,lop,diem) values(null,'hoa','9a5',6)");
        Cursor data=database_hs.get_Data("select * from hs");
        arr_hs=new ArrayList<>();
        while(data.moveToNext()){

            int idhs=data.getInt(0);
            String tenhs=data.getString(1);
            String lophs=data.getString(2);
            Float diemhs=data.getFloat(3);

            arr_hs.add(new hocsinh(idhs,tenhs,lophs,diemhs));


        }

        adapter=new adapter_hs(this,R.layout.row_hs,arr_hs);
        ls=(ListView)findViewById(R.id.LV_main);
        ls.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt_hienthi=(TextView)findViewById(R.id.txt_hienthi);

                int idObject_selected=arr_hs.get(position).getId();



                dialog_thongtin(position);

            }
        });

    }

    public void dialog_thongtin(final int i){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thongtin);
         final EditText edt_hienthiten=(EditText)(dialog).findViewById(R.id.edt_hienthiten);
        final EditText edt_hienthilop=(EditText)(dialog).findViewById(R.id.edt_hienthilop);
        final EditText edt_hienthidiem=(EditText)(dialog).findViewById(R.id.edt_hienthidiem);

        edt_hienthiten.setText(arr_hs.get(i).getTen());
        edt_hienthilop.setText(arr_hs.get(i).getLop());
        edt_hienthidiem.setText(arr_hs.get(i).getDiem()+"");

        Button btn_sua=(Button)dialog.findViewById(R.id.btn_sua);

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi=edt_hienthiten.getText().toString().trim();
                String lopmoi=edt_hienthilop.getText().toString().trim();
                Float diemmoi=Float.parseFloat(edt_hienthidiem.getText().toString().trim());


                String sql=String.format("update hs set ten='%s',lop='%s',diem=%f where id=%d",tenmoi,lopmoi,diemmoi,arr_hs.get(i).getId());





                database_hs.query_Data(sql);

                dialog.dismiss();
                hienthi_data();
            }
        });


        //btn huy
        Button btn_huy=(Button)dialog.findViewById(R.id.btn_huy);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tat dialog hien tai

                dialog.dismiss();

            }
        });

        dialog.show();

    }
    public void xoa(int id){

        String sql=String.format("delete from hs where id=%d",id);

        database_hs.query_Data(sql);

        hienthi_data();
    }

}
