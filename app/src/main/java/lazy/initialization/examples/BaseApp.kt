package lazy.initialization.examples

import android.app.Application
import lazy.initialization.examples.exampleScreen.di.AppDependencies
import lazy.initialization.examples.exampleScreen.di.AppDependenciesComponentHolder

/**
 * @author a.taganov
 */
internal class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDependenciesComponentHolder.set(AppDependencies.Impl(this))
    }
}
