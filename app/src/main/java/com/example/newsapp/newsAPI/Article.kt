package com.example.newsapp.newsAPI

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val imgUrl: String,
    @SerializedName("publishedAt") val date: String
)
