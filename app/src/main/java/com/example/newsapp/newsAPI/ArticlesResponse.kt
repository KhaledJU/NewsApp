package com.example.newsapp.newsAPI

import com.example.newsapp.Models.Article
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val count: Int,
    @SerializedName("articles") val articles: List<Article>
)