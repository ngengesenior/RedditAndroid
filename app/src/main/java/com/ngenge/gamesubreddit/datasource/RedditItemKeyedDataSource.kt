package com.ngenge.gamesubreddit.datasource

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.ngenge.gamesubreddit.api.GameSubredditApi
import com.ngenge.gamesubreddit.model.RedditPostItem
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class RedditItemKeyedDataSource(val gameSubredditApi: GameSubredditApi):ItemKeyedDataSource<String,RedditPostItem>()
{
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<RedditPostItem>) {
        val request = gameSubredditApi.getHead(limit = params.requestedLoadSize)
            try {
                val response = request.execute()
                val posts = response.body()?.data?.children?.map {it.data  }?: emptyList()
                callback.onResult(posts)
            }
            catch (ioEx:IOException){
                Log.d("IOFailure","Failed to load")

            }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<RedditPostItem>) {
       gameSubredditApi.getAfterHead(limit = params.requestedLoadSize,after = params.key)
           .enqueue(object: retrofit2.Callback<GameSubredditApi.GameListingResponse>{
               override fun onFailure(call: Call<GameSubredditApi.GameListingResponse>, t: Throwable) {

               }

               override fun onResponse(
                   call: Call<GameSubredditApi.GameListingResponse>,
                   response: Response<GameSubredditApi.GameListingResponse>
               ) {
                   if (response.isSuccessful)
                   {
                       val posts = response.body()?.data?.children?.map { it.data }?: emptyList()
                       callback.onResult(posts)

                   }
               }
           })

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<RedditPostItem>) {

    }

    override fun getKey(item: RedditPostItem): String {
        return item.name
    }

}