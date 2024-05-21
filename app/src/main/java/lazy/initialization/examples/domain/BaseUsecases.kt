package lazy.initialization.examples.domain

import dagger.Lazy
import lazy.initialization.examples.measureSuspend

/**
 * @author a.taganov
 */
internal interface BaseUsecase<R : Repository> : Usecase {
    val repo: R

    override suspend fun invoke() {
        measureSuspend(repo::config)
        measureSuspend(repo::local)
        measureSuspend(repo::remote)
    }
}

internal interface BaseUsecaseLazy<R : Repository> : BaseUsecase<R> {
    val repoLazy: Lazy<R>
    override val repo: R
        get() = repoLazy.get()
}
