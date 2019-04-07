package com.ngenge.gamesubreddit.model

import com.google.gson.annotations.SerializedName

/**
 * This holds a single post item on Reddit
 */
data class RedditPostItem(@SerializedName("title")  val title:String,
                          @SerializedName("name") val name:String,
                          @SerializedName("permalink") var permalink:String,
                          @SerializedName("subreddit_name_prefixed") val subreddit_name_prefixed:String,
                          @SerializedName("score") val score:Int)