package com.example.newsapp.repositories

import com.example.newsapp.networking.ArticleService
import com.example.newsapp.networking.ArticleServiceGenerator
import com.example.newsapp.models.ArticlesResponse
import retrofit2.Call
import kotlin.random.Random

object ArticlesRepo {
    private val category = "sports"
    private val language = "en"
    private val country = "us"
    private val api_key = "5e4ae8ef3f27478c94543bec4ccc1360" //todo remove it from here

    fun getArticlesResponseCall(): Call<ArticlesResponse> {

        val articleService = ArticleServiceGenerator.createArticleService(ArticleService::class.java)
        if(Random.nextBoolean())
            return articleService.getArticles("health", language, country, api_key)

        return articleService.getArticles(category, language, country, api_key)
    }

}
