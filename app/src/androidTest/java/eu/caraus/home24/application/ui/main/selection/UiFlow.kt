package eu.caraus.home24.application.ui.main.selection

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
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
import android.support.v7.widget.RecyclerView
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import eu.caraus.home24.application.ui.main.review.ReviewAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher


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
    fun uiTestReviewGoBack() {

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

    @Test
    fun uiTestReviewList() {

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

        onView(withId(R.id.rvReviewItems)).check( matches(
                atPosition(0, hasDescendant( withText("Premium Komfortmatratze Smood")))))

        onView( withId(R.id.rvReviewItems)).perform( RecyclerViewActions.scrollToPosition<ReviewAdapter.ViewHolder>(6))

        onView(withId(R.id.rvReviewItems)).check( matches(
                atPosition(6, hasDescendant(withText("Schlafsofa Latina Webstoff")))))

    }

    fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {

        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

}
