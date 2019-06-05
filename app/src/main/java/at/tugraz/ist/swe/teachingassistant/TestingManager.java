package at.tugraz.ist.swe.teachingassistant;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class TestingManager {
    private static TestingManager instance;

    private Vector<Vocab> testingVocabs;
    private Vector<Vocab> incorrectVocabs;
    private Vector<Vocab> rootVocabs;

    private int score;
    private int current_position;

    private TestingManager() {
        testingVocabs = new Vector<>();
    }


    public static TestingManager getInstance() {
        if (instance == null) {
            instance = new TestingManager();
        }

        return instance;
    }

    public void setTestingVocabs(Vector<Vocab> vocabs){
        rootVocabs = vocabs;
    }

    public void buildRandomTest(){
        score = 0;
        current_position = 0;
        testingVocabs = rootVocabs;
        Collections.shuffle(testingVocabs);
    }

    public Vocab getVocabByPosition(int position) {
        return testingVocabs.elementAt(position);
    }

    public int getActiveSize(){
        return testingVocabs.size();
    }

    public boolean checkResult(String requested_Word){

        if(requested_Word.equals(testingVocabs.elementAt(current_position).getTranslationByLanguage("fi"))){
            score++;
            current_position++;
            return true;
        }
        //incorrectVocabs.add(testingVocabs.elementAt(current_position-1));
        current_position++;
        return false;
    }
}