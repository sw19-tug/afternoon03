package at.tugraz.ist.swe.teachingassistant;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VocabularManager {
    private static VocabularManager instance;
    private Vector<Vocabular> vocabulars;
    private String current_first_language;
    private String current_second_language;

    private VocabularManager() {
        vocabulars = new Vector<>();
        current_first_language = "English";
        current_second_language = "Finnish";
    }

    public static VocabularManager getInstance() {
        if (instance == null) {
            instance = new VocabularManager();
        }
        return instance;
    }

    public void addVocab(String word1, String word2) {
        Vocabular vocabular = getVocabularByTranslation(current_first_language, current_second_language);
        if(vocabular != null){
            Vocab new_vocab = new Vocab(word1,word2);
            vocabular.vocabs.add(new_vocab);
            return;
        }
        vocabular = getVocabularByTranslation(current_second_language, current_first_language);
        if (vocabular != null){
            Vocab new_vocab = new Vocab(word2,word1);
            vocabular.vocabs.add(new_vocab);
            return;
        }
        vocabular =  new Vocabular(current_first_language, current_second_language);
        vocabulars.add(vocabular);
        Vocab new_vocab = new Vocab(word1,word2);
        vocabular.vocabs.add(new_vocab);

    }

    private Vocabular getVocabularByTranslation(String first_language, String second_language){
        for (Vocabular vocabular: vocabulars){
            if(vocabular.first_language.equals(first_language) && vocabular.second_language.equals(second_language)){
                return vocabular;
            }
        }
        return null;
    }

    public Vector<String> getWordsFromLanguage(String langCode) {
        Vocabular vocabular = getVocabularByTranslation(current_first_language, current_second_language);
        Vector<String> words = new Vector<>();
        if(vocabular != null) {
            for (Vocab vocab : vocabular.vocabs) {
                if(vocabular.first_language.equals(langCode)){
                    words.add(vocab.getFirstWord());
                }
                if(vocabular.second_language.equals(langCode)){
                    words.add(vocab.getSecondWord());
                }
            }
            return words;
        }
        vocabular = getVocabularByTranslation(current_second_language, current_first_language);
        if(vocabular != null) {
            for (Vocab vocab : vocabular.vocabs) {
                if(vocabular.first_language.equals(langCode)){
                    words.add(vocab.getSecondWord());
                }
                if(vocabular.second_language.equals(langCode)){
                    words.add(vocab.getFirstWord());
                }
            }
        }
        return words;
    }


    public ArrayList<String> getWordsFromLanguageString(String langCode) {
        Vocabular vocabular = getVocabularByTranslation(current_first_language, current_second_language);
        ArrayList<String> words = new ArrayList<>();
        if(vocabular != null) {
            for (Vocab vocab : vocabular.vocabs) {
                if(vocabular.first_language.equals(langCode)){
                    words.add(vocab.getFirstWord());
                }
                if(vocabular.second_language.equals(langCode)){
                    words.add(vocab.getSecondWord());
                }
            }
            return words;
        }
        vocabular = getVocabularByTranslation(current_second_language, current_first_language);
        if(vocabular != null) {
            for (Vocab vocab : vocabular.vocabs) {
                if(vocabular.first_language.equals(langCode)){
                    words.add(vocab.getSecondWord());
                }
                if(vocabular.second_language.equals(langCode)){
                    words.add(vocab.getFirstWord());
                }
            }
        }
        return words;
    }
    public String getFirstLanguage(){return current_first_language;}

    public void setFirstLanguage(String language){current_first_language = language;}

    public String getSecondLanguage(){return current_second_language;}

    public void setSecondLanguage(String language){current_second_language = language;}

    public int export(String filename){
        return 0;
    }
}
