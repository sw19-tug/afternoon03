package at.tugraz.ist.swe.teachingassistant;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddVocabUI {

    private String stringToBetyped;

    @Rule
    public ActivityTestRule<AddVocabActivity> activityRule = new ActivityTestRule<>(AddVocabActivity.class);

    public ActivityTestRule<AddVocabActivity> getActivityRule()
    {
        return activityRule;
    }

    @Before
    public void initValidString() {
        stringToBetyped = "Espresso";
    }

    @Test
    public void changeText_sameActivity() {
        onView(withId(R.id.et_first_lang)).check(matches(isDisplayed()));
        onView(withId(R.id.save_translation)).check(matches(isDisplayed()));
        onView(withId(R.id.et_second_lang)).check(matches(isDisplayed()));

        onView(withId(R.id.save_translation)).check(matches(isClickable()));
        onView(withId(R.id.save_translation)).check(matches(withText("Save Translation")));
        onView(withId(R.id.et_first_lang)).perform(typeText("hello"),closeSoftKeyboard());
        onView(withId(R.id.et_second_lang)).perform(typeText("hullu"),closeSoftKeyboard());

        onView(withId(R.id.save_translation)).perform(click());
    }
    @Test
    public void emptyInput()
    {

        onView(withId(R.id.et_first_lang)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.et_second_lang)).perform(typeText(""),closeSoftKeyboard());


        onView(withId(R.id.save_translation)).perform(click());

        onView(withId(R.id.save_translation)).check(matches(isClickable()));

    }
    public void singleInput()
    {

        onView(withId(R.id.et_second_lang)).perform(typeText("input"),closeSoftKeyboard());
        onView(withId(R.id.save_translation)).perform(click());
        onView(withId(R.id.save_translation)).check(matches(isClickable()));
    }
}
