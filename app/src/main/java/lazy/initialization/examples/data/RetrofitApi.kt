package lazy.initialization.examples.data

import com.google.gson.Gson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 * @author a.taganov
 */
internal interface RetrofitApi {

    @GET
    fun getSomething()
}
internal interface RetrofitApiA : RetrofitApi
internal interface RetrofitApiB : RetrofitApi
internal interface RetrofitApiC : RetrofitApi

internal fun buildRetrofit(
    baseUrl: String,
): Retrofit {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor())
        .addNetworkInterceptor(NetworkInterceptor())
    return Retrofit.Builder()
        .client(
            okHttpClientBuilder
                .build()
        )
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
}
