package com.example.abdullah.inventoryapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Abdullah on 5/11/2017.
 */

public class InventoryContract {

    public static final class ProductEntry implements BaseColumns {

        public static final String TABLE_NAME = "product";

        public static final String COLUMN_Id = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE = "image_uri";
        public static final String COLUMN_QUANTITY = "quantity";

    }
}

