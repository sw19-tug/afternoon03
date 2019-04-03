package at.tugraz.ist.swe.teachingassistant;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Vector;

public class Exporter {
    Type vocabVector;

    public Exporter() {
        vocabVector = new TypeToken<Vector<Vocab>>() {
        }.getType();
    }

    public String stringifyVocabulary(Vector<Vocab> vocabs) {
        Gson gson = new Gson();
        return gson.toJson(vocabs, vocabVector);
    }

    public Vector<Vocab> parseVocabulary(String vocabularyString) {
        Gson gson = new Gson();
        Vector<Vocab> vocabs = gson.fromJson(vocabularyString, vocabVector);
        return vocabs;
    }
}
