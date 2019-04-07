package com.ngenge.gamesubreddit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ngenge.gamesubreddit.model.RedditPostItem
import kotlinx.android.synthetic.main.list_item_layout.view.*

class GameRedditListAdapter(val redditItemClickListener: OnRedditItemClickListener):PagedListAdapter<RedditPostItem, GameRedditListAdapter.ViewHolder>(diffUtilCallback)

{
    private  val base_url = "https://www.reddit.com"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val redditPostItem = getItem(position)

        //MAke sure you set the click listener only when the reddit post item is not null
        with(holder)
        {
            bind(redditPostItem)

            redditPostItem?.let {
                itemView.setOnClickListener {
                    redditItemClickListener.OnItemClicked(redditPostItem)
                }
            }
        }

    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {

        fun bind(redditPostItem: RedditPostItem?)
        {
            itemView.titleTextView.text = redditPostItem?.title ?:""
            itemView.scoreTextView.text = redditPostItem?.score.toString() ?:""
            itemView.subredditTextView.text = redditPostItem?.subreddit_name_prefixed ?:""

        }

    }


    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<RedditPostItem>() {
            override fun areContentsTheSame(oldItem: RedditPostItem, newItem: RedditPostItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: RedditPostItem, newItem: RedditPostItem): Boolean {
               return oldItem.name == newItem.name
            }

        }
    }




}

interface OnRedditItemClickListener{
    fun OnItemClicked(redditPostItem: RedditPostItem)
}


