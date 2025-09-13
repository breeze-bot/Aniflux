package com.aniflux.app.network

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// Generic GraphQL query request
data class GraphQLQuery(val query: String, val variables: Map<String, Any> = emptyMap())

// Response models
data class MediaTitle(val romaji: String?, val english: String?)
data class MediaCover(val large: String?)
data class Media(val id: Int, val title: MediaTitle, val coverImage: MediaCover)

data class Page(val media: List<Media>)
data class PageResponse(val Page: Page)
data class AniListResponse(val data: PageResponse)

interface ApiService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("/")
    suspend fun fetchAnime(@Body body: RequestBody): AniListResponse

    companion object {
        fun create(): ApiService {
            val client = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl("https://graphql.anilist.co/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
