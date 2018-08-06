package eu.caraus.home24.application.ui.main.selection

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.support.test.rule.ActivityTestRule
import eu.caraus.home24.R

import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Rule

import eu.caraus.home24.application.ui.main.MainActivity
import eu.caraus.home24.application.ui.start.StartActivity
import kotlinx.android.synthetic.main.activity_start.*



@RunWith(AndroidJUnit4::class)
@LargeTest
class UiFlow {

    @get:Rule
    var startActivityRule = ActivityTestRule(StartActivity::class.java)

    @get:Rule
    var mainActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun uiTestSimple() {

        onView( withId( R.id.btStart )).perform( click())

        onView( withId( R.id.tvLiked)).check( matches( withText("0")))
        onView( withId( R.id.tvTotal)).check( matches( withText("1")))

        onView( withId( R.id.ivLike)).perform( click())

        onView( withId( R.id.tvLiked)).check( matches( withText("1")))
        onView( withId( R.id.tvTotal)).check( matches( withText("2")))

        onView( withId( R.id.ivLike)).perform( click())

        onView( withId( R.id.tvLiked)).check( matches( withText("2")))
        onView( withId( R.id.tvTotal)).check( matches( withText("3")))


    }

    @Test
    fun uiTestReview() {

        onView( withId( R.id.btStart )).perform( click())

        onView( withId( R.id.tvLiked)).check( matches( withText("0")))
        onView( withId( R.id.tvTotal)).check( matches( withText("1")))

        for( i  in 1..12 ) {
            onView( withId( R.id.ivLike)).perform( click())
        }

        onView( withId( R.id.tvLiked)).check( matches( withText("12")))
        onView( withId( R.id.tvTotal)).check( matches( withText("12")))

        onView( withId( R.id.btReview)).check( matches( isDisplayed()) )

        onView( withId( R.id.btReview)).perform( click())

        pressBack()

        pressBack()

        onView( withId( R.id.btStart )).perform( click())

        onView( withId( R.id.tvLiked)).check( matches( withText("0")))
        onView( withId( R.id.tvTotal)).check( matches( withText("1")))

    }

}
