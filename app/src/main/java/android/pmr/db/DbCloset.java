package android.pmr.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.pmr.entities.Clothes;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.sql.Blob;
import java.util.ArrayList;

public class DbCloset extends DbHelper{

    // ---------> ATTRIBUTES & CONSTANS <---------
    private Context context;
    private String img;
    private EditText txtClothesName, txtShopName;
    private Button btnSave;

    // ---------> DEVELOPMENT <---------
    public DbCloset(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertClothing(String shopName, String desc, String price){
        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("shopName", shopName);
            contentValues.put("description", desc);
            contentValues.put("price", price);

           id = db.insert(TABLE_CLOSET, null, contentValues);
        } catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Clothes> showClothes() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clothes> listClothes = new ArrayList<>();
        Clothes clothes = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_CLOSET, null);

        if(cursor.moveToFirst()) {
            do{
                clothes = new Clothes();
                clothes.setId(cursor.getString(0));
                clothes.setShopName(cursor.getString(1));
                clothes.setDescription(cursor.getString(2));
                clothes.setPrice(cursor.getString(3));
                listClothes.add(clothes);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return  listClothes;
    }

    public Clothes show(int id) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Clothes clothes = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_CLOSET + " WHERE id = " + id + " LIMIT 1", null);

        if(cursor.moveToFirst()) {
            clothes = new Clothes();
            clothes.setId(cursor.getString(0));
            clothes.setShopName(cursor.getString(1));
            clothes.setDescription(cursor.getString(2));
            clothes.setPrice(cursor.getString(3));
        }

        cursor.close();
        return  clothes;
    }
}
