package com.engie.eea_tech_interview

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.engie.eea_tech_interview.ui.MoviesActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesScreenUITest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MoviesActivity>()

    @Before
    fun before(){
    }

    @Test
    fun test_movies_list_displayed_uis(){
        composeRule.onNodeWithTag("Main Column").assertExists()
        composeRule.onNodeWithTag("Default Row").assertExists()
        composeRule.onNodeWithTag("Grid Layout").assertExists()
    }

    @Test
    fun searchIcon_opensSearchView() {
        composeRule.onNodeWithContentDescription("Search Icon").performClick()
        composeRule.onNodeWithTag("Search Row").assertExists()
    }

    @Test
    fun cancelSearch_closesSearchView() {
        composeRule.onNodeWithContentDescription("Search Icon").performClick()
        composeRule.onNodeWithContentDescription("Cancel Icon").performClick()
        composeRule.onNodeWithTag("Default Row").assertExists()
    }

}