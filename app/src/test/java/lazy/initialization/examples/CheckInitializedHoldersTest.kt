package lazy.initialization.examples

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * @author a.taganov
 */
@RunWith(AndroidJUnit4::class)
@Config(application = BaseApp::class)
internal class CheckInitializedHoldersTest : BaseHoldersTest() {
    @Test
    fun checkThatHoldersInitialized() {
        val notInitialized = findNotInitializedHolders()
        assertTrue(
            "Found not initialized holders $notInitialized",
            notInitialized.isEmpty(),
        )
    }
}
