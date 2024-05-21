package lazy.initialization.examples.featureA.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import lazy.initialization.examples.data.LocalDao
import lazy.initialization.examples.data.RetrofitApiA
import lazy.initialization.examples.data.buildRetrofit
import lazy.initialization.examples.data.createDao
import lazy.initialization.examples.featureA.UsecaseA
import lazy.initialization.examples.featureA.data.DaoA
import lazy.initialization.examples.featureA.data.DataStoreA
import lazy.initialization.examples.featureA.data.RepositoryA
import lazy.initialization.examples.featureA.data.RepositoryImplA
import lazy.initialization.examples.featureA.data.RepositoryLazyImplA
import lazy.initialization.examples.featureA.data.UsecaseAImpl
import lazy.initialization.examples.featureA.data.UsecaseLazyImplA

/**
 * @author a.taganov
 */
@Module(includes = [FeatureADataModule::class])
internal abstract class FeatureAModule {

    @Binds
    abstract fun usecaseA(usecaseAImpl: UsecaseAImpl): UsecaseA

    @Binds
    abstract fun repositoryA(impl: RepositoryImplA): RepositoryA
}

@Module(includes = [FeatureADataModule::class])
internal abstract class FeatureAModuleLazy {
    @Binds
    abstract fun usecaseA(usecaseAImpl: UsecaseLazyImplA): UsecaseA

    @Binds
    abstract fun repositoryA(impl: RepositoryLazyImplA): RepositoryA
}

@Module
internal class FeatureADataModule {

    private val Context.dataStoreA: DataStore<Preferences> by preferencesDataStore(name = "storeA")

    @DataStoreA
    @Provides
    fun dataStoreA(context: Context): DataStore<Preferences> = context.dataStoreA

    @Provides
    @DaoA
    fun daoA(context: Context): LocalDao = context.createDao("database_a.db")

    @Provides
    fun apiA(): RetrofitApiA {
        val retrofit = buildRetrofit("http://baseurlA.ru/")
        return retrofit.create(RetrofitApiA::class.java)
    }
}
