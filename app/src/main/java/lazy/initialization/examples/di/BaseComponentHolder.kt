package lazy.initialization.examples.di

/**
 * @author a.khodanovich
 *
 * Интерфейс базового поведения холдера предоставляющего компонет
 */
interface BaseComponentHolder<Component : DIComponent> {

    fun get(): Component

    fun set(component: Component)

    fun clear()
}
