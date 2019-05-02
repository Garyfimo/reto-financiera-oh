package com.garyfimo.retofinancieraoh;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.garyfimo.retofinancieraoh.ui.clients.list.ClientListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, false, false);

    private MainActivity activity = null;

    private static final Intent MY_ACTIVITY_INTENT =
            new Intent(getInstrumentation().getTargetContext(),
                    MainActivity.class);

    @Before
    public void setup() {
        rule.launchActivity(MY_ACTIVITY_INTENT);
        activity = rule.getActivity();
    }

    @Test
    public void showContainer() {
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }

    @Test
    public void showFloatingActionButton() {
        rule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container, ClientListFragment.newInstance()).commit();
        getInstrumentation().waitForIdleSync();
        onView(withId(R.id.fab_add)).inRoot(isPlatformPopup());
    }

    @Test
    public void showRecyclerView() {
        rule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container, ClientListFragment.newInstance()).commit();
        getInstrumentation().waitForIdleSync();
        onView(withId(R.id.recyclerView)).inRoot(isPlatformPopup());
    }

    @Test
    public void showLoading() throws InterruptedException {
        ClientListFragment fragment = ClientListFragment.newInstance();
        rule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commitAllowingStateLoss();
        getInstrumentation().waitForIdleSync();
        View view = fragment.getView().findViewById(R.id.layout_loading);
        assertNotNull(view);
        Thread.sleep(5000);
        assertEquals(View.GONE, view.getVisibility());
    }

    @After
    public void tearDown() {
        activity = null;
        rule = null;
    }
}