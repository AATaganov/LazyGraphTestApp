package lazy.initialization.examples.exampleScreen.di

import android.content.Context

/**
 * @author a.taganov
 */
interface AppDependencies {
    val context: Context

    class Impl(override val context: Context) : AppDependencies
}
