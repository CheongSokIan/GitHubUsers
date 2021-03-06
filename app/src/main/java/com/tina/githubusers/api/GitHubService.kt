package com.tina.githubusers.api


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tina.githubusers.data.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface GitHubService {

    @Headers(HEADERS)
    @GET("users")
    suspend fun getUserList(
        @Query("since") since: Long,
        @Query("per_page") perPage: Int
    ): List<User>

    @Headers(HEADERS)
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): User


    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private const val HEADERS = "accept: application/vnd.github.v3+json"

        @Volatile
        private var instance: GitHubService? = null

        fun getInstance(): GitHubService {
            return instance ?: synchronized(this) {
                instance ?: create().also { instance = it }
            }
        }

        private fun create(): GitHubService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(GitHubService::class.java)
        }
    }
}