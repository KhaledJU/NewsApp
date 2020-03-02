package com.example.newsapp.Repositories

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.Models.Article
import com.example.newsapp.newsAPI.ArticleService
import com.example.newsapp.newsAPI.ArticleServiceGenerator
import com.example.newsapp.newsAPI.ArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesRebo private constructor() {
    private object HOLDER {
        val instance = ArticlesRebo()
    }
    companion object {
        val instance: ArticlesRebo by lazy { HOLDER.instance }
    }
    private var articles: ArrayList<Article> = ArrayList()
    private val category = "sports"
    private val language = "en"
    private val country = "us"
    private val api_key = "b003d08e5c694c209b06604ac60629ef" //todo remove it from here

    private fun fetchArticles() {

        val articleService =
            ArticleServiceGenerator.createArticleService(ArticleService::class.java)
        articleService.getArticles(category, language, country, api_key)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onFailure(call: Call<ArticlesResponse>, throwable: Throwable) {
                    throwable.message.let {
                        Log.d("RESPONCEFAIL", it);
                    }
                }

                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    if (response.isSuccessful) {
                        articles = response.body()?.articles as ArrayList<Article>
                    } else {
                        val errorBody = response.errorBody()
                        Log.d("RESPONCE ERROR", errorBody?.string())
                    }
                }
            })
    }

    fun getArticles(): MutableLiveData<List<Article>>{
        fetchArticles()

        val data: MutableLiveData<List<Article>> = MutableLiveData()
        data.value = articles
        return data
    }

}