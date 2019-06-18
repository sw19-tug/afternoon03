package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "test_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StoredTest.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StoredTest.TABLE_NAME);
        onCreate(db);
    }

    public long insert(int size, int correct, int time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        java.util.Date c =Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        String formattedDate = df.format(c);
        values.put(StoredTest.COLUMN_DATE, formattedDate);
        values.put(StoredTest.COLUMN_SIZE, size);
        values.put(StoredTest.COLUMN_CORRECT, correct);
        values.put(StoredTest.COLUMN_TIME, time);

        long id = db.insert(StoredTest.TABLE_NAME, null, values);
        db.close();
        return id;
    }


    public List<StoredTest> getAllNotes() {
        List<StoredTest> tests = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + StoredTest.TABLE_NAME + " ORDER BY " +
                StoredTest.COLUMN_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                StoredTest test = new StoredTest();
                test.setId(cursor.getInt(cursor.getColumnIndex(StoredTest.COLUMN_ID)));
                String dateString = cursor.getString(cursor.getColumnIndex(StoredTest.COLUMN_DATE));
                test.setDate(dateString);
                test.setCorrect(cursor.getInt(cursor.getColumnIndex(StoredTest.COLUMN_CORRECT)));
                test.setSize(cursor.getInt(cursor.getColumnIndex(StoredTest.COLUMN_SIZE)));
                test.setTime(cursor.getInt(cursor.getColumnIndex(StoredTest.COLUMN_TIME)));

                tests.add(test);
            } while (cursor.moveToNext());
        }
        db.close();
        return tests;
    }

    public int getTotalCount() {
        String countQuery = "SELECT  * FROM " + StoredTest.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}

