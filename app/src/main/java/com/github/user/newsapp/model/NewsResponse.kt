package com.github.user.newsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class NewsResponse(
    val articles: List<Article>,
    val isSuccessful: Boolean
) {
    @Parcelize
    data class Article(
        val author: String,
        val title: String,
        val urlToImage: String?,
        val url: String,
        val description: String,
        val content: String
    ) : Parcelable
}

