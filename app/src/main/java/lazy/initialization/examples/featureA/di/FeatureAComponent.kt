package lazy.initialization.examples.featureA.di

import dagger.Component
import lazy.initialization.examples.di.ComponentHolder
import lazy.initialization.examples.di.DIComponent
import lazy.initialization.examples.exampleScreen.di.AppDependencies
import lazy.initialization.examples.exampleScreen.di.AppDependenciesComponentHolder
import lazy.initialization.examples.featureA.UsecaseA

/**
 * @author a.taganov
 */
interface FeatureAComponent : DIComponent {
    val usecaseA: UsecaseA
}

@Component(modules = [FeatureAModuleLazy::class], dependencies = [AppDependencies::class])
internal interface FeatureAComponentImpl : FeatureAComponent {

    @Component.Factory
    interface Factory {
        fun build(dependencies: AppDependencies): FeatureAComponentImpl
    }
}

object FeatureAComponentHolder : ComponentHolder<FeatureAComponent>() {
    override fun build(): FeatureAComponent {
        return DaggerFeatureAComponentImpl.factory().build(
            AppDependenciesComponentHolder.get()
        )
    }
}
