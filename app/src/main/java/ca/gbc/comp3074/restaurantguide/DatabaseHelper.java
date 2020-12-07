package ca.gbc.comp3074.restaurantguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Restaurants.db";
    private static final String TABLE_NAME = "restaurants_table";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "address";
    private static final String DESCRIPTION = "description";
    private static final String RATING = "rating";
    private static final String TAGS = "tags";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, phone TEXT, address TEXT, rating TEXT, description TEXT, tags TEXT)");
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
        return res;
    }

    public String getPhone(String restName)
    {
        String res = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + PHONE + " FROM " + TABLE_NAME +
                " WHERE name = ?", new String[]{restName});

        if (cursor.moveToFirst())
        {
            res = cursor.getString(cursor.getColumnIndex("phone"));
        }
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
        return res;
    }

    public String getTags(String restName)
    {
        String res = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TAGS + " FROM " + TABLE_NAME +
                " WHERE name = ?", new String[]{restName});

        if (cursor.moveToFirst())
        {
            res = cursor.getString(cursor.getColumnIndex("tags"));
        }
        return res;
    }

    public boolean deleteRow(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME +
                " WHERE name = ?", new String[]{name});


        return true;
    }

    public boolean insertData(String name, String phone, String address, String rating,
                              String description, String tags)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PHONE, phone);
        contentValues.put(ADDRESS, address);
        contentValues.put(RATING, rating);
        contentValues.put(DESCRIPTION, description);
        contentValues.put(TAGS, tags);

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

    public boolean updateData(String name, String phone, String address, String rating,
                              String description, String tags, String restName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(!name.equals("") && name != null)
        {
            contentValues.put(NAME, name);
        }


        if(phone != null)
        {
            contentValues.put(PHONE, phone);
        }


        if(!address.equals("") && address != null)
        {
            contentValues.put(ADDRESS, address);
        }


        if(!rating.equals("") && rating != null)
        {
            contentValues.put(RATING, rating);
        }


        if(!description.equals("") && description != null)
        {
            contentValues.put(DESCRIPTION, description);
        }


        if(!tags.equals("") && tags != null)
        {
            contentValues.put(TAGS, tags);
        }


        long result = db.update(TABLE_NAME, contentValues, "name = ?",
                new String[]{restName});

        return result != -1;
    }
}
