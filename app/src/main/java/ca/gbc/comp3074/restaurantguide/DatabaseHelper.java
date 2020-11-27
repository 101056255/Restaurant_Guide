package ca.gbc.comp3074.restaurantguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Restaurants.db";
    private static final String TABLE_NAME = "restaurants_table";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String DESCRIPTION = "description";
    private static final String RATING = "rating";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, address TEXT, rating TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/Restaurants.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String getName(String restName)
    {
        String res = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + NAME + " FROM " + TABLE_NAME +
                        " WHERE name = ?", new String[]{restName});

        if (cursor.moveToFirst())
        {
            res = cursor.getString(cursor.getColumnIndex("name"));
        }
        db.close();
        return res;
    }

    public String getAddress(String restName)
    {
        String res = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + ADDRESS + " FROM " + TABLE_NAME +
                        " WHERE name = ?", new String[]{restName});

        if (cursor.moveToFirst())
        {
            res = cursor.getString(cursor.getColumnIndex("address"));
        }
        db.close();
        return res;
    }

    public String getRating(String restName)
    {
        String res = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + RATING + " FROM " + TABLE_NAME +
                " WHERE name = ?", new String[]{restName});

        if (cursor.moveToFirst())
        {
            res = cursor.getString(cursor.getColumnIndex("rating"));
        }
        db.close();
        return res;
    }

    public String getDescription(String restName)
    {
        String res = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + DESCRIPTION + " FROM " + TABLE_NAME +
                " WHERE name = ?", new String[]{restName});

        if (cursor.moveToFirst())
        {
            res = cursor.getString(cursor.getColumnIndex("description"));
        }
        db.close();
        return res;
    }

    public boolean insertData(String name, String address, String rating, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(ADDRESS, address);
        contentValues.put(RATING, rating);
        contentValues.put(DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewData(){
        SQLiteDatabase db= this.getReadableDatabase();
        String query= "SELECT name FROM "+ TABLE_NAME;
        Cursor cursor= db.rawQuery(query,null);
        return cursor;

    }
}
