package com.example.quick_note.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quick_note.MainActivity;
import com.example.quick_note.Model.Tag;
import com.example.quick_note.Model.Todo;
import com.example.quick_note.R;
import com.example.quick_note.TodoActivity;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adapter_tag extends RecyclerView.Adapter {
    Context context;
    int layout;
    List<Tag> list_tag;

    public Adapter_tag(Context context, int layout, List<Tag> list_tag) {
        this.context = context;
        this.layout = layout;
        this.list_tag = list_tag;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView txt_tenTag,txt_soluongtodo;
        ImageView img_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tenTag = itemView.findViewById(R.id.ten_tag);
            txt_soluongtodo=itemView.findViewById(R.id.soluong_todo);
            cardView=itemView.findViewById(R.id.card_view);
            img_delete=itemView.findViewById(R.id.tag_img_delete);
        }


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //troi buoc noi dung cua tung thanh phan trong recycle view
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final Tag tag = list_tag.get(i);


        //ép kiểu và gán giá trị
        ((ViewHolder) viewHolder).txt_tenTag.setText(tag.getTagName());

        //gán số lượng note theo từng tag
        final int tag_id=tag.getId();
        ((ViewHolder) viewHolder).txt_soluongtodo.setText(MainActivity.db.get_countTodo(tag_id));

        //hien thi sang activity voi cong viec tuong ung voi tag
        ((ViewHolder)viewHolder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //tao Intent de gui List<todo>
                Intent intent = new Intent(context, TodoActivity.class);


                intent.putExtra("tag_id",tag.getId());

                context.startActivity(intent);

            }
        });

        //xóa
        ((ViewHolder)viewHolder).img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Snackbar snackbar=Snackbar.make(v,"Bạn muốn xóa danh sách "+tag.getTagName()+" hả ?",Snackbar.LENGTH_LONG)
                                            .setAction("Ừ !", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    MainActivity.db.deleteTag(tag,true);

                                                    list_tag.remove(i); //đồng thời cũng xóa phần tử đó trong list theo position, để cập nhật giá trị trong list
                                                    notifyDataSetChanged();//cập nhật lại mảng
                                                    Toast.makeText(context, "Tui xóa cho bạn rồi đó", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                snackbar.show();


            }
        });

        //edit tag_name
        ((ViewHolder)viewHolder).txt_tenTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //ko hiển thị đường viền trên
                dialog.setContentView(R.layout.dialog_add_edit_list);
                dialog.show();

                final EditText edt_addList=(dialog).findViewById(R.id.dialog_edtListname);
                Button btn_ok=(dialog).findViewById(R.id.dialog_btnOK);


                edt_addList.setText(tag.getTagName()); //set Text for dialog List's name

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String old_name=tag.getTagName();
                        String new_name=edt_addList.getText().toString().trim();

                        Tag tag1=new Tag(tag.getId(),new_name);
                        if(new_name.isEmpty()){

                            Toast.makeText(context, "Tên muốn thay đổi rỗng bạn ơi !", Toast.LENGTH_SHORT).show();

                        }else{
                            try{


                                if(old_name.equals(new_name)){
                                    Toast.makeText(context, "Tên vẫn được giữ nguyên nhe bạn", Toast.LENGTH_LONG).show();
                                }else {


                                    MainActivity.db.updateTag(tag1);

                                    //cap nhat phan tu trong list

                                    list_tag.set(i,tag1);
                                    notifyDataSetChanged();//cập nhật lại mảng

                                    Toast.makeText(context, "Tui sửa tên "+old_name+" thành "+new_name +" rồi đó", Toast.LENGTH_LONG).show();

                                }



                                dialog.dismiss(); //đóng dialog


                            }catch (Exception e){
                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        }

                });


            }
        });


    }

    @Override
    public int getItemCount() {
        return list_tag.size();
    }
}
