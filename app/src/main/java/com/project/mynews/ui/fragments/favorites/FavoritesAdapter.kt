package com.project.mynews.ui.fragments.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.project.mynews.R
import com.project.mynews.data.model.NewsItem

class FavoritesAdapter(
    private val context: Context,
    private val newsList: List<NewsItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<FavoritesAdapter.NewsViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(newsItem: NewsItem)
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.title)
        private val descriptionTextView: TextView = view.findViewById(R.id.description)
        private val authorTextView: TextView = view.findViewById(R.id.author)
        private val mainFrame: FrameLayout = view.findViewById(R.id.mainFrame)

        fun bind(newsItem: NewsItem) {
            titleTextView.text = newsItem.title
            descriptionTextView.text = newsItem.description
            authorTextView.text = newsItem.author
            mainFrame.setBackgroundDrawable(newsItem.image?.toDrawable(context.resources))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(newsList[position])
        }
    }

    override fun getItemCount(): Int = newsList.size
}
