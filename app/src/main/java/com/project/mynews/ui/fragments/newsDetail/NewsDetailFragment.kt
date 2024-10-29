package com.project.mynews.ui.fragments.newsDetail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.project.mynews.data.model.NewsItem
import com.project.mynews.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!
    private var newsItem: NewsItem? = null

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        arguments?.let {
            newsItem = it.getParcelable(ARG_ITEM_ID, NewsItem::class.java)
        }

        newsItem.let {
            binding.date.text = it?.publishedAt
            binding.title.text = it?.title
            binding.author.text = it?.author
            binding.content.text = it?.content
            binding.imageContainer.background = it?.image?.toDrawable(requireContext().resources)
        }

        return root
    }

}