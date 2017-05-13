package com.example.abdullah.inventoryapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InventoryDbHelper db = new InventoryDbHelper(MainActivity.this);
        final ListView products = (ListView)findViewById(R.id.products);


        try {
            ArrayList<Product> prods = db.getAllData();
            InventoryAdapter Adp = new InventoryAdapter(MainActivity.this, prods);
            products.setAdapter(Adp);
        }
        catch (Exception e ) {
            Log.e(" In Main Activity " ,e.toString());
            products.setEmptyView(findViewById(R.id.emptyView));
        }

        Button addProduct = (Button)findViewById(R.id.addProduct);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (MainActivity.this,AddProductActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = (Product) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(MainActivity.this, ProductInfoActivity.class);
                intent.putExtra("MyClass",  product);
                startActivity(intent);
                MainActivity.this.finish();

            }
        });
    }
}
