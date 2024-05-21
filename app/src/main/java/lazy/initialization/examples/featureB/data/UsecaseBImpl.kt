package lazy.initialization.examples.featureB.data

import dagger.Lazy
import lazy.initialization.examples.domain.BaseUsecase
import lazy.initialization.examples.domain.BaseUsecaseLazy
import lazy.initialization.examples.featureB.UsecaseB
import javax.inject.Inject

/**
 * @author a.taganov
 */
internal class UsecaseBImpl @Inject constructor(
    override val repo: RepositoryB,
) : BaseUsecase<RepositoryB>, UsecaseB

internal class UsecaseLazyImplB @Inject constructor(
    override val repoLazy: Lazy<RepositoryB>,
) : BaseUsecaseLazy<RepositoryB>, UsecaseB
