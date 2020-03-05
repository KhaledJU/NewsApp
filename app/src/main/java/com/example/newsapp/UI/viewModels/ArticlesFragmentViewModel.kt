package com.example.newsapp.UI.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.models.Article
import com.example.newsapp.repositories.ArticlesRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArticlesFragmentViewModel: ViewModel() {
    private var articles: MutableLiveData<List<Article>> = MutableLiveData()
    private var articlesRepo: ArticlesRepo = ArticlesRepo
    private var articleDisposable: Disposable? = null
    fun getArticles (): MutableLiveData<List<Article>>  {
        return articles
    }

    fun fetchArticles(){
        val articlesResponseObservable =
            articlesRepo.getArticlesResponseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        articleDisposable = articlesResponseObservable.subscribe({ articlesResponse ->
            articles.value = articlesResponse.articles
        }, {
           Log.d(TAG, it.message)
        })

    }

    override fun onCleared() {
        super.onCleared()
        articleDisposable?.dispose()
    }
}