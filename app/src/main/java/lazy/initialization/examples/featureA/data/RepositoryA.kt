package lazy.initialization.examples.featureA.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Lazy
import lazy.initialization.examples.data.BaseRepository
import lazy.initialization.examples.data.BaseRepositoryLazy
import lazy.initialization.examples.data.LocalDao
import lazy.initialization.examples.data.RetrofitApiA
import lazy.initialization.examples.domain.Repository
import javax.inject.Inject
import javax.inject.Qualifier

/**
 * @author a.taganov
 */
@Qualifier
annotation class DataStoreA

@Qualifier
annotation class DaoA

interface RepositoryA : Repository

internal class RepositoryImplA @Inject constructor(
    @DataStoreA
    override val store: DataStore<Preferences>,
    @DaoA
    override val dao: LocalDao,
    override val api: RetrofitApiA,
) : BaseRepository(), RepositoryA

internal class RepositoryLazyImplA @Inject constructor(
    @DataStoreA
    override val storeLazy: Lazy<DataStore<Preferences>>,
    @DaoA
    override val daoLazy: Lazy<LocalDao>,
    override val apiLazy: Lazy<RetrofitApiA>,
) : BaseRepositoryLazy<RetrofitApiA>(), RepositoryA
