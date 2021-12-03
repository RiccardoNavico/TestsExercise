package com.navico.testsexample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun givenMainActivity_whenClickValidationButtonWithEmptyString_checkErrorMessageInResultTextView() {
        val stringToType = ""
        onView(withId(R.id.et_password)).perform(typeText(stringToType))
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.tv_result)).check(matches(withText(R.string.pwd_requirement_too_short)))
    }

    @Test
    fun givenMainActivity_whenClickValidationButtonWithEmptyString_checkErrorMessageInResultTextViewaa() {
        val stringToType = "abc"
        onView(withId(R.id.et_password)).perform(typeText(stringToType))
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.et_password)).perform(typeText(stringToType))
        onView(withId(R.id.tv_result)).check(matches(withText("")))
    }

}