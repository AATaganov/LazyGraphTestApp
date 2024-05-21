package lazy.initialization.examples.exampleScreen

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import lazy.initialization.examples.featureA.UsecaseA
import lazy.initialization.examples.featureB.UsecaseB
import lazy.initialization.examples.featureC.data.UsecaseC
import lazy.initialization.examples.measureSuspend

/**
 * @author a.taganov
 */
interface ExampleViewModel {
    fun callA()
    fun callB()
    fun callC()
}

internal abstract class BaseExampleViewModel : ExampleViewModel {

    protected abstract val usecaseA: UsecaseA
    protected abstract val usecaseB: UsecaseB
    protected abstract val usecaseC: UsecaseC
    protected abstract val scope: CoroutineScope

    override fun callA() {
        scope.launch {
            measureSuspend(::asyncCallA)
        }
    }

    override fun callB() {
        scope.launch {
            measureSuspend(::asyncCallB)
        }
    }

    override fun callC() {
        scope.launch {
            measureSuspend(::asyncCallC)
        }
    }

    private suspend fun asyncCallA() { usecaseA.invoke() }
    private suspend fun asyncCallB() { usecaseB.invoke() }
    private suspend fun asyncCallC() { usecaseC.invoke() }
}
