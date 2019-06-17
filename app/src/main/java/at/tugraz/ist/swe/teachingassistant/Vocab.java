package at.tugraz.ist.swe.teachingassistant;

import java.util.Vector;

public class Vocab {
    //rating
    private Vector<Word> translation_table;
    private int rating_ = 1;

    private String tags;

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        if (tags != null) {
            this.tags = tags;
        }
    }

    public Vocab() {
    }

    public String getTranslationByLanguage(String lang) {
        for (Word w : translation_table) {
            if (w.getLang().equals(lang.toLowerCase())) {
                return w.getText();

            }
        }
        return null;
    }

    public Vector<Word> getTranslation_table() {
        return translation_table;
    }

    public void setTranslation_table(Vector<Word> translations) {
        translation_table = translations;
    }

    public int getRating()
    {
        return rating_;
    }

    public void setRating(int rating)
    {
        rating_ = rating;
    }
}

