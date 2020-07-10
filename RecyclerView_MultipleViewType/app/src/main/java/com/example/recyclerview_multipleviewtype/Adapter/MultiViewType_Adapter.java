package com.example.recyclerview_multipleviewtype.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview_multipleviewtype.Models.FoodItem;
import com.example.recyclerview_multipleviewtype.Models.Footer;
import com.example.recyclerview_multipleviewtype.Models.Header;
import com.example.recyclerview_multipleviewtype.Models.RecyclerViewItem;
import com.example.recyclerview_multipleviewtype.R;

import java.util.List;

public class MultiViewType_Adapter extends RecyclerView.Adapter {

    //Declare List of Recyclerview Items
    List<RecyclerViewItem> recyclerViewItems;

    //Header Item Type
    private static final int HEADER_ITEM = 0;

    //Footer Item Type
    private static final int FOOTER_ITEM = 1;

    ////Food Item Type
    private static final int FOOD_ITEM = 2;
    Context mContext;


    // Contructor
    public MultiViewType_Adapter(List<RecyclerViewItem> recyclerViewItems, Context mContext) {
        this.recyclerViewItems = recyclerViewItems;
        this.mContext = mContext;
    }

    //                 2)
    // create ViewHolder ứng với số type view (headerViewHolder,footerViewHolder,..)

    // headerViewHolder
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        //
        private TextView txt;
        // ....

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_header);
        }
    }

    // footerViewHolder
    public class FooterViewHolder extends RecyclerView.ViewHolder {

        //
        private TextView txt;
        // ....

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_footer);
        }
    }

    // foodItem ViewHolder
    public class FoodItemViewHolder extends RecyclerView.ViewHolder {
        //
        private TextView txt_tenmonan, txt_giamonan;
        private ImageView img_monan;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tenmonan = itemView.findViewById(R.id.textView);
            txt_giamonan = itemView.findViewById(R.id.textView2);
            img_monan = itemView.findViewById(R.id.imageView);
        }
    }


    //                 3)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Component LayoutInflater chuyển xml sang java
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row;

        // check nếu type nào thì tạo ViewHolder đó
        if (viewType == HEADER_ITEM) {
            row = inflater.inflate(R.layout.custom_header, parent, false); //ko attach ngay
            return new HeaderViewHolder(row);
        } else if (viewType == FOOTER_ITEM) {
            row = inflater.inflate(R.layout.custom_footer, parent, false);
            return new FooterViewHolder(row);
        } else if (viewType == FOOD_ITEM) {
            row = inflater.inflate(R.layout.custom_food_item, parent, false);
            return new FoodItemViewHolder(row);

        }

        return null;
    }

    //            4)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);

        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            Header header = (Header) recyclerViewItem;

            //
            headerViewHolder.txt.setText(header.getHeaderText());
            // ...
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            Footer footer = (Footer) recyclerViewItem;

            //
            footerViewHolder.txt.setText(footer.getQuote());
        } else if (holder instanceof FoodItemViewHolder) {
            // ep sang kieu FooditemViewHolder cho holder
            FoodItemViewHolder foodItemViewHolder = (FoodItemViewHolder) holder;
            FoodItem foodItem = (FoodItem) recyclerViewItem;

            //
            foodItemViewHolder.txt_tenmonan.setText(foodItem.getTitle());
            foodItemViewHolder.txt_giamonan.setText(foodItem.getPrice());
            foodItemViewHolder.img_monan.setImageResource(foodItem.getImageUrl());
        }
    }

    //               1)
    @Override
    public int getItemViewType(int position) {

        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);

        if (recyclerViewItem instanceof Header) { // neu doi tuong trong list la Header
            return HEADER_ITEM;
        } else if (recyclerViewItem instanceof Footer) {
            return FOOTER_ITEM;
        } else if (recyclerViewItem instanceof FoodItem) {
            return FOOD_ITEM;
        } else {
            return super.getItemViewType(position);
        }


    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }
}
