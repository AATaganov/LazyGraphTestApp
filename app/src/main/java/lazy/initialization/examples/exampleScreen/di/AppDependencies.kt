package lazy.initialization.examples.exampleScreen.di

import android.content.Context
import lazy.initialization.examples.di.DIComponent
import lazy.initialization.examples.di.LazyComponentHolder

/**
 * @author a.taganov
 */
interface AppDependencies : DIComponent {
    val context: Context

    class Impl(override val context: Context) : AppDependencies
}

object AppDependenciesComponentHolder : LazyComponentHolder<AppDependencies>()
