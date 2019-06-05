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
        testingVocabs = rootVocabs;
        Collections.shuffle(testingVocabs);
    }

    public Vocab getVocabByPosition(int position) {
        return testingVocabs.elementAt(position);
    }

    public int getActiveSize(){
        return testingVocabs.size();
    }
}