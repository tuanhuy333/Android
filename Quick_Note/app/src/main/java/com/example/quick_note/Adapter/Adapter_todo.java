package com.example.quick_note.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import java.util.List;

public class Adapter_todo extends RecyclerView.Adapter {
    Context context;
    int layout;
    List<Todo> list_todo;

    public Adapter_todo(Context context, int layout, List<Todo> list_todo) {
        this.context = context;
        this.layout = layout;
        this.list_todo = list_todo;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView txt_tenTodo,date_created;
        CheckBox checkBox_todo;
        CardView todo_container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tenTodo = itemView.findViewById(R.id.txt_tentodo);
            checkBox_todo=itemView.findViewById(R.id.checkbox_todo);
            date_created=itemView.findViewById(R.id.date_created);
            todo_container=itemView.findViewById(R.id.todo_container);

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
        final Todo todo= list_todo.get(i);


        //ép kiểu và gán giá trị
        ((ViewHolder) viewHolder).txt_tenTodo.setText(todo.getNote());
        ((ViewHolder) viewHolder).date_created.setText(todo.getCreateAt());

        //xóa todo
        ((ViewHolder)viewHolder).checkBox_todo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.db.deleteToDo_TodoTag(todo.getId());
                list_todo.remove(i);
                notifyDataSetChanged();
                Toast.makeText(context, "Tui xóa cho bạn rồi đó", Toast.LENGTH_SHORT).show();

            }
        });

        //edit todo
        ((ViewHolder)viewHolder).todo_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //ko hiển thị đường viền trên
                dialog.setContentView(R.layout.dialog_add_edit_todo);
                dialog.show();

                final EditText edt_addTodo=(dialog).findViewById(R.id.dialog_edtTodoname);
                Button btn_ok=(dialog).findViewById(R.id.dialog_btnOK);


                edt_addTodo.setText(todo.getNote());

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String old_name=todo.getNote();
                        String new_name=edt_addTodo.getText().toString().trim();

                        //truyền tham số đủ theo hàm updateTodo
                        Todo todo1=new Todo(todo.getId(),new_name,0,MainActivity.db.getDateTime());

                        if(new_name.isEmpty()){

                            Toast.makeText(context, "Tên muốn thay đổi rỗng kìa bạn ơi !", Toast.LENGTH_SHORT).show();

                        }else{
                            try{


                                if(old_name.equals(new_name)){
                                    Toast.makeText(context, "Tên vẫn được giữ nguyên nhe bạn", Toast.LENGTH_LONG).show();
                                }else {


                                    MainActivity.db.updateToDo(todo1);


                                    //cap nhat phan tu trong list

                                    list_todo.set(i,todo1);
                                    notifyDataSetChanged();//cập nhật lại mảng

                                    Toast.makeText(context, "Tui sửa tên "+old_name+" thành "+new_name+ " rồi đó", Toast.LENGTH_LONG).show();

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
        return list_todo.size();
    }


}
