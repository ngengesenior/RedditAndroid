package com.ngenge.gamesubreddit

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ngenge.gamesubreddit.model.RedditPostItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: RedditViewModel
    private lateinit var gameRedditListAdapter: GameRedditListAdapter
    private val base_url = "https://www.reddit.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(RedditViewModel::class.java)

        swipeToRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        initialiseAdapter()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.swipe_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.menu_refresh ->{
                swipeToRefresh.isRefreshing = true
            }

        }
        return true
    }


    private fun initialiseAdapter()
    {
        gameRedditListAdapter = GameRedditListAdapter(object : OnRedditItemClickListener {
            override fun OnItemClicked(redditPostItem: RedditPostItem) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${base_url}${redditPostItem.permalink}"))

                try {
                    startActivity(intent)
                }
                catch (ex:ActivityNotFoundException)
                {
                    Toast.makeText(this@MainActivity,"No browser installed",Toast.LENGTH_LONG).show()
                }
            }
        })
        reditRecyclerview.adapter = gameRedditListAdapter
        viewModel.getRedditLiveData().observe(this, Observer {
            gameRedditListAdapter.submitList(it)
            swipeToRefresh.isRefreshing = false
        })

    }
}
