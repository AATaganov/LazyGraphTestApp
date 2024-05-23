package lazy.initialization.examples

import lazy.initialization.examples.di.LazyComponentHolder

/**
 * @author a.taganov
 */
internal abstract class BaseHoldersTest {

    protected fun findNotInitializedHolders(): Set<Class<*>> {
        val classes: List<Class<*>> = getClasses(LazyComponentHolder::class.java)
        val notInitialized: MutableSet<Class<*>> = mutableSetOf()
        classes.forEach { clazz ->
            try {
                val holder = clazz.kotlin.objectInstance as LazyComponentHolder<*>
                holder.get()
            } catch (e: @Suppress("TooGenericExceptionCaught") Exception) {
                println("Adding not initialized $clazz")
                notInitialized.add(clazz)
            }
        }
        notInitialized.remove(LazyComponentHolder::class.java)
        return notInitialized
    }

    private fun getClasses(targetClass: Class<*>): List<Class<*>> {
        return ClasspathInspector.getMatchingClassesForPackage(targetClass, "lazy/initialization")
    }
}
