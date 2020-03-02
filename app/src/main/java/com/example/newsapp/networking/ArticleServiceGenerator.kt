package com.example.newsapp.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArticleServiceGenerator {
    private val baseUrl = "https://newsapi.org"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createArticleService(
        serviceClass: Class<S>?
    ): S {
        return retrofit.create(serviceClass)
    }
}