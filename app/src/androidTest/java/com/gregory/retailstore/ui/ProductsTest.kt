package com.gregory.retailstore.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gregory.retailstore.MainActivity
import com.gregory.retailstore.R
import com.gregory.retailstore.ui.products.ProductFragment
import com.gregory.retailstore.ui.products.ProductsAdapter
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductsTest {
    @get:Rule
    var scenario = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testVisuals() {
        onView(withId(R.id.products_recyclerview)).check { view, noViewFoundException ->
            (view as RecyclerView).adapter?.itemCount?.let {
                assertTrue(it > 0)
            } ?: assert(false) {
                "Adapter null"
            }
        }
    }

    @Test
    fun goToProductDetails() {
        onView(withId(R.id.products_recyclerview)).perform(
            actionOnItemAtPosition<ProductsAdapter.ProductViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.product_detail_title)).check(matches(isDisplayed()))
    }
}