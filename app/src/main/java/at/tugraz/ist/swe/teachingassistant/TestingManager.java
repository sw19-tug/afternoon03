package at.tugraz.ist.swe.teachingassistant;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class TestingManager {
    private static TestingManager instance;
    private static Vector<String> availableTests;

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
            availableTests = new Vector<>();
            availableTests.add("Random Test");
            availableTests.add("Custom Random Test");
        }

        return instance;
    }

    public void setTestingVocabs(Vector<Vocab> vocabs){
        rootVocabs = vocabs;
    }

    public Vector<Vocab> getRootVocabs () {
        if(rootVocabs == null)
            rootVocabs = new Vector<>();
        return rootVocabs;
    }

    public void clearRootVocab(){
        if (rootVocabs != null)
            rootVocabs.clear();
    }

    public boolean addVocabToRoot(Vocab vocabToAdd){
        if(rootVocabs == null)
            rootVocabs = new Vector<>();
        if(!checkVocabAlreadyPresent(vocabToAdd)) {
            rootVocabs.add(vocabToAdd);
            return true;
        }
        return false;
    }

    public boolean checkVocabAlreadyPresent(Vocab vocabToCheck){
        for (Vocab vocab : rootVocabs){
            if (vocab == vocabToCheck)
                return true;
        }
        return false;
    }

    public void buildRandomTest(){
        score = 0;
        current_position = 0;
        testingVocabs = rootVocabs;
        Collections.shuffle(testingVocabs);
        incorrectVocabs = new Vector<>();
    }

    public Vocab getCurrentVocab() {
        return testingVocabs.elementAt(current_position);
    }

    public int getActiveSize(){
        return testingVocabs.size();
    }

    public int getCurrentPosition() {return current_position;}

    public int getScore() {return score;}

    public ArrayList<String> getAvailableTests() {
        ArrayList<String> testTypes = new ArrayList<>();
        for (String testType : availableTests) {
            testTypes.add(testType);
        }
        return testTypes;
    }

    public void setContinueTesting() {
        score = 0;
        current_position = 0;
        testingVocabs.clear();
        Collections.shuffle(incorrectVocabs);
        for (int iterator = 0; iterator < incorrectVocabs.size(); iterator++)
        {
            testingVocabs.add(incorrectVocabs.elementAt(iterator));
        }
        incorrectVocabs.clear();
    }

    public int checkResult(String requested_Word){
        current_position++;
        if(requested_Word.equals(testingVocabs.elementAt(current_position-1).getTranslationByLanguage("fi"))){
            score++;
            if(current_position == testingVocabs.size())
                return 2;

            return 1;
        }
        incorrectVocabs.add(testingVocabs.elementAt(current_position-1));

        if(current_position == testingVocabs.size()) {
            return 2;
        }
        return 0;
    }
    public int getSizeOfIncorrectVocabs(){return incorrectVocabs.size();}

}