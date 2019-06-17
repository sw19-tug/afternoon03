package at.tugraz.ist.swe.teachingassistant;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Vector;

import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LANG_019_vocabs_to_database {
    private DatabaseHelper db;
    private VocabularManager vocabularManager;

    @Before
    public void setup() {
        db = new DatabaseHelper(InstrumentationRegistry.getTargetContext());
        vocabularManager = VocabularManager.getInstance();
        vocabularManager.clear();
        Word word1 = new Word("first", "en");
        Word word2 = new Word("first_trans", "fi");
        Word word3 = new Word("second", "en");
        Word word4 = new Word("second_trans", "fi");
        vocabularManager.addVocab(word1, word2, null);
        vocabularManager.addVocab(word3, word4, null);

}
    @Test
    public void testDbExportImport() {
        db.deleteVocabs();
        Vector<Vocab> memoryVocabs = vocabularManager.getVocabs();
        for (Vocab vocab : vocabularManager.getVocabs()) {
            db.insertVocab(vocab);
        }
        Vector<Vocab> vocabs = new Vector<>(db.getAllVocabs());
        assertEquals(vocabs.size(), memoryVocabs.size());
        int counter = 0;
        for (Vocab vocab : memoryVocabs) {
            assertEquals(vocab.getTags(), vocabs.elementAt(counter).getTags());
            assertEquals(vocab.getRating(), vocabs.elementAt(counter).getRating());
            assertEquals(vocab.getTranslationByLanguage("fi"), vocabs.elementAt(counter).getTranslationByLanguage("fi"));
            assertEquals(vocab.getTranslationByLanguage("en"), vocabs.elementAt(counter).getTranslationByLanguage("en"));
            counter++;
        }
        db.deleteVocabs();
    }
}
