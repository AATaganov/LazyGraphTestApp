package lazy.initialization.examples.exampleScreen.di

import android.app.Application
import dagger.Binds
import dagger.Component
import dagger.Module
import lazy.initialization.examples.exampleScreen.ExampleViewModelSync
import lazy.initialization.examples.exampleScreen.ExampleViewModel
import lazy.initialization.examples.exampleScreen.ExampleScreen
import lazy.initialization.examples.featureA.di.FeatureAModule
import lazy.initialization.examples.featureB.di.FeatureBModule
import lazy.initialization.examples.featureC.di.FeatureCModule

/**
 * @author a.taganov
 */
internal fun buildSyncComponent(application: Application): SyncComponent {
    return DaggerSyncComponent.factory()
        .create(AppDependencies.Impl(application))
}

@Component(
    modules = [
        FeatureAModule::class,
        FeatureBModule::class,
        FeatureCModule::class,
        SyncModule::class,
    ],
    dependencies = [AppDependencies::class]
)
internal interface SyncComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: AppDependencies): SyncComponent
    }

    fun inject(screen: ExampleScreen)
}

@Module
internal abstract class SyncModule {

    @Binds
    abstract fun vm(impl: ExampleViewModelSync): ExampleViewModel
}
