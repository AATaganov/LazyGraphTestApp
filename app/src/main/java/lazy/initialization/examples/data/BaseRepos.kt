package lazy.initialization.examples.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Lazy
import lazy.initialization.examples.domain.Repository

/**
 * @author a.taganov
 */
internal abstract class BaseRepository : Repository {
    protected abstract val store: DataStore<Preferences>
    protected abstract val dao: LocalDao
    protected abstract val api: RetrofitApi

    override suspend fun local() { dao }
    override suspend fun remote() { api }
    override suspend fun config() { store.data }
}

internal abstract class BaseRepositoryLazy<T : RetrofitApi> : BaseRepository() {
    protected abstract val storeLazy: Lazy<DataStore<Preferences>>
    protected abstract val daoLazy: Lazy<LocalDao>
    protected abstract val apiLazy: Lazy<T>

    override val store: DataStore<Preferences>
        get() = storeLazy.get()

    override val dao: LocalDao
        get() = daoLazy.get()

    override val api: T
        get() = apiLazy.get()
}
