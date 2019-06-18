package at.tugraz.ist.swe.teachingassistant;
import android.content.Context;
import android.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static junit.framework.TestCase.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class SaveTest_UnitTest {

    @Mock
    Context mockContext;

    @Test
    public void addTestToDb() throws Exception {
        DatabaseHelper db = new DatabaseHelper(mockContext);
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

