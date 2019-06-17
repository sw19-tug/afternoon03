package at.tugraz.ist.swe.teachingassistant;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LANG_007 {

    @Test
    public void addTestToDb() throws Exception {
        DatabaseHelper db = new DatabaseHelper(InstrumentationRegistry.getTargetContext());
        int beforeCount = db.getTotalCount();
        Log.i("Before count: ",Integer.toString(beforeCount));
        db.insert(12,10, 12);
        int afterCount = db.getTotalCount();
        Log.i("After count: ",Integer.toString(afterCount));
        for (StoredTest test: db.getAllNotes()) {
            Log.i("Stored test: ",test.getId() + " " + test.getCorrect() + " " + test.getSize() + " " + test.getDate());
        }

        assertEquals(beforeCount+ 1 , afterCount);
    }
}

