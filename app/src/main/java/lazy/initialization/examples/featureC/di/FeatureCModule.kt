package lazy.initialization.examples.featureC.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import lazy.initialization.examples.data.LocalDao
import lazy.initialization.examples.data.RetrofitApiC
import lazy.initialization.examples.data.buildRetrofit
import lazy.initialization.examples.data.createDao
import lazy.initialization.examples.featureC.data.DaoC
import lazy.initialization.examples.featureC.data.DataStoreC
import lazy.initialization.examples.featureC.data.RepositoryC
import lazy.initialization.examples.featureC.data.RepositoryImplC
import lazy.initialization.examples.featureC.data.RepositoryLazyImplC
import lazy.initialization.examples.featureC.data.UsecaseC
import lazy.initialization.examples.featureC.data.UsecaseImplC
import lazy.initialization.examples.featureC.data.UsecaseLazyImplC

/**
 * @author a.taganov
 */
@Module(includes = [FeatureCDataModule::class])
internal abstract class FeatureCModule {

    @Binds
    abstract fun usecaseС(usecase: UsecaseImplC): UsecaseC

    @Binds
    abstract fun repositoryС(impl: RepositoryImplC): RepositoryC
}

@Module(includes = [FeatureCDataModule::class])
internal abstract class FeatureCModuleLazy {

    @Binds
    abstract fun usecaseС(usecaseBImpl: UsecaseLazyImplC): UsecaseC

    @Binds
    abstract fun repositoryС(impl: RepositoryLazyImplC): RepositoryC
}

@Module
internal class FeatureCDataModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "storeС")

    @DataStoreC
    @Provides
    fun dataStore(context: Context): DataStore<Preferences> = context.dataStore

    @Provides
    @DaoC
    fun dao(context: Context): LocalDao = context.createDao("database_с.db")

    @Provides
    fun api(): RetrofitApiC {
        val retrofit = buildRetrofit("http://baseurlC.ru/")
        return retrofit.create(RetrofitApiC::class.java)
    }
}
