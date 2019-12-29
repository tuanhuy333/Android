package com.example.chat_app_firebase.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chat_app_firebase.Adapter.Adapter_User;
import com.example.chat_app_firebase.DangNhapActivity;
import com.example.chat_app_firebase.Model.User;
import com.example.chat_app_firebase.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DangKyActivity extends AppCompatActivity {
    EditText edt_username,edt_password,edt_email,edt_phone;
    Button btn_dangky;
    ImageView img_camera;
    private final int REQUEST_CODE_CAMERA=111;

    //FireBase khai báo
    FirebaseAuth mAuth;

    FirebaseDatabase db_firebase;
    DatabaseReference reference;

    FirebaseStorage storage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        initView();

        //khoi tao Firebase
        mAuth=FirebaseAuth.getInstance(); //authencation
        db_firebase= FirebaseDatabase.getInstance(); //database realtime
        reference=db_firebase.getReference();
        storage=FirebaseStorage.getInstance("gs://chatapp-741e6.appspot.com"); //storage luu anh va file

        //them anh
        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });

        //click register
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=edt_email.getText().toString().trim();
                String password=edt_password.getText().toString().trim();
                String username=edt_username.getText().toString().trim();

                if(!email.isEmpty() && !password.isEmpty() && !username.isEmpty()){
                    dangky(email,password,username);
                }
                else {
                    Toast.makeText(DangKyActivity.this, "Fill out , please !", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    private void uploadImage_and_writeUsers(final String username){

        //=================upload
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

        final StorageReference ref = storageRef.child("avatar_user/img_"+username+".png");

        // Get the data from an ImageView as bytes
        img_camera.setDrawingCacheEnabled(true);
        img_camera.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) img_camera.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = ref.putBytes(data);
        //====================== upload and get Url image
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {

                    //url image trên Storage
                    Uri uri_download=task.getResult();

                    // dang ky Authen thanh cong thi luu key_id vao Users trong database
                    FirebaseUser user = mAuth.getCurrentUser();
                    reference=FirebaseDatabase.getInstance().getReference();
                    String key_id=user.getUid();


                    User u=new User(key_id,username,uri_download.toString(),"offline");

                    reference.child("Users").child(key_id).setValue(u);

                    Toast.makeText(DangKyActivity.this,"Register is successed", Toast.LENGTH_SHORT).show();

                    //chuyen qua dang nhap luon
                    startActivity(new Intent(DangKyActivity.this, DangNhapActivity.class));

                } else {
                    // Handle failures
                    // ...
                }
            }
        });






    }



    private void initView() {
        edt_username=(EditText)findViewById(R.id.editText_dk);
        edt_password=(EditText)findViewById(R.id.editText2_dk);
        edt_email=(EditText)findViewById(R.id.editText3_dk);


        btn_dangky=(Button)findViewById(R.id.button2_dk);

        img_camera=(ImageView)findViewById(R.id.imageView);
    }


    public void dangky(String email, String password, final String username){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //if dang ky Authen thanh cong thi upload hinh va write database Users
                            uploadImage_and_writeUsers(username);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(DangKyActivity.this, task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
    //display anh imageView tu camera


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode== RESULT_OK && data!=null){
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            img_camera.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
