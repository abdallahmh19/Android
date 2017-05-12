package com.example.abdullah.inventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductInfoActivity extends AppCompatActivity {

    Product product ;
    EditText name,price ,quantity;
    Button save ,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        final InventoryDbHelper db = new InventoryDbHelper(this);
        product = (Product) getIntent().getSerializableExtra("MyClass");
        name = (EditText)findViewById(R.id.name);
        name.setText(product.getName());

        price = (EditText)findViewById(R.id.price);
        price.setText(String.valueOf(product.getPrice()));

        quantity = (EditText)findViewById(R.id.quantity);
        quantity.setText(String.valueOf(product.getQuantity()));

        delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.deleteData(product.getId())) {
                    Intent intent = new Intent(ProductInfoActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(ProductInfoActivity.this, "The product has been deleted!", Toast.LENGTH_SHORT).show();
                }else
                Toast.makeText(ProductInfoActivity.this, " deletion failed ", Toast.LENGTH_SHORT).show();
            }
        });
        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setName(name.getText().toString());
                product.setPrice(Integer.parseInt(price.getText().toString()));
                product.setQuantity(Integer.parseInt(quantity.getText().toString()));
                db.updateData(product );
                    Intent intent = new Intent(ProductInfoActivity.this, MainActivity.class);
                    Toast.makeText(ProductInfoActivity.this, "The Product Info has been Updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

            }
        });

    }



}
