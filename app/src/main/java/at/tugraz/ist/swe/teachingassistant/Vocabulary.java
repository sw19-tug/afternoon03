package at.tugraz.ist.swe.teachingassistant;
import android.util.Log;

import java.util.Vector;
public class Vocabulary
{
    private static Vocabulary instance;
    private Vector<Vocab> vocabulary_vector = new Vector<>();

    private Vocabulary(){}

    public static Vocabulary getInstance()
    {
        if(instance == null)
        {
            instance = new Vocabulary();
        }
        return instance;
    }

    public void addVocab(Vocab vocab)
    {
       vocabulary_vector.add(vocab);
    }

    public void printVocab()
    {
        for(Vocab vocab : vocabulary_vector)
        {
            Log.e("Output",vocab.germanTranslation() + " " + vocab.getEnglishTranslation());
        }

    }

}
