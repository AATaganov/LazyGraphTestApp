package lazy.initialization.examples.domain

/**
 * @author a.taganov
 */
interface Repository {
    suspend fun config()
    suspend fun local()
    suspend fun remote()
}
