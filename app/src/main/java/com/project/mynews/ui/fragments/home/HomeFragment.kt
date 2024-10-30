package com.project.mynews.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.project.mynews.R
import com.project.mynews.data.model.NewsItem
import com.project.mynews.databinding.FragmentHomeBinding
import com.project.mynews.ui.fragments.newsDetail.NewsDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeAdapter: HomeAdapter
    private var latestNews = emptyList<NewsItem>()
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initContent()

        return binding.root
    }

    private fun initContent() {
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch(Dispatchers.Main) {
            latestNews = viewModel.getLatestNews()
            homeAdapter =
                HomeAdapter(requireContext().applicationContext, latestNews, this@HomeFragment)
            binding.homeRecyclerView.adapter = homeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(newsItem: NewsItem) {
        findNavController().navigate(
            R.id.newsDetailFragment,
            bundleOf(NewsDetailFragment.ARG_ITEM_ID to newsItem)
        )
    }
}