package com.example.newsapp.UI.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.Models.Article
import com.example.newsapp.Models.ArticlesResponse
import com.example.newsapp.Repositories.ArticlesRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesFragmentViewModel: ViewModel() {
    private var articles: MutableLiveData<List<Article>> = MutableLiveData()
    private var articlesRepo: ArticlesRepo = ArticlesRepo.instance

    fun getArticles(): LiveData<List<Article>>{
        val articlesResponseCall = articlesRepo.getArticlesResponseCall()
        articlesResponseCall.enqueue(object : Callback<ArticlesResponse> {
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
                    articles.value = response.body()?.articles
                } else {

                }
            }
        })
        return articles
    }
}