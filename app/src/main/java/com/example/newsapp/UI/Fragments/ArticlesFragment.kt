package com.example.newsapp.UI.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsapp.R
import com.example.newsapp.UI.Adapters.ArticleAdapter
import com.example.newsapp.newsAPI.Article
import com.example.newsapp.newsAPI.ArticleService
import com.example.newsapp.newsAPI.ArticleServiceGenerator
import com.example.newsapp.newsAPI.ArticlesResponse
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.android.synthetic.main.fragment_articles.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ArticlesFragment : Fragment() {

    var articles: ArrayList<Article> = ArrayList()
    val category = "sports"
    val language = "en"
    val country = "us"
    val api_key = "b003d08e5c694c209b06604ac60629ef" //todo remove it from here
    var articleAdapter: ArticleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_articles, container, false)

        articleAdapter = ArticleAdapter(articles, rootView.context)

        rootView.newsRecycle.layoutManager = LinearLayoutManager(rootView.context)
        rootView.newsRecycle.setHasFixedSize(true)
        rootView.newsRecycle.adapter = articleAdapter

        fetchArticles(rootView)
        rootView.swiper.setOnRefreshListener {
            fetchArticles(rootView)
        }

        return rootView
    }

    private fun fetchArticles(rootView: View) {

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
                        updateUI(rootView)
                    } else {
                        val errorBody = response.errorBody()
                        Log.d("RESPONCE ERROR", errorBody?.string())
                    }
                }
            })
    }

    private fun updateUI(rootView: View) {
        articleAdapter?.changeArtecles(articles)
        rootView.swiper.isRefreshing = false
    }


}
