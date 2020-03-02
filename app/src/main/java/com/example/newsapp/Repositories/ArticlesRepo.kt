package com.example.newsapp.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.Models.Article
import com.example.newsapp.newsAPI.ArticleService
import com.example.newsapp.newsAPI.ArticleServiceGenerator
import com.example.newsapp.Models.ArticlesResponse
import retrofit2.Call
import kotlin.random.Random

class ArticlesRepo private constructor() {
    private object HOLDER {
        val instance = ArticlesRepo()
    }
    companion object {
        val instance: ArticlesRepo by lazy { HOLDER.instance }
    }
    private val category = "sports"
    private val language = "en"
    private val country = "us"
    private val api_key = "b003d08e5c694c209b06604ac60629ef" //todo remove it from here

    fun getArticlesResponseCall(): Call<ArticlesResponse> {
        val articleService = ArticleServiceGenerator.createArticleService(ArticleService::class.java)
        /*if(Random.nextBoolean())
            return articleService.getArticles("health", language, country, api_key)
        */
        return articleService.getArticles(category, language, country, api_key)
    }

}
