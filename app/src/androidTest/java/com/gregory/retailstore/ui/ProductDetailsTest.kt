package com.gregory.retailstore.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.gregory.retailstore.R
import com.gregory.retailstore.ToastMatcher.Companion.onToast
import com.gregory.retailstore.system.db.product.ProductDto
import com.gregory.retailstore.system.db.product.ProductModel
import com.gregory.retailstore.system.toPrice
import com.gregory.retailstore.ui.productDetails.ProductDetailFragment
import com.gregory.retailstore.ui.products.ProductFragmentDirections
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProductDetailsTest {
    private val testProduct = ProductDto(1, "Test", ProductModel.CATEGORY.ELECTRONICS, 1f, null)
    lateinit var scenario: FragmentScenario<ProductDetailFragment>

    @Before
    fun setupFragment() {
        val action =
            ProductFragmentDirections.actionNavigationHomeToProductDetailFragment(testProduct)
        scenario = launchFragmentInContainer<ProductDetailFragment>(action.arguments)
    }

    @Test
    fun testVisuals() {
        onView(withId(R.id.product_detail_title)).check(matches(withText("Test")))
        onView(withId(R.id.product_detail_price)).check(matches(withText(testProduct.price.toPrice())))
    }

    @Test
    fun addToCart() {
        onView(withId(R.id.product_detail_cart_button)).perform(click())
        onToast(R.string.product_detail_added_to_cart).check(matches(isDisplayed()))
    }

}