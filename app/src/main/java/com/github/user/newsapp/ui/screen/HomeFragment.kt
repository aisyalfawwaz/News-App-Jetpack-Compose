package com.github.user.newsapp.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.user.newsapp.DetailActivity
import com.github.user.newsapp.api.NewsApiClient
import com.github.user.newsapp.model.NewsResponse
import com.github.user.newsapp.ui.component.NewsItem
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
@Preview
fun HomeFragment() {
    val coroutineScope = rememberCoroutineScope()
    val newsList = remember { mutableStateListOf<NewsResponse.Article>() }
    var searchKeyword by remember { mutableStateOf("") }

    LaunchedEffect(searchKeyword) {
        coroutineScope.launch {
            val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val newsApi = retrofit.create(NewsApiClient::class.java)

            if (searchKeyword.isNotEmpty()) {
                val response = newsApi.searchNews(searchKeyword, "c53cc88867124e7cb8268beeece516ec")
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    newsResponse?.articles?.let {
                        newsList.clear()
                        newsList.addAll(it)
                    }
                }
            } else {
                val response = newsApi.getTopHeadlines("us", "c53cc88867124e7cb8268beeece516ec")
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    newsResponse?.articles?.let {
                        newsList.clear()
                        newsList.addAll(it)
                    }
                }
            }
        }
    }

    val context = LocalContext.current // Get the context

    ProvideWindowInsets {
        LocalWindowInsets.current.systemBars
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = searchKeyword,
                onValueChange = { searchKeyword = it },
                label = { Text(text = "Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(45.dp)),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { /* Perform search action here */ })
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(newsList) { article ->
                    NewsItem(article = article) { selectedArticle ->
                        val intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra("extra_title", selectedArticle.title)
                            putExtra("extra_author", selectedArticle.author)
                            putExtra("extra_description", selectedArticle.description)
                            putExtra("extra_image_url", selectedArticle.urlToImage)
                        }
                        launchActivity(context, intent)
                    }
                }
            }
        }
    }
}