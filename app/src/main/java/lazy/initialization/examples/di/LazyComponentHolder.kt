package lazy.initialization.examples.di

abstract class LazyComponentHolder<Component : DIComponent> :
    BaseComponentHolder<Component> {

    @Volatile
    private var component: Component? = null
    private var componentProvider: () -> Component = {
        error("${javaClass.simpleName} — component provider not found")
    }

    override fun get(): Component {
        return component ?: synchronized(this) {
            component ?: componentProvider().also { set(it) }
        }
    }

    override fun clear() {
        this.component = null
    }

    fun set(provider: () -> Component) {
        componentProvider = provider
    }

    override fun set(component: Component) {
        this.component = component
    }
}
