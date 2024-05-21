package lazy.initialization.examples.featureB.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Lazy
import lazy.initialization.examples.data.BaseRepository
import lazy.initialization.examples.data.BaseRepositoryLazy
import lazy.initialization.examples.data.LocalDao
import lazy.initialization.examples.data.RetrofitApiB
import lazy.initialization.examples.domain.Repository
import javax.inject.Inject
import javax.inject.Qualifier

/**
 * @author a.taganov
 */

@Qualifier
annotation class DataStoreB

@Qualifier
annotation class DaoB

interface RepositoryB : Repository

internal class RepositoryImplB @Inject constructor(
    @DataStoreB
    override val store: DataStore<Preferences>,
    @DaoB
    override val dao: LocalDao,
    override val api: RetrofitApiB,
) : BaseRepository(), RepositoryB

internal class RepositoryLazyImplB @Inject constructor(
    @DataStoreB
    override val storeLazy: Lazy<DataStore<Preferences>>,
    @DaoB
    override val daoLazy: Lazy<LocalDao>,
    override val apiLazy: Lazy<RetrofitApiB>,
) : BaseRepositoryLazy<RetrofitApiB>(), RepositoryB
