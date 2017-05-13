package com.example.abdullah.inventoryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Abdullah on 5/11/2017.
 */

public class InventoryAdapter extends ArrayAdapter<Product> {

    private ArrayList<Product> products = new ArrayList<>();
    private Context mContexts;

    public InventoryAdapter(Activity context, ArrayList<Product> products) {
        super(context, 0, products);
        mContexts = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewgroup) {

        View listItemView = view;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_product_item, viewgroup, false);
        }
        final Product product = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        nameTextView.setText("Product Name : "+product.getName());

        TextView priceTextView = (TextView) listItemView.findViewById(R.id.price);
        priceTextView.setText("Price "+product.getPrice()+"$");

        TextView quantityTextView = (TextView) listItemView.findViewById(R.id.quantity);
        quantityTextView.setText("Remaining Quantity : "+product.getQuantity());

        ImageView picImageView = (ImageView) listItemView.findViewById(R.id.image);
        Bitmap bmp = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        picImageView.setImageBitmap(bmp);

        Button saleButton = (Button) listItemView.findViewById(R.id.sale);
        saleButton.setOnClickListener(new View.OnClickListener() {
            InventoryDbHelper db = new InventoryDbHelper(getContext());
            Cursor cursor = db.getData(product.getId());
            @Override
            public void onClick(View v) {

                if (cursor.moveToFirst()){
                    int quantity = cursor.getInt(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_QUANTITY));
                    if (quantity > 0){
                        product.setQuantity(--quantity);
                        db.updateData(product);
                        Toast.makeText(getContext() , "The product sold ",Toast.LENGTH_LONG).show();
                       notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(getContext(), "There is now quantity avalible" , Toast.LENGTH_LONG);
                    }
                }


            }
        });

        return listItemView;
    }

}
