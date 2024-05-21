package lazy.initialization.examples.domain

/**
 * @author a.taganov
 */
interface Usecase {
    suspend fun invoke()
}
