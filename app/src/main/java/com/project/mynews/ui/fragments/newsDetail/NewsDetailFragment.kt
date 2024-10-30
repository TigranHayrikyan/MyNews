package com.project.mynews.ui.fragments.newsDetail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.project.mynews.data.model.NewsItem
import com.project.mynews.databinding.FragmentNewsDetailBinding
import com.project.mynews.ui.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!
    private var newsItem: NewsItem? = null
    private val viewModel by viewModels<NewsDetailFragmentViewModel>()

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
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        initNewsItem()
        configNewsItem()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.backBtn.setOnClickListener {
            it.findNavController().popBackStack()
            (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
        }

        binding.saveBtn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.insertNews(newsItem!!)
            }
        }
    }

    private fun configNewsItem() {
        newsItem.let {
            binding.date.text = it?.publishedAt
            binding.title.text = it?.title
            binding.author.text = it?.author
            binding.content.text = it?.content
            binding.imageContainer.background = it?.image?.toDrawable(requireContext().resources)
        }
    }

    private fun initNewsItem() {
        arguments?.let {
            newsItem = it.getParcelable(ARG_ITEM_ID, NewsItem::class.java)
        }
    }
}