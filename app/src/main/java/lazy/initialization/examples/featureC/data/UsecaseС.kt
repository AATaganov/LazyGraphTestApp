package lazy.initialization.examples.featureC.data

import dagger.Lazy
import lazy.initialization.examples.domain.BaseUsecase
import lazy.initialization.examples.domain.BaseUsecaseLazy
import lazy.initialization.examples.domain.Usecase
import javax.inject.Inject

/**
 * @author a.taganov
 */
interface UsecaseC : Usecase
internal class UsecaseImplC @Inject constructor(
    override val repo: RepositoryC,
) : BaseUsecase<RepositoryC>, UsecaseC

internal class UsecaseLazyImplC @Inject constructor(
    override val repoLazy: Lazy<RepositoryC>,
) : BaseUsecaseLazy<RepositoryC>, UsecaseC
