package com.project.mynews.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mynews.R
import com.project.mynews.data.model.NewsItem

class HomeAdapter(private val newsList: List<NewsItem>) :
    RecyclerView.Adapter<HomeAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.title)
        private val descriptionTextView: TextView = view.findViewById(R.id.description)
        private val authorTextView: TextView = view.findViewById(R.id.author)
        private val mainFrame: FrameLayout = view.findViewById(R.id.mainFrame)
        // Add other views as needed

        fun bind(newsItem: NewsItem) {
            titleTextView.text = newsItem.title
            descriptionTextView.text = newsItem.description
            authorTextView.text = newsItem.author
            mainFrame.setBackgroundDrawable(newsItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size
}
