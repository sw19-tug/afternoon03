package at.tugraz.ist.swe.teachingassistant;

import java.util.Date;

public class StoredTest {
    public static final String TABLE_NAME = "tests";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_CORRECT = "correct";
    public static final String COLUMN_TIME = "time";


    private int id;
    private String date;
    private int size;
    private int correct;
    private int time;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_SIZE + " INTEGER,"
                    + COLUMN_CORRECT + " INTEGER,"
                    + COLUMN_TIME + " INTEGER"
                    + ")";

    public StoredTest() {
    }

    public StoredTest(int id, String date, int size, int correct) {
        this.id = id;
        this.date = date;
        this.size = size;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getSize() {
        return size;
    }

    public int getCorrect() {
        return correct;
    }
    public int getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
