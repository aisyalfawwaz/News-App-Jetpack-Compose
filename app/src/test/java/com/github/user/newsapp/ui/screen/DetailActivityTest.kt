package com.github.user.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @Test
    fun start_shouldStartActivityWithCorrectExtras() {
        val title = "Test Title"
        val author = "Test Author"
        val description = "Test Description"
        val imageUrl = "http://example.com/image.png"

        val intent = Intent(getApplicationContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_TITLE, title)
            putExtra(DetailActivity.EXTRA_AUTHOR, author)
            putExtra(DetailActivity.EXTRA_DESCRIPTION, description)
            putExtra(DetailActivity.EXTRA_IMAGE_URL, imageUrl)
        }

        val scenario = ActivityScenario.launch<DetailActivity>(intent)
        scenario.onActivity { activity ->
            val receivedIntent = activity.intent
            val extras = receivedIntent?.extras

            assertEquals(title, extras?.getString(DetailActivity.EXTRA_TITLE))
            assertEquals(author, extras?.getString(DetailActivity.EXTRA_AUTHOR))
            assertEquals(description, extras?.getString(DetailActivity.EXTRA_DESCRIPTION))
            assertEquals(imageUrl, extras?.getString(DetailActivity.EXTRA_IMAGE_URL))
        }
    }

    // Add more test cases as needed
}
