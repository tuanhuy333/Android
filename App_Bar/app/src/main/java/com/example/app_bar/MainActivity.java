package com.example.app_bar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //phải noActionBar của app trước khi sử dụng toolbar


        Toolbar toolbar = findViewById(R.id.mToolBar);
        toolbar.setTitle("aaa2");
        toolbar.setLogo(R.drawable.ic_launcher_foreground);
        setSupportActionBar(toolbar);


    }
    //create menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        final CharSequence text_search = searchView.getQuery();

        final TextView txt=findViewById(R.id.text);



        //xu ly giá trị nhập vào searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                txt.setText(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}
