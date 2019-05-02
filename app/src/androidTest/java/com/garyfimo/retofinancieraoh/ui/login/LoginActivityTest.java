package com.garyfimo.retofinancieraoh.ui.login;

import android.content.Intent;

import androidx.test.platform.app.*;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.garyfimo.retofinancieraoh.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> rule =
            new ActivityTestRule<>(LoginActivity.class, false, false);

    private static final Intent MY_ACTIVITY_INTENT =
            new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),
                    LoginActivity.class);

    @Before
    public void setup() {
        rule.launchActivity(MY_ACTIVITY_INTENT);
    }

    @Test
    public void writePhoneNumber() {
        String phoneNumber = "959821390";
        onView(withId(R.id.et_phone_number)).perform(typeText(phoneNumber), closeSoftKeyboard());
    }

    @Test
    public void cantWritePhoneNumberLetters() {
        String phoneNumberLetters = "A";
        onView(withId(R.id.et_phone_number))
                .perform(typeText(phoneNumberLetters), closeSoftKeyboard())
                .check(matches(withText("")));
    }

    @Test
    public void showSmsCodeViewAfterWritePhoneNumber() throws InterruptedException {
        String phoneNumber = "959821390";
        onView(withId(R.id.et_phone_number)).perform(typeText(phoneNumber), closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.smscode)).check(matches(isDisplayed()));
    }

    @Test
    public void noShowSmsCodeViewAfterWritePhoneNumber() throws InterruptedException {
        String phoneNumber = "95982139";
        onView(withId(R.id.et_phone_number)).perform(typeText(phoneNumber), closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.smscode)).check(matches(not(isDisplayed())));
    }

    @Test
    public void showBiometricDialogAfterClickedFingerprintButton() {
        onView(withId(R.id.iv_biometric)).perform(click());
        onView(withId(R.layout.view_bottom_sheet))
                .inRoot(isPlatformPopup());
    }
}