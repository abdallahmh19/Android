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

    InventoryDbHelper db ;
     ListView products ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            db = new InventoryDbHelper(MainActivity.this);
            products = (ListView)findViewById(R.id.products);
            products.setEmptyView(findViewById(R.id.emptyView));
            ArrayList<Product> prods = db.getAllData();
            InventoryAdapter Adp = new InventoryAdapter(MainActivity.this, prods);
            products.setAdapter(Adp);




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

                //MainActivity.this.finish();
                Intent intent = new Intent(MainActivity.this, ProductInfoActivity.class);
                intent.putExtra("MyClass",  product);
                startActivity(intent);


            }
        });
    }

}
