package android.pmr.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    // Helper Class that supplies app data

    // ---------> ATTRIBUTES & CONSTANS <---------
    private static final int DATABASE_VERSION = 1; // our db version
    private static final String DATABASE_NAME = "closet.db"; // our db name
    public static final String TABLE_CLOSET = "t_closet"; // main table name

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // ---------> DEVELOPMENT <---------
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CLOSET + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "shopName TEXT NOT NULL," +
                "description TEXT NOT NULL," +
                "price TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_CLOSET); // delete current database
        onCreate(db); // create again db with changes

    }
}
