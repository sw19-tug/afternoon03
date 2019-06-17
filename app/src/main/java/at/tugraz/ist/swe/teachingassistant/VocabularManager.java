package at.tugraz.ist.swe.teachingassistant;

import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class VocabularManager {
    private static VocabularManager instance;

    private Vector<Vocab> vocabs;
    private Vector<Vocab> vocabs_changed;

    public VocabularManager() {
        vocabs = new Vector<>();
    }


    public static VocabularManager getInstance() {
        if (instance == null) {
            instance = new VocabularManager();
        }
        return instance;
    }

    public ArrayList<Vocab> getVocabsArrayList() {
        return new ArrayList<Vocab>(vocabs);
    }

    public void setVocabs(Vector<Vocab> input) {
        vocabs = input;
    }

    public ArrayList<String> getWordsFromVocabs(ArrayList<Vocab> vocabs, String languageCode) {
        ArrayList<String> oneLangList = new ArrayList<>();
        for (Vocab vocab : vocabs) {
            oneLangList.add(vocab.getTranslationByLanguage(languageCode));
        }
        return oneLangList;
    }

    public void addVocab(Word word1, Word word2, @Nullable String tags) {
        Vocab vocab = new Vocab();
        vocab.setTags(tags);
        Vector<Word> words = new Vector<Word>();
        words.add(word1);
        words.add(word2);
        vocab.setTranslation_table(words);
        vocabs.add(vocab);

        for (Vocab vocab1 : vocabs) {
            for (Word word3 : vocab1.getTranslation_table()) {

            }
        }
    }

    public Vector<String> getWordsFromLanguage(String langCode) {

        Vector<String> words = new Vector<>();
        for (Vocab vocab : vocabs) {

            String word = vocab.getTranslationByLanguage(langCode);
            if (word != null) {
                words.add(word);
            }
        }
        return words;
    }

    public void deleteVocabByWord(String first_lang, String second_lang)
    {
        Vector<Vocab> vocabsToDelete = new Vector<Vocab>();
        for (Vocab vocab : vocabs) {
            Vector<Word> words = vocab.getTranslation_table();
            if(words.get(0).getText().equals(first_lang) && words.get(1).getText().equals(second_lang))
            {
                vocabsToDelete.add(vocab);
            }
        }
        for (Vocab vocab : vocabsToDelete) {
            vocabs.removeElement(vocab);
        }
    }


    public ArrayList<String> getWordsFromChangedLanguageString(String langCode) {
        ArrayList<String> words = new ArrayList<>();
        for (Vocab vocab : vocabs_changed) {
            String word = vocab.getTranslationByLanguage(langCode);
            if (word != null) {
                words.add(word);
            }
        }
        return words;
    }


    public ArrayList<String> getWordsFromLanguageString(String langCode) {
        ArrayList<String> words = new ArrayList<>();
        for (Vocab vocab : vocabs) {
            String word = vocab.getTranslationByLanguage(langCode);
            if (word != null) {
                words.add(word);
            }
        }
        changeVocabOrder(langCode, words);
        return words;
    }

    public void clearVocabs() {
        vocabs.clear();
    }


    private void changeVocabOrder(String langCode, ArrayList<String> words) {
        Vector<Vocab> vocabs_new = new Vector<>();
        Vector<Vocab> vocabs_tmp = new Vector<>(vocabs);
        ArrayList<String> words_tmp = new ArrayList<>(words);
        while (!vocabs_tmp.isEmpty()) {
            for (Vocab vocab : vocabs_tmp) {
                if (words_tmp.isEmpty()) {
                    vocabs_changed = vocabs_new;
                    return;
                }
                if (vocab.getTranslationByLanguage(langCode).equals(words_tmp.get(0))) {
                    vocabs_new.add(vocab);
                    vocabs_tmp.remove(vocab);
                    words_tmp.remove(0);
                    break;
                }
            }
        }
        vocabs_changed = vocabs_new;
    }

    public ArrayList<String> getWordsFromLanguageRatingString(String langCode, int rating) {
        ArrayList<String> words = new ArrayList<>();
        for (Vocab vocab : vocabs) {
            if (vocab.getRating() == rating) {
                String word = vocab.getTranslationByLanguage(langCode);
                if (word != null) {
                    words.add(word);
                }
            }
        }

        changeVocabOrder(langCode, words);
        return words;
    }

    public ArrayList<String> getSortedWords(String langCode, int sort) {
        Vector<Vocab> vocabs = getVocabs();
        ArrayList<String> words = new ArrayList<>();
        if (sort == 1) {
            for (int i = 0; i <= 2; i++) {
                for (Vocab vocab : vocabs) {
                    if (vocab.getRating() == i) {
                        words.add(vocab.getTranslationByLanguage(langCode));
                    }
                }
            }
        } else {
            for (int i = 2; i >= 0; i--) {
                for (Vocab vocab : vocabs) {
                    if (vocab.getRating() == i) {
                        words.add(vocab.getTranslationByLanguage(langCode));
                    }
                }
            }
        }
        changeVocabOrder(langCode, words);
        return words;
    }

    public Vector<Vocab> getVocabs() {
        return vocabs;
    }

    public Vector<Vocab> getChangedVocabs() {
        return vocabs_changed;
    }

    public void changeRatingOfVocabInVocabs(Vocab voc, int rating) {
        for (Vocab vocab : vocabs) {
            if (vocab.hashCode() == voc.hashCode()) {
                vocab.setRating(rating);
            }
        }
    }

    public void editVocabByWord(String first_lang, String second_lang, Word new_word1, Word new_word2, String tag)
    {
        this.deleteVocabByWord(first_lang,second_lang);
        this.addVocab(new_word1, new_word2, tag);
    }

    public void clear() {
        vocabs = new Vector<>();
    }

    public boolean emptyVocabCheck() {
        if(vocabs.size() == 0){
            return true;
        }
        return false;
    }


    public int export(String filename){
        return 0;
    }
}
