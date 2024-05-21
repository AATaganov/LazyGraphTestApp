package lazy.initialization.examples.exampleScreen.di

import android.app.Application
import dagger.Binds
import dagger.Component
import dagger.Module
import lazy.initialization.examples.exampleScreen.ExampleViewModelLazy
import lazy.initialization.examples.exampleScreen.ExampleViewModel
import lazy.initialization.examples.exampleScreen.ExampleScreen
import lazy.initialization.examples.featureA.di.FeatureAModuleLazy
import lazy.initialization.examples.featureB.di.FeatureBModuleLazy
import lazy.initialization.examples.featureC.di.FeatureCModuleLazy

/**
 * @author a.taganov
 */
internal fun buildLazyGraphComponent(application: Application): LazyGraphComponent {
    return DaggerLazyGraphComponent.factory()
        .create(AppDependencies.Impl(application))
}

@Component(
    modules = [
        FeatureAModuleLazy::class,
        FeatureBModuleLazy::class,
        FeatureCModuleLazy::class,
        LazyGraphModule::class,
    ],
    dependencies = [AppDependencies::class]
)
internal interface LazyGraphComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppDependencies): LazyGraphComponent
    }

    fun inject(screen: ExampleScreen)
}

@Module
internal abstract class LazyGraphModule {

    @Binds
    abstract fun vm(impl: ExampleViewModelLazy): ExampleViewModel
}
