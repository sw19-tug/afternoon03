package at.tugraz.ist.swe.teachingassistant;

public class StoredVocab {
    public static final String TABLE_NAME = "vocabs";

    public static final String C_ID = "id";
    public static final String C_RATING = "rating";
    public static final String C_TAGS = "tags";
    public static final String C_ENWORD = "enWord";
    public static final String C_FIWORD = "fiWord";


    public int id;
    public int rating;
    public String tags;
    public String enWord;
    public String fiWord;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + C_RATING + " INTEGER,"
                    + C_TAGS + " TEXT,"
                    + C_ENWORD + " TEXT,"
                    + C_FIWORD + " TEXT"
                    + ")";

    public StoredVocab() {
    }

    public StoredVocab(int id, int rating, String tags, String enWord, String fiWord) {
        this.id = id;
        this.rating = rating;
        this.tags = tags;
        this.enWord = enWord;
        this.fiWord= fiWord;
    }
}
