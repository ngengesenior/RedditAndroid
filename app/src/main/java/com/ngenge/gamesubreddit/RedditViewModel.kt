package com.ngenge.gamesubreddit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ngenge.gamesubreddit.api.GameSubredditApi
import com.ngenge.gamesubreddit.datasource.GameRedditDataSOurceFactory
import com.ngenge.gamesubreddit.model.RedditPostItem

class RedditViewModel:ViewModel()
{

    var redditPostsLiveData:LiveData<PagedList<RedditPostItem>>
    val pageSize = 25
    val gameRedditDataSOurceFactory:GameRedditDataSOurceFactory

    init {
        gameRedditDataSOurceFactory = GameRedditDataSOurceFactory(GameSubredditApi.create())
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setPrefetchDistance(pageSize*2)
            .setEnablePlaceholders(true)
            .build()

        redditPostsLiveData = LivePagedListBuilder<String,RedditPostItem>(gameRedditDataSOurceFactory,config).build()
    }

    fun getRedditLiveData() = redditPostsLiveData

    fun refresh()
    {
        gameRedditDataSOurceFactory.postsItemDataSource.value?.invalidate()
    }
}