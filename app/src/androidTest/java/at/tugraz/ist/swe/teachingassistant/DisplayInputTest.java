package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import at.tugraz.ist.swe.teachingassistant.tools.DisplayInput;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class VocabularyInputTest {
    @Test
    public void testInputTwoStrings() {
    DisplayInput = new  DisplayInput();

    assertTrue(DisplayInput.input("Hello World","Hallo Welt"));
    }

    @Test
    public void testInputTwoStringsAndCheckDisplay() {
        DisplayInput = new  DisplayInput();

        assertTrue(DisplayInput.input("Hello World","Hallo Welt"));
        onView(withId(R.id.txt_display)).check(matches(withText("Hello,World,Hallo,Welt")));
    }
}



@RunWith(AndroidJUnit4.class)
public class PathTest {

    @Test
    public void testSimplePath() {
        List<PointF> points = new ArrayList<>();

        points.add(new PointF(10, 10));
        points.add(new PointF(20, 20));

        PathTool tool = new PathTool();

        assertTrue(tool.draw(points));
    }