package com.example.newsapp.newsAPI

import com.example.newsapp.Models.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ArticleService {
    @GET("/v2/top-headlines")
    fun getArticles(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("country") country: String,
        @Query("apiKey") apikey: String
    ): Call<ArticlesResponse>
}