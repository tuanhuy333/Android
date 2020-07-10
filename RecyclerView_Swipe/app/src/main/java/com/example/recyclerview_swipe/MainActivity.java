package com.example.recyclerview_swipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter_user.onItemClickListener,RecyclerItemTouchHelper.RecyclerItemTouchHelperListener  {
    List<User> mData;
    Adapter_user adapter_user;
    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //anh xa
        RecyclerView recyclerView = findViewById(R.id.recycle);
        coordinatorLayout=findViewById(R.id.coordinator_layout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mData = User.mData(100);
        adapter_user = new Adapter_user(mData);

        //horizontal recyclerview
        //        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), RecyclerView.HORIZONTAL, false));
        //vertical recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(adapter_user);



        adapter_user.setOnClickListener(this);

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0,
                ItemTouchHelper.LEFT,this); // listener
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


    }

    @Override
    public void onClick(View v, int position) {
        Toast.makeText(getApplicationContext(), mData.get(position).getEmail(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof Adapter_user.mViewHolder) {
            // get the removed item name to display it in snack bar
            String name = mData.get(viewHolder.getAdapterPosition()).getEmail();

            // backup of removed item for undo purpose
            final User deletedItem = mData.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            adapter_user.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter_user.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
