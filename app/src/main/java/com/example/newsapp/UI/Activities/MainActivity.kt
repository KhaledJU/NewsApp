package com.example.newsapp.UI.Activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.UI.Adapters.ArticleAdapter
import com.example.newsapp.UI.Fragments.ArticlesFragment
import com.example.newsapp.newsAPI.Article
import com.example.newsapp.newsAPI.ArticleService
import com.example.newsapp.newsAPI.ArticleServiceGenerator
import com.example.newsapp.newsAPI.ArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initIngFragment()
    }

    private fun initIngFragment() {
        val articlesFragment = ArticlesFragment()
        // Add the fragment to the 'fragment_container' FrameLayout
        supportFragmentManager.beginTransaction()
            .replace(R.id.articles_frame, articlesFragment).commit()
    }
}


