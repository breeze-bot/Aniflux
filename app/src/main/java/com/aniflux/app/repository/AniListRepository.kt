package com.aniflux.app.repository

import com.aniflux.network.*
import com.google.gson.Gson
import okhttp3.RequestBody

class AniListRepository(private val api: ApiService) {

    private val gson = Gson()

    suspend fun getTrendingAnime(): List<Media> {
        val query = """
            query {
              Page(page: 1, perPage: 10) {
                media(sort: TRENDING_DESC, type: ANIME) {
                  id
                  title { romaji english }
                  coverImage { large }
                }
              }
            }
        """.trimIndent()
        val body = makeRequest(query)
        return api.fetchAnime(body).data.Page.media
    }

    suspend fun getPopularAnime(): List<Media> {
        val query = """
            query {
              Page(page: 1, perPage: 10) {
                media(sort: POPULARITY_DESC, type: ANIME) {
                  id
                  title { romaji english }
                  coverImage { large }
                }
              }
            }
        """.trimIndent()
        val body = makeRequest(query)
        return api.fetchAnime(body).data.Page.media
    }

    suspend fun getAiringAnime(): List<Media> {
        val query = """
            query {
              Page(page: 1, perPage: 10) {
                media(sort: START_DATE_DESC, status: RELEASING, type: ANIME) {
                  id
                  title { romaji english }
                  coverImage { large }
                }
              }
            }
        """.trimIndent()
        val body = makeRequest(query)
        return api.fetchAnime(body).data.Page.media
    }

    private fun makeRequest(query: String): RequestBody {
        val payload = GraphQLQuery(query)
        return gson.toJson(payload).toRequestBody("application/json".toMediaType())
    }
}
