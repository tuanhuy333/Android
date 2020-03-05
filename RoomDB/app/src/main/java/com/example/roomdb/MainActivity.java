package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.roomdb.Room.AppDatabase;
import com.example.roomdb.Room.Company;
import com.example.roomdb.Room.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    TextView textView;
    List<Employee> employeeList;

    List<String> companyName;
    List<String>companyName_tmp;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);


        appDatabase = AppDatabase.getInstance(getApplication()); //gọi singleton


        appDatabase.employDao().insertCompany(new Company(4, "Microsoft"));
        appDatabase.employDao().insertCompany(new Company(5, "Google"));
        appDatabase.employDao().insertCompany(new Company(6, "Apple"));
//
//        appDatabase.employDao().insertEmploy(new Employee(1, "Huy", 3));
//        appDatabase.employDao().insertEmploy(new Employee(2, "Quan", 1));
//        appDatabase.employDao().insertEmploy(new Employee(3, "Hau", 2));
//        appDatabase.employDao().insertEmploy(new Employee(4, "Thien", 2));
//        appDatabase.employDao().insertEmploy(new Employee(5, "Kiet", 3));

        employeeList = new ArrayList<>();
//      get all company
        List<Company> companyArrayList = new ArrayList<>();
        companyArrayList = appDatabase.employDao().getAllCompany();


        //............Test........
        //Show  UI
        companyName = new ArrayList<>();
        for (Company item : companyArrayList) {
            companyName.add(item.getTen_company());
        }

        //list tam thoi
        companyName_tmp=new ArrayList<>();
        companyName_tmp.addAll(companyName);


        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, companyName);

        listView.setAdapter(adapter);


    }


    //option menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mysearch, menu);


        //searchview
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                companyName.clear();

                if(newText.length() == 0){
                    companyName.addAll(companyName_tmp);
                }else {
                    for (String item:companyName_tmp) {
                        if (item.contains(newText)) {
                            companyName.add(item);
                        }
                    }
                }
                adapter.notifyDataSetChanged();//cap nhat du lieu khi co thay doi

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
