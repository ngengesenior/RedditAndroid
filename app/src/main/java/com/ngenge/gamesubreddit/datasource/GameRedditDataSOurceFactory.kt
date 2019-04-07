package com.ngenge.gamesubreddit.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ngenge.gamesubreddit.api.GameSubredditApi
import com.ngenge.gamesubreddit.model.RedditPostItem

class GameRedditDataSOurceFactory(val gameSubredditApi: GameSubredditApi): DataSource.Factory<String,RedditPostItem>()
{

    val postsItemDataSource = MutableLiveData<RedditItemKeyedDataSource>()

    override fun create(): DataSource<String, RedditPostItem> {
        val redditItemDataSource = RedditItemKeyedDataSource(gameSubredditApi)
        postsItemDataSource.postValue(redditItemDataSource)
        return redditItemDataSource
    }

}