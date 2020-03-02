package com.example.newsapp.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.Models.Article
import com.example.newsapp.Repositories.ArticlesRebo

class ArticlesFragmentViewModel: ViewModel() {
    private var articles: MutableLiveData<List<Article>>
    private var articlesRebo: ArticlesRebo

    init {
        articlesRebo = ArticlesRebo.instance
        articles = articlesRebo.getArticles()
    }

    fun getArticles(): LiveData<List<Article>>{
        return articles
    }
}