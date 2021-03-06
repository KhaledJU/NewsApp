package com.example.newsapp.UI.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.UI.viewHolders.ArticleViewHolder
import com.example.newsapp.models.Article

class ArticleAdapter(var articles: ArrayList<Article>, val context: Context) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.single_article, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles.get(position), context)
    }

    fun changeArtecles(articles: ArrayList<Article>) {
        this.articles = articles

//        this.articles.clear()
//        this.articles.addAll(articles)

        notifyDataSetChanged()
    }
}

