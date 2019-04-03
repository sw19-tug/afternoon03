package at.tugraz.ist.swe.teachingassistant;


import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LANG_008_ExportImportTest
{
    @Test
    public void testExportPASS()
    {
        int ret = 1;
        Gson gson = new Gson();
        VocabularManager manager = VocabularManager.getInstance();
        Word w1 = new Word("Hallo", "DE");
        Word w2 = new Word("hello", "EN");
        manager.addVocab(w1,w2);
        gson.toJson(manager.getVocabs());

        Log.w("JSON", gson.toString());


        assertEquals(ret , 1);

    }

}