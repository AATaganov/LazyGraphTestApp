package lazy.initialization.examples.featureB.di

import dagger.Component
import lazy.initialization.examples.di.ComponentHolder
import lazy.initialization.examples.di.DIComponent
import lazy.initialization.examples.exampleScreen.di.AppDependencies
import lazy.initialization.examples.exampleScreen.di.AppDependenciesComponentHolder
import lazy.initialization.examples.featureB.UsecaseB

/**
 * @author a.taganov
 */
interface FeatureBComponent: DIComponent {
    val usecaseB: UsecaseB
}

@Component(modules = [FeatureBModuleLazy::class], dependencies = [AppDependencies::class])
internal interface FeatureBComponentImpl: FeatureBComponent {

    @Component.Factory
    interface Factory {
        fun build(dependencies: AppDependencies): FeatureBComponentImpl
    }
}

object FeatureBComponentHolder: ComponentHolder<FeatureBComponent>() {
    override fun build(): FeatureBComponent {
        return DaggerFeatureBComponentImpl.factory().build(
            AppDependenciesComponentHolder.get()
        )
    }
}
