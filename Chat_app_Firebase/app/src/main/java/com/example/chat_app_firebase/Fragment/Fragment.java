package com.example.chat_app_firebase.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_app_firebase.Activity.DangKyActivity;
import com.example.chat_app_firebase.Adapter.Adapter_User;
import com.example.chat_app_firebase.DangNhapActivity;
import com.example.chat_app_firebase.Model.Chat;
import com.example.chat_app_firebase.Model.User;
import com.example.chat_app_firebase.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class Fragment extends androidx.fragment.app.Fragment {

    RecyclerView recyclerView,recyclerView_chat;
    List<User>list;
    Adapter_User adapter_user;

    EditText edt_username;
    Button btn_update;
    ImageView img_chupanh,img_profile;


    HashSet<String> listReceiver;

    //khai bao Firebase
    FirebaseDatabase db_firebase;
    DatabaseReference reference;



    //tao instance giong nhu contructor
    public static Fragment newInstance(String data){
        Fragment fragment=new Fragment();

        Bundle args=new Bundle();
        args.putString("data",data);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;



        Bundle bundle=getArguments();
        String txt=bundle.getString("data");
        
        if(txt.equals("fragment_users")){
            view=inflater.inflate(R.layout.fragment_users,container,false);

           getDataFirebase(view);

        }
//        else if(txt.equals("fragment_chats")){
//            view=inflater.inflate(R.layout.fragment_chats,container,false);
//            getUserisChat(view);
//        }
        else if(txt.equals("fragment_profile")) {
            view=inflater.inflate(R.layout.fragment_profile,container,false);

            img_chupanh=view.findViewById(R.id.imageView_chupanh);
            img_profile=view.findViewById(R.id.imageView_profile);
            edt_username=view.findViewById(R.id.editText_profile);

            load_data(view);


            getProfile(view);

        }

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 222) {
            if (resultCode == RESULT_OK) {
                final Bitmap bp = (Bitmap) data.getExtras().get("data");
                img_chupanh.post(new Runnable() {
                    @Override
                    public void run() {
                        img_chupanh.setImageBitmap(bp);
                    }
                });


            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void load_data(final View v){

        db_firebase= FirebaseDatabase.getInstance();
        reference=db_firebase.getReference();

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        final String Uid=user.getUid();

        img_chupanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,222);
            }
        });


        //read database Firebase
        final ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<>();

                for(DataSnapshot data:dataSnapshot.getChildren()){

                    User u=data.getValue(User.class);
                    if(u.getKey_id().equals(Uid)){

                        list.add(u);


                    }

                }
                edt_username.setText(list.get(0).getUsername());
                //do picasso xung dot khi chup anh se khong hien thi len ImageView neu co picasso
                //nen phai su dung 2 imageview nhu lop mat na ;))
                Picasso.get().load(list.get(0).getImage_url()).into(img_profile);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        reference.child("Users").addValueEventListener(valueEventListener);
    }







    private void getProfile(View v){

        db_firebase= FirebaseDatabase.getInstance();
        reference=db_firebase.getReference();




        btn_update=v.findViewById(R.id.button2_profile);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage_and_updateUsers(edt_username.getText().toString());

            }
        });


    }

    private void uploadImage_and_updateUsers(final String newusername){

        //=================upload
        // Create a storage reference from our app
        FirebaseStorage storage;
        storage=FirebaseStorage.getInstance("gs://chatapp-741e6.appspot.com"); //storage luu anh va file
        StorageReference storageRef = storage.getReference();
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        final String Uid=user.getUid();

        final StorageReference ref = storageRef.child("avatar_user/img_"+newusername+".png");

        // Get the data from an ImageView as bytes
        img_chupanh.setDrawingCacheEnabled(true);
        img_chupanh.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) img_chupanh.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        final byte[] data = baos.toByteArray();

        final UploadTask uploadTask = ref.putBytes(data);


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
                    final Uri uri_download=task.getResult();

                    updateUser(Uid,edt_username.getText().toString(),uri_download.toString());
                    Toast.makeText(getContext(), "Update successed", Toast.LENGTH_SHORT).show();

                } else {
                    // Handle failures
                    // ...
                }
            }
        });



    }
    private void updateUser(String key, String newusername, String url_image) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously

        Map<String,Object>map=new HashMap<>();
        map.put("key_id",key);
        map.put("username",newusername);
        map.put("image_url",url_image);
        reference.child("Users").child(key).updateChildren(map);
    }
    private void getUserisChat(final View v) {
        db_firebase = FirebaseDatabase.getInstance();
        reference = db_firebase.getReference("Chats");

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String Uid = user.getUid();

        recyclerView_chat = v.findViewById(R.id.recyclerView_Chats);


        list = new ArrayList<>();
        listReceiver=new HashSet<>();


        //read database Firebase
        reference.child("Who_receiver").child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Chat c = data.getValue(Chat.class);
                    if(c.getReceiver().equals(Uid) ==false){
                        listReceiver.add(c.getReceiver());
                    }



                }

                Toast.makeText(getContext(), listReceiver.toString(), Toast.LENGTH_LONG).show();
                //
               chatUser(v,listReceiver);


                //
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void chatUser(View v, final HashSet<String> listReceiver){
        db_firebase = FirebaseDatabase.getInstance();
        reference = db_firebase.getReference("Users");

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String Uid = user.getUid();

        recyclerView_chat = v.findViewById(R.id.recyclerView_Chats);


        list = new ArrayList<>();

        final HashSet<User>userHashSet=new HashSet<>();


        //read database Firebase
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    User u = data.getValue(User.class);

                    if(!u.getKey_id().equals(Uid)){

                        for (String id_receiver:listReceiver){
                            if(!id_receiver.equals(u.getKey_id()) ){
                                list.add(u);
                            }
                        }
                    }




                }


                //

                adapter_user=new Adapter_User(getContext(),R.layout.user_item,list);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());

                recyclerView_chat.setLayoutManager(linearLayoutManager);
                recyclerView_chat.setAdapter(adapter_user);

                //
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getDataFirebase(View v){
        db_firebase=FirebaseDatabase.getInstance();
        reference=db_firebase.getReference();

        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        final String Uid=user.getUid();

        recyclerView=v.findViewById(R.id.recyclerView);


        list=new ArrayList<>();

        //read database Firebase
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot data:dataSnapshot.getChildren()){

                    User u=data.getValue(User.class);
                    if(u.getKey_id().equals(Uid) == false){
                        list.add(u);
                    }



                }

                //setAdapter for RecyclerView
                adapter_user=new Adapter_User(getContext(),R.layout.user_item,list);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter_user);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        reference.child("Users").addValueEventListener(valueEventListener);
    }



}
