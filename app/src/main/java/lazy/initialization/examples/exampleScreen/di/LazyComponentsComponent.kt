package lazy.initialization.examples.exampleScreen.di

import dagger.Binds
import dagger.Component
import dagger.Module
import lazy.initialization.examples.di.DIComponent
import lazy.initialization.examples.exampleScreen.ExampleViewModelLazy
import lazy.initialization.examples.exampleScreen.ExampleViewModel
import lazy.initialization.examples.exampleScreen.ExampleScreen
import lazy.initialization.examples.featureA.UsecaseA
import lazy.initialization.examples.featureA.di.FeatureAComponentHolder
import lazy.initialization.examples.featureB.UsecaseB
import lazy.initialization.examples.featureB.di.FeatureBComponentHolder
import lazy.initialization.examples.featureC.data.UsecaseC
import lazy.initialization.examples.featureC.di.FeatureCComponentHolder

/**
 * @author a.taganov
 */
internal fun buildLazyComponentsComponent(): LazyComponentsComponent {
    return DaggerLazyComponentsComponent.factory()
        .create(LazyComponentsDependencies.Impl())
}

@Component(
    modules = [
        LazyComponentsModule::class,
    ],
    dependencies = [LazyComponentsDependencies::class]
)
internal interface LazyComponentsComponent : DIComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: LazyComponentsDependencies): LazyComponentsComponent
    }

    fun inject(screen: ExampleScreen)
}

@Module
internal abstract class LazyComponentsModule {

    @Binds
    abstract fun vm(impl: ExampleViewModelLazy): ExampleViewModel
}

interface LazyComponentsDependencies {

    val usecaseA: UsecaseA
        get() = FeatureAComponentHolder.get().usecaseA
    val usecaseB: UsecaseB
        get() = FeatureBComponentHolder.get().usecaseB
    val usecaseC: UsecaseC
        get() = FeatureCComponentHolder.get().usecaseC

    class Impl : LazyComponentsDependencies
}
