package at.tugraz.ist.swe.teachingassistant;

public class Vocab {
    //rating
    private String first_word;
    private String second_word;

    public Vocab(String first_w, String second_w) {
        first_word = first_w;
        second_word = second_w;
    }

    public String getFirstWord() { return first_word;}
    public String getSecondWord() {return second_word;}
/*
    public Vector<Word> getTranslation_table() {
        return translation_table;
    }

    public void setTranslation_table(Vector<Word> translations) {
        translation_table = translations;
    }

    public int getRating() { return -1; }

    public void setRating(int rating){}
    public int changeRating()
    {
        return -1;
    }*/
}

