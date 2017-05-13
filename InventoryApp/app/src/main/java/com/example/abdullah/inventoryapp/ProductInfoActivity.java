package com.example.abdullah.inventoryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductInfoActivity extends AppCompatActivity {

    Product product ;
    EditText name,price ,quantity;
    Button save ,delete , orderMore;

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
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                if (db.deleteData(product.getId())) {
                                    Intent returnHome = new Intent(ProductInfoActivity.this, MainActivity.class);
                                    startActivity(returnHome);
                                    Toast.makeText(ProductInfoActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder alert = new AlertDialog.Builder(ProductInfoActivity.this);
                alert.setMessage("Are you sure , you want to delete ? ").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setName(name.getText().toString());
                product.setPrice(Integer.parseInt(price.getText().toString()));
                product.setQuantity(Integer.parseInt(quantity.getText().toString()));
                db.updateData(product);
                    Intent intent = new Intent(ProductInfoActivity.this, MainActivity.class);
                    Toast.makeText(ProductInfoActivity.this, "The Product Info has been Updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    ProductInfoActivity.this.finish();

            }
        });
        orderMore = (Button) findViewById(R.id.orderMore);
        orderMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:" + "Abdallahmh19@gmail.com")
                        .buildUpon()
                        .appendQueryParameter("subject", "Request" )
                        .appendQueryParameter("body", "I need this product "+ product.getName())
                        .build();

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(Intent.createChooser(emailIntent, "Request"));
            }
        });
    }
}
