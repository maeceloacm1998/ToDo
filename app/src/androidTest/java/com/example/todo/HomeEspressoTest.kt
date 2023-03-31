package com.example.todo

import KoinTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.todo.view.HomeActivity
import com.example.todo.viewModel.HomeViewModel
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.java.KoinJavaComponent.inject

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @get:Rule
    val koinTestRule = KoinTestRule()

    // viewModel Injection
    private val viewModel: HomeViewModel by inject(HomeViewModel::class.java)

    @Before
    fun setup() {
        viewModel.deleteAllItems()
        // recreate activity
        activityRule.scenario.recreate()
    }

    @Test
    fun test_showEmptyListMessage() {
        onView(withText(R.string.empty_title)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun test_notShowHomeTitle() {
        onView(withText(R.string.home_title)).check(
            matches(not(isDisplayed()))
        )
    }

    @Test
    fun test_openNewTaskDialog() {
        val dialog = onView(withId(R.id.new_task))

        dialog.perform(click())
        dialog.inRoot(isDialog())
        onView(withText(R.string.new_task_title)).check(matches(isDisplayed()))
    }


    @Test
    fun test_createNewTask() {
        val dialog = onView(withId(R.id.new_task))
        val editTextTitle = onView(withId(R.id.task_title))
        val createNewTaskButton = onView(withId(R.id.create_task))
        val newTaskTitle = "teste espresso"

        dialog.perform(click())
        editTextTitle.perform(typeText(newTaskTitle))
        createNewTaskButton.perform(click())

        onView(withText(newTaskTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun test_checkAndUncheckTaskItem() {
        val dialog = onView(withId(R.id.new_task))
        val editTextTitle = onView(withId(R.id.task_title))
        val createNewTaskButton = onView(withId(R.id.create_task))
        val checkboxTaskItem = onView(withId(R.id.checkbox))
        val newTaskTitle = "teste espresso"

        dialog.perform(click())
        editTextTitle.perform(typeText(newTaskTitle))
        createNewTaskButton.perform(click())

        // Check
        onView(withText(newTaskTitle)).perform(click())
        checkboxTaskItem.check(matches(isChecked()))

        // Uncheck
        onView(withText(newTaskTitle)).perform(click())
        checkboxTaskItem.check(matches(not(isChecked())))
    }

    @Test
    fun test_deleteTaskItem() {
        val dialog = onView(withId(R.id.new_task))
        val editTextTitle = onView(withId(R.id.task_title))
        val createNewTaskButton = onView(withId(R.id.create_task))
        val acceptDeleteItem = R.string.delete_task_accept
        val newTaskTitle = "teste espresso"

        dialog.perform(click())
        editTextTitle.perform(typeText(newTaskTitle))
        createNewTaskButton.perform(click())
        onView(withText(newTaskTitle)).perform(longClick())
        onView(withText(acceptDeleteItem)).perform(longClick())

        onView(withText(newTaskTitle)).check(matches(not(isDisplayed())))
    }
}