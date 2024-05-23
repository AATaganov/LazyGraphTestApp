package lazy.initialization.examples

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author a.taganov
 */
@RunWith(AndroidJUnit4::class)
internal class CheckNotInitializedHoldersTest : BaseHoldersTest() {

    @Test
    fun checkThatHoldersNotInitialized() {
        val notInitialized = findNotInitializedHolders()
        assertTrue("Failed to find not loaded holders", notInitialized.isNotEmpty())
    }
}
