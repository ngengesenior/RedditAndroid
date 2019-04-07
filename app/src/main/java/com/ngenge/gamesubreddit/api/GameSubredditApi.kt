package com.ngenge.gamesubreddit.api

import com.ngenge.gamesubreddit.model.RedditPostItem
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GameSubredditApi{

    /**
     * This is the function to access to get the first page
     * @param limit The highest number of items to return
     */
    @GET("r/gaming/top.json")
    fun getHead(@Query("limit") limit:Int):Call<GameListingResponse>

    /**
     * @param limit The max number 0f items to return in response
     * @param after Used to navigate to the next page
     */
    @GET("r/gaming/top.json")
    fun getAfterHead(@Query("limit") limit: Int,
                     @Query("after") after: String):Call<GameListingResponse>

    /**
     * @param limit is the max number of items to return
     * @param before used to navigate to the previous response
     */
    @GET("r/gaming/top.json")
    fun getBeforeHead(@Query("limit") limit: Int,
                      @Query("before") before: String):Call<GameListingResponse>




    class GameListingResponse(val data:GameListingData)

    class GameListingData(val children:List<ChildrenResponse>,before:String?,after:String?)

    data class ChildrenResponse(val data:RedditPostItem)


    companion object {
        private val BASE_URL ="https://www.reddit.com/"


        fun create(httpUrl: HttpUrl):GameSubredditApi{
           val okHttpClient = OkHttpClient.Builder()
               .build()

            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(GameSubredditApi::class.java)
        }


        fun create():GameSubredditApi = create(HttpUrl.parse(BASE_URL)!!)
    }


}