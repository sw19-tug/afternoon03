package at.tugraz.ist.swe.teachingassistant;

import android.util.Log;

import java.util.ArrayList;
import java.util.Vector;

public class VocabularManager {
    private static VocabularManager instance;
    private Vector<Vocab> vocabs;
    public ArrayList<String> words;

    private VocabularManager() {
        vocabs = new Vector<>();
    }



    public static VocabularManager getInstance() {
        if (instance == null) {
            instance = new VocabularManager();
        }
        return instance;
    }

    public void addVocab(Word word1, Word word2) {
        Vocab vocab = new Vocab();
        Vector<Word> words = new Vector<Word>();
        words.add(word1);
        words.add(word2);
        vocab.setTranslation_table(words);
        vocabs.add(vocab);

        for (Vocab vocab1 : vocabs) {
            for (Word word3 : vocab1.getTranslation_table()) {

                Log.e("Blabla ", word3.getText());
            }
        }
    }
    public Vector<String> getWordsFromLanguage(String langCode) {
        Vector<String> words = new Vector<>();
        for (Vocab vocab : vocabs) {
            String word = vocab.getTranslationByLanguage(langCode);
            if (word != null)
            {
                words.add(word);
            }
        }
        return words;
    }


    // just for VocabularyAdapter
    public ArrayList<String> getWordsFromLanguageString(String langCode) {
        ArrayList<String> words = new ArrayList<>();
        for (Vocab vocab : vocabs) {
            String word = vocab.getTranslationByLanguage(langCode);
            if (word != null) {
                words.add(word);
            }
        }
        return words;
    }

    public ArrayList<String> getWordsFromLanguageRatingString(String langCode, int rating)
    {
        ArrayList<String> words = new ArrayList<>();
        for (Vocab vocab : vocabs)
        {
            Log.e("HELP",String.valueOf(vocab.getRating()));
            if(vocab.getRating() == rating)
            {
                Log.e("HELP","the rating was found");
                String word = vocab.getTranslationByLanguage(langCode);
                if (word != null) {
                    words.add(word);
                }
            }
        }
        return words;
    }

    public ArrayList<String> getSortedWords(String langCode, int sort){
        Vector<Vocab> vocabs = getVocabs();
        ArrayList<String> words = new ArrayList<>();
        for(int x = 0; x <= 2; x++)
        {
            for(Vocab vocab: vocabs)
            {
                if(vocab.getRating() == x)
                {
                    words.add(vocab.getTranslationByLanguage(langCode));
                }
            }
        }

        return words;
    }



    public Vector<Vocab> getVocabs()
    {
        return vocabs;
    }

    public int export(String filename){
        return 0;
    }
}
