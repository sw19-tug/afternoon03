package at.tugraz.ist.swe.teachingassistant;

import android.support.test.runner.AndroidJUnit4;


import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DisplayInputTest {
    @Test
    public void testInputTwoStrings() {
    //DisplayInput = new  DisplayInput();

    //assertEquals(0,1);
    }

    @Test
    public void testAddInterface() {
        onView(withId(R.id.btn_listadd)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_first_lang)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_second_lang)).check(matches(isDisplayed()));
        //onView(withId(R.id.btn_save_translation)).check(matches(isDisplayed()));
        onView(withId(R.id.et_first_lang)).check(matches(isDisplayed()));
        onView(withId(R.id.et_second_lang)).check(matches(isDisplayed()));
    }
}



