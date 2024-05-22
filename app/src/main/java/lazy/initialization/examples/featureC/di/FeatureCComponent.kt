package lazy.initialization.examples.featureC.di

import dagger.Component
import lazy.initialization.examples.di.ComponentHolder
import lazy.initialization.examples.di.DIComponent
import lazy.initialization.examples.exampleScreen.di.AppDependencies
import lazy.initialization.examples.exampleScreen.di.AppDependenciesComponentHolder
import lazy.initialization.examples.featureC.data.UsecaseC

/**
 * @author a.taganov
 */
interface FeatureCComponent: DIComponent {
    val usecaseC: UsecaseC
}

@Component(modules = [FeatureCModuleLazy::class], dependencies = [AppDependencies::class])
internal interface FeatureCComponentImpl: FeatureCComponent {

    @Component.Factory
    interface Factory {
        fun build(dependencies: AppDependencies): FeatureCComponentImpl
    }
}

object FeatureCComponentHolder: ComponentHolder<FeatureCComponent>() {
    override fun build(): FeatureCComponent {
        return DaggerFeatureCComponentImpl.factory().build(
            AppDependenciesComponentHolder.get()
        )
    }
}
