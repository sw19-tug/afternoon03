package at.tugraz.ist.swe.teachingassistant;


import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Vector;

public class JsonHandler
{
    public String vocabularToJsonString()
    {
        Gson gson = new Gson();
        VocabularManager manager = VocabularManager.getInstance();
        String jsonString = gson.toJson(manager.getVocabs());
        Log.e("JSON", jsonString);
        return jsonString;
    }

    public Vector<Vocab> vocabularyFromJsonString(String jsonString)
    {
        Gson gson = new Gson();
        Type vocabVector = new TypeToken<Vector<Vocab>>() {
        }.getType();

        return gson.fromJson(jsonString, vocabVector);

    }
}
