package lazy.initialization.examples.exampleScreen.di

import android.app.Application
import dagger.Binds
import dagger.Component
import dagger.Module
import lazy.initialization.examples.exampleScreen.ExampleViewModelLazy
import lazy.initialization.examples.exampleScreen.ExampleViewModel
import lazy.initialization.examples.exampleScreen.ExampleScreen
import lazy.initialization.examples.featureA.di.FeatureAModule
import lazy.initialization.examples.featureB.di.FeatureBModule
import lazy.initialization.examples.featureC.di.FeatureCModule

/**
 * @author a.taganov
 */
internal fun buildLazyVmComponent(application: Application): LazyVmComponent {
    return DaggerLazyVmComponent.factory()
        .create(AppDependencies.Impl(application))
}

@Component(
    modules = [
        FeatureAModule::class,
        FeatureBModule::class,
        FeatureCModule::class,
        LazyVmModule::class,
    ],
    dependencies = [AppDependencies::class]
)
internal interface LazyVmComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppDependencies): LazyVmComponent
    }

    fun inject(screen: ExampleScreen)
}

@Module()
internal abstract class LazyVmModule {

    @Binds
    abstract fun vm(impl: ExampleViewModelLazy): ExampleViewModel
}
