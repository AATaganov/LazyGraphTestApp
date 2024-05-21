package lazy.initialization.examples.data

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author a.taganov
 */
internal class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request)
    }
}
