package com.example.newsapp.UI.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.models.Article
import com.example.newsapp.models.ArticlesResponse
import com.example.newsapp.repositories.ArticlesRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesFragmentViewModel: ViewModel() {
    private var articles: MutableLiveData<List<Article>> = MutableLiveData()
    private var articlesRepo: ArticlesRepo = ArticlesRepo

    fun getArticles (): MutableLiveData<List<Article>>  {
        return articles
    }

    fun fetchArticles(){
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
                    //erro
                }
            }
        })
    }
}