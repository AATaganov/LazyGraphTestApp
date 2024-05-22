package lazy.initialization.examples.di

/**
 * @author a.khodanovich
 *
 * Позволяет получить компонент. Если компонента нет то создается новый
 */
abstract class ComponentHolder<Component : DIComponent> :
    BaseComponentHolder<Component> {

    @Volatile
    private var component: Component? = null

    override fun get(): Component {
        return component ?: synchronized(this) {
            component ?: build().also(::set)
        }
    }

    override fun set(component: Component) {
        this.component = component
    }

    protected abstract fun build(): Component

    override fun clear() {
        component = null
    }
}
