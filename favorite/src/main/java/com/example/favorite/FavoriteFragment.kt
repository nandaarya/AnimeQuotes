package com.example.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.R
import com.example.animequotes.ui.detail.DetailFragment
import com.example.core.ui.ListQuoteAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteModule)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listQuoteAdapter = ListQuoteAdapter{ quote ->
            val bundle = bundleOf(DetailFragment.QUOTE_DATA_KEY to quote)
            findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }

        favoriteViewModel.favoritesQuotes.observe(viewLifecycleOwner) { data ->
            listQuoteAdapter.submitList(data)
            binding?.apply {
                ivEmpty.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
                tvEmpty.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
                loadingBar.visibility = View.GONE
                rvQuotes.visibility = View.VISIBLE
            }
        }

        with(binding?.rvQuotes) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = listQuoteAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
