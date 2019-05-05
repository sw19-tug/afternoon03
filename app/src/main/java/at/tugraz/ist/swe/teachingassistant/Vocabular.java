package at.tugraz.ist.swe.teachingassistant;

import java.util.Vector;

public class Vocabular {
    public String first_language;
    public String second_language;
    public Vector<Vocab> vocabs;

    public Vocabular(String first_lang, String second_lang) {
        vocabs = new Vector<>();
        first_language = first_lang;
        second_language = second_lang;
    }


}