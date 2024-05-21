package lazy.initialization.examples.featureA.data

import dagger.Lazy
import lazy.initialization.examples.domain.BaseUsecase
import lazy.initialization.examples.domain.BaseUsecaseLazy
import lazy.initialization.examples.featureA.UsecaseA
import javax.inject.Inject

/**
 * @author a.taganov
 */
internal class UsecaseAImpl @Inject constructor(
    override val repo: RepositoryA,
) : BaseUsecase<RepositoryA>, UsecaseA

internal class UsecaseLazyImplA @Inject constructor(
    override val repoLazy: Lazy<RepositoryA>,
) : BaseUsecaseLazy<RepositoryA>, UsecaseA
