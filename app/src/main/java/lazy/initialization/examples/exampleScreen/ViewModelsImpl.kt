package lazy.initialization.examples.exampleScreen

import dagger.Lazy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import lazy.initialization.examples.featureA.UsecaseA
import lazy.initialization.examples.featureB.UsecaseB
import lazy.initialization.examples.featureC.data.UsecaseC
import javax.inject.Inject

/**
 * @author a.taganov
 */
internal class ExampleViewModelSync @Inject constructor(
    override val usecaseA: UsecaseA,
    override val usecaseB: UsecaseB,
    override val usecaseC: UsecaseC,
) : BaseExampleViewModel() {

    override val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
}

internal class ExampleViewModelLazy @Inject constructor(
    private val usecaseLazyA: Lazy<UsecaseA>,
    private val usecaseLazyB: Lazy<UsecaseB>,
    private val usecaseLazyC: Lazy<UsecaseC>,
) : BaseExampleViewModel() {

    override val usecaseA: UsecaseA
        get() = usecaseLazyA.get()
    override val usecaseB: UsecaseB
        get() = usecaseLazyB.get()
    override val usecaseC: UsecaseC
        get() = usecaseLazyC.get()

    override val scope: CoroutineScope by lazy { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
}
