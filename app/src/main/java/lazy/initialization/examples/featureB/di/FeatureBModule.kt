package lazy.initialization.examples.featureB.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import lazy.initialization.examples.data.LocalDao
import lazy.initialization.examples.data.RetrofitApiB
import lazy.initialization.examples.data.buildRetrofit
import lazy.initialization.examples.data.createDao
import lazy.initialization.examples.featureB.UsecaseB
import lazy.initialization.examples.featureB.data.DaoB
import lazy.initialization.examples.featureB.data.DataStoreB
import lazy.initialization.examples.featureB.data.RepositoryB
import lazy.initialization.examples.featureB.data.RepositoryImplB
import lazy.initialization.examples.featureB.data.RepositoryLazyImplB
import lazy.initialization.examples.featureB.data.UsecaseBImpl
import lazy.initialization.examples.featureB.data.UsecaseLazyImplB

/**
 * @author a.taganov
 */
@Module(includes = [FeatureBDataModule::class])
internal abstract class FeatureBModule {

    @Binds
    abstract fun usecaseB(usecaseBImpl: UsecaseBImpl): UsecaseB

    @Binds
    abstract fun repositoryB(impl: RepositoryImplB): RepositoryB
}

@Module(includes = [FeatureBDataModule::class])
internal abstract class FeatureBModuleLazy {
    @Binds
    abstract fun usecaseB(usecaseBImpl: UsecaseLazyImplB): UsecaseB

    @Binds
    abstract fun repositoryB(impl: RepositoryLazyImplB): RepositoryB
}

@Module
internal class FeatureBDataModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "storeB")

    @DataStoreB
    @Provides
    fun dataStoreB(context: Context): DataStore<Preferences> = context.dataStore

    @Provides
    @DaoB
    fun daoB(context: Context): LocalDao = context.createDao("database_b.db")

    @Provides
    fun apiA(): RetrofitApiB {
        val retrofit = buildRetrofit("http://baseurlB.ru/")
        return retrofit.create(RetrofitApiB::class.java)
    }
}
