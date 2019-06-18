package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StoredTest_UnitTest
{
    @Mock
    Context mockContext;
    int id = 0, size = 100, correct = 50;
    String date = "date";
    public StoredTest storedTest = new StoredTest(id, date, size, correct);

    @Test
    public void testSetGetId()
    {
        storedTest.setId(1);
        assertEquals(storedTest.getId(), 1);
        storedTest.setId(id);
        assertEquals(storedTest.getId(), id);
    }

    @Test
    public void testSetGetDate()
    {
        storedTest.setDate("date_new");
        assertEquals(storedTest.getDate(), "date_new");
        storedTest.setDate(date);
        assertEquals(storedTest.getDate(), date);
    }

    @Test
    public void testSetGetSize()
    {
        storedTest.setSize(10);
        assertEquals(storedTest.getSize(), 10);
        storedTest.setSize(size);
        assertEquals(storedTest.getSize(), size);
    }

    @Test
    public void testSetGetCorrect()
    {
        storedTest.setCorrect(10);
        assertEquals(storedTest.getCorrect(), 10);
        storedTest.setCorrect(correct);
        assertEquals(storedTest.getCorrect(), correct);
    }
}