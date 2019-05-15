package at.tugraz.ist.swe.teachingassistant;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class VocabularManager {
    private static VocabularManager instance;
    private Vector<Vocab> vocabs;

    private VocabularManager() {
        vocabs = new Vector<>();
    }

    public static VocabularManager getInstance() {
        if (instance == null) {
            instance = new VocabularManager();
        }
        return instance;
    }

    public ArrayList<Vocab> getVocabs() {
        return new ArrayList<Vocab> (vocabs);
    }

    public ArrayList<String> getWordsFromVocabs(ArrayList<Vocab> vocabs, String languageCode){
        ArrayList<String> oneLangList = new ArrayList<>();
        for (Vocab vocab: vocabs){
            oneLangList.add(vocab.getTranslationByLanguage(languageCode));
        }
        return oneLangList;
    }

    public void addVocab(Word word1, Word word2, String tags) {
        Vocab vocab = new Vocab();
        vocab.setTags(tags);
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

    public int export(String filename){
        return 0;
    }
}
