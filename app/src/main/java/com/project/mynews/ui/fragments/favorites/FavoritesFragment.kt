package com.project.mynews.ui.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mynews.R
import com.project.mynews.data.model.NewsItem
import com.project.mynews.databinding.FragmentFavoritesBinding
import com.project.mynews.ui.fragments.home.HomeAdapter
import com.project.mynews.ui.fragments.newsDetail.NewsDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment(), FavoritesAdapter.OnItemClickListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoritesAdapter: FavoritesAdapter
    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        initContent()

        return binding.root
    }

    private fun initContent() {
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch(Dispatchers.Main) {
            favoritesAdapter = FavoritesAdapter(requireContext().applicationContext, viewModel.getAllNews(), this@FavoritesFragment)
            binding.favoritesRecyclerView.adapter = favoritesAdapter
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