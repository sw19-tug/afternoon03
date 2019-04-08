/*package at.tugraz.ist.swe.teachingassistant;


import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
//import com.google.gson.Gson;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Type;
import java.util.Vector;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class LANG_008_ExportImportTest {
    @Test
    public void testExportPASS() {
        int ret = 1;
        Gson gson = new Gson();
        VocabularManager manager = VocabularManager.getInstance();
        Word w1 = new Word("Hallo", "DE");
        Word w2 = new Word("hello", "EN");
        manager.addVocab(w1, w2);
        Type vocabVector = new TypeToken<Vector<Vocab>>() {
        }.getType();
        String jsonString = gson.toJson(manager.getVocabs());
        Log.w("JSON before", gson.toJson(jsonString));
        Vector<Vocab> vocabs = gson.fromJson(jsonString, vocabVector);
        Log.e("Word test", vocabs.elementAt(0).getTranslation_table().elementAt(0).getText());
       
        boolean isEqual = vocabs.equals(manager.getVocabs());
        assertEquals(true, isEqual);
        //Log.w("JSON", gson.toJson(manager.getVocabs()));
//        assertEquals(ret , 1);
    }

}*/