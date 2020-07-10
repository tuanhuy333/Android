package com.example.recyclerview_multipleviewtype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.example.recyclerview_multipleviewtype.Adapter.MultiViewType_Adapter;
import com.example.recyclerview_multipleviewtype.Models.FoodItem;
import com.example.recyclerview_multipleviewtype.Models.Footer;
import com.example.recyclerview_multipleviewtype.Models.Header;
import com.example.recyclerview_multipleviewtype.Models.RecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init RecyclerView
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //finally set adapter
        MultiViewType_Adapter adapter=new MultiViewType_Adapter(createList(),this);
        recyclerView.setAdapter(adapter);


    }
    private List<RecyclerViewItem> createList(){

        List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();
        Header header = new Header("Welcome To Food Express", "Non-Veg Menu",
                "https://cdn.pixabay.com/photo/2017/09/30/15/10/pizza-2802332_640.jpg");

        //add header
        recyclerViewItems.add(header);
        int [] imageUrls = {R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground};

        String[] titles = {"5 in 1 Chicken Zinger Box",
                "Paneer Butter Masala",
                "Chicken Lollipop Masala", "Paneer Manchurian", "Non-Veg. Lemon & Coriander Soup"};
        String[] descriptions = {"Chicken zinger+hot wings [2 pieces]+veg strip [1 piece]+Pillsbury cookie cake+Pepsi [can]",
                "A spicy North Indian dish made from cottage cheese, cream, butter and select spices",
                "Chicken wings coated with batter of flour",
                "Deep-fried cottage cheese balls sautéed with ginger", "Meat shreds, lime juice and coriander"};
        String[] price = {"₹220", "₹530", "₹400", "₹790", "₹150"};
        boolean[] isHot = {true, false, true, true, false};

        for (int i = 0; i < imageUrls.length; i++) {
            FoodItem foodItem = new FoodItem(titles[i], descriptions[i], imageUrls[i], price[i],isHot[i]);
            //add food items
            recyclerViewItems.add(foodItem);
        }

        Footer footer = new Footer("This is footer",
                "Bethenny Frankel", "https://cdn.pixabay.com/photo/2016/12/26/17/28/background-1932466_640.jpg");

        //add footer
        recyclerViewItems.add(footer);
        return recyclerViewItems;

    }
}