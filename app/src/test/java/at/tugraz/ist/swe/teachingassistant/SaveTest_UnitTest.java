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
    }
}

