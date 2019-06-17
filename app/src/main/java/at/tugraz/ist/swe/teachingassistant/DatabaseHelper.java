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
import java.util.Vector;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "test_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(StoredTest.CREATE_TABLE);
        db.execSQL(StoredVocab.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + StoredTest.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StoredVocab.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insert(int size, int correct, int time) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        java.util.Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        String formattedDate = df.format(c);
        values.put(StoredTest.COLUMN_DATE, formattedDate);
        values.put(StoredTest.COLUMN_SIZE, size);
        values.put(StoredTest.COLUMN_CORRECT, correct);
        values.put(StoredTest.COLUMN_TIME, time);

        // insert row
        long id = db.insert(StoredTest.TABLE_NAME, null, values);
        // close db connection
        db.close();
        // return newly inserted row id
        return id;
    }

    public long insertVocab(Vocab vocab) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StoredVocab.C_RATING, vocab.getRating());
        values.put(StoredVocab.C_TAGS, vocab.getTags());
        values.put(StoredVocab.C_ENWORD, vocab.getTranslationByLanguage("en"));
        values.put(StoredVocab.C_FIWORD, vocab.getTranslationByLanguage("fi"));
        long id = db.insert(StoredVocab.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public void deleteVocabs() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + StoredVocab.TABLE_NAME);
    }


    public List<StoredTest> getAllNotes() {
        List<StoredTest> tests = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + StoredTest.TABLE_NAME + " ORDER BY " +
                StoredTest.COLUMN_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
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
        // close db connection
        db.close();
        // return notes list
        return tests;
    }

    public List<Vocab> getAllVocabs() {
        List<Vocab> vocabs = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + StoredVocab.TABLE_NAME + " ORDER BY id ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Vocab vocab = new Vocab();

                Word word1 = new Word(cursor.getString(cursor.getColumnIndex(StoredVocab.C_ENWORD)), "en");
                Word word2 = new Word(cursor.getString(cursor.getColumnIndex(StoredVocab.C_FIWORD)), "fi");
                vocab.setTags(cursor.getString(cursor.getColumnIndex(StoredVocab.C_TAGS)));
                vocab.setRating(cursor.getInt(cursor.getColumnIndex(StoredVocab.C_RATING)));
                Vector<Word> words = new Vector<>();
                words.add(word1);
                words.add(word2);
                vocab.setTranslation_table(words);
                vocabs.add(vocab);
            } while (cursor.moveToNext());
        }
        db.close();
        return vocabs;
    }

    public int getTotalCount() {
        String countQuery = "SELECT  * FROM " + StoredTest.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }
}

