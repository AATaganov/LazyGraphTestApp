package lazy.initialization.examples.featureC.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Lazy
import lazy.initialization.examples.data.BaseRepository
import lazy.initialization.examples.data.BaseRepositoryLazy
import lazy.initialization.examples.data.LocalDao
import lazy.initialization.examples.data.RetrofitApiC
import lazy.initialization.examples.domain.Repository
import javax.inject.Inject
import javax.inject.Qualifier

/**
 * @author a.taganov
 */
@Qualifier
annotation class DataStoreC

@Qualifier
annotation class DaoC

interface RepositoryC : Repository

internal class RepositoryImplC @Inject constructor(
    @DataStoreC
    override val store: DataStore<Preferences>,
    @DaoC
    override val dao: LocalDao,
    override val api: RetrofitApiC,
) : BaseRepository(), RepositoryC

internal class RepositoryLazyImplC @Inject constructor(
    @DataStoreC
    override val storeLazy: Lazy<DataStore<Preferences>>,
    @DaoC
    override val daoLazy: Lazy<LocalDao>,
    override val apiLazy: Lazy<RetrofitApiC>,
) : BaseRepositoryLazy<RetrofitApiC>(), RepositoryC
