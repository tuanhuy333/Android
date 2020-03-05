package com.example.search_filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Adapter_Celeb adapter_celeb;
    List<Celebrity> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //data temp
        mData = new ArrayList<>();
        mData.add(new Celebrity("Tom Hardy", "England", R.drawable.ic_launcher_foreground));
        mData.add(new Celebrity("Tom Hardy rew", "England", R.drawable.ic_launcher_foreground));
        mData.add(new Celebrity("Albert Einstein", "Germany", R.drawable.ic_launcher_foreground));
        mData.add(new Celebrity("Bill Grate", "America", R.drawable.ic_launcher_foreground));
        mData.add(new Celebrity("Bill Grate 2", "America", R.drawable.ic_launcher_foreground));
        mData.add(new Celebrity("Bill Grate 3", "America", R.drawable.ic_launcher_foreground));
        mData.add(new Celebrity("Ho Chi Minh", "Viet Nam", R.drawable.ic_launcher_foreground));
        mData.add(new Celebrity("Mark Zuckerberg", "America", R.drawable.ic_launcher_foreground));
        mData.add(new Celebrity("Steve Job", "America", R.drawable.ic_launcher_foreground));
        mData.add(new Celebrity("Steve Job awa", "America", R.drawable.ic_launcher_foreground));


        mRecyclerView = findViewById(R.id.recyclerView);
        adapter_celeb = new Adapter_Celeb(this, R.layout.celeb_item, mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter_celeb);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu from xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_search, menu);

        MenuItem menuItem = menu.findItem(R.id.searchItem);
        SearchView searchView = (SearchView) menuItem.getActionView();


        //listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {




                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                adapter_celeb.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
