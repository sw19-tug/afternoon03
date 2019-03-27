package at.tugraz.ist.swe.teachingassistant;

import java.util.Vector;

public class Vocab
{
    //rating
    private Vector<Word> translation_table;

    public Vocab()
    { }

    public String getTranslationByLanguage(String lang)
    {
        for (Word w : translation_table)
        {
            if (w.getLang().equals(lang.toLowerCase()))
            {
                return w.getText();

            }
        }
        // // TODO: error, no translation found
        return null;
    };
}

