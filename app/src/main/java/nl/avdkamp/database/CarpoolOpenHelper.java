package nl.avdkamp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import nl.avdkamp.model.CarpoolEnum;

public class CarpoolOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String CARPOOL_TABLE_NAME = "carpool";

    public static final String DATUM_KOLOM_NAAM = "datum";

    public static final String SELECTIE_KOLOM_NAAM = "selectie";

    public static final String DATABASE_NAME = "CARPOOL_DATABASE";
    private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + CARPOOL_TABLE_NAME + " (" +
                        DATUM_KOLOM_NAAM + " INTEGER, " +
                        SELECTIE_KOLOM_NAAM + " TEXT);";

    public CarpoolOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    public CarpoolEnum getCarpoolEntity(Long date){

        SQLiteDatabase db = getReadableDatabase();

        // Which row to update, based on the ID
        String selection = DATUM_KOLOM_NAAM + " = ?";
        String[] selectionArgs = { String.valueOf(date) };
        Cursor c = db.query(
                CARPOOL_TABLE_NAME,  // The table to query
                null,                              // The columns to return
                selection,                               // The columns for the WHERE clause
                selectionArgs,                           // The values for the WHERE clause
                null,                                    // don't group the rows
                null,                                    // don't filter by row groups
                null                                     // The sort order
        );
        if(c.getCount() > 0) {
            c.moveToFirst();
            String carpoolEnum = c.getString(c.getColumnIndexOrThrow(SELECTIE_KOLOM_NAAM));
            if (carpoolEnum != null)
                return CarpoolEnum.valueOf(carpoolEnum);
        }
        return null;

    }

    public void saveOrUpdate(long date, CarpoolEnum carpoolEnum) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DATUM_KOLOM_NAAM, date);
        values.put(SELECTIE_KOLOM_NAAM, carpoolEnum.name());

        // Insert the new row
        db.insert(CARPOOL_TABLE_NAME,DATUM_KOLOM_NAAM, values);
    }
}