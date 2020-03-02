package com.example.newsapp.UI.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.example.newsapp.UI.fragments.ArticlesFragment


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


