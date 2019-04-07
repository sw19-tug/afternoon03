package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import at.tugraz.ist.swe.teachingassistant.AddVocabActivity;
import at.tugraz.ist.swe.teachingassistant.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_001_UI {

    private String stringToBetyped;

    @Rule
    public ActivityTestRule<AddVocabActivity> activityRule = new ActivityTestRule<>(AddVocabActivity.class);

    public ActivityTestRule<AddVocabActivity> getActivityRule()
    {
        return activityRule;
    }


    //@Rule
   // public ActivityTestRule<VocabularyActivity> activityRule2 = new ActivityTestRule<>(VocabularyActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso";
    }

    @Test
    public void changeText_sameActivity() {


        //test if every view is there
        onView(withId(R.id.et_first_lang)).check(matches(isDisplayed()));
        onView(withId(R.id.save_translation)).check(matches(isDisplayed()));
        onView(withId(R.id.et_second_lang)).check(matches(isDisplayed()));

        //button is clickable
        onView(withId(R.id.save_translation)).check(matches(isClickable()));
       // onView(withId(R.id.button)).check(matches(isClickable()));


        //check button says save translation
        onView(withId(R.id.save_translation)).check(matches(withText("save Translation")));



        onView(withId(R.id.et_first_lang)).perform(typeText("hello"),closeSoftKeyboard());
        onView(withId(R.id.et_second_lang)).perform(typeText("hullu"),closeSoftKeyboard());

        onView(withId(R.id.save_translation)).perform(click());


        //here the other activity should start !!
        //onView(withId(R.id.button)).check(matches(isDisplayed()));

       /* activityRule.finishActivity();


        Intent intent = new Intent(activityRule.getActivity(),VocabularyActivity.class);
        activityRule.launchActivity(intent); *7

        //launchActivity<VocabularyActivity>();


     */

    }
}
