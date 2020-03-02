package com.example.newsapp.UI.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsapp.R
import com.example.newsapp.UI.Adapters.ArticleAdapter
import com.example.newsapp.Models.Article
import com.example.newsapp.UI.ViewModels.ArticlesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_articles.view.*


class ArticlesFragment : Fragment() {


    lateinit var articleAdapter: ArticleAdapter
    lateinit var articlesFragmentViewModel: ArticlesFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_articles, container, false)

        articlesFragmentViewModel = ViewModelProvider(this)[ArticlesFragmentViewModel::class.java]
        articlesFragmentViewModel.getArticles().observe(this, Observer {
            articleAdapter.changeArtecles(articlesFragmentViewModel.getArticles().value as ArrayList<Article>)
        })

        initArticlesRecycle(rootView)


        rootView.swiper.setOnRefreshListener {
            articlesFragmentViewModel.getArticles().observe(this, Observer {
                articleAdapter.changeArtecles(articlesFragmentViewModel.getArticles().value as ArrayList<Article>)
                rootView.swiper.isRefreshing = false
            })
        }

        return rootView
    }

    private fun initArticlesRecycle(rootView: View) {
        articleAdapter = ArticleAdapter(arrayListOf(), rootView.context)
        rootView.newsRecycle.layoutManager = LinearLayoutManager(rootView.context)
        rootView.newsRecycle.setHasFixedSize(true)
        rootView.newsRecycle.adapter = articleAdapter

    }




}
