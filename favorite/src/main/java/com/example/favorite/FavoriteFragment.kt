package com.example.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.ListQuoteAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.favoriteModule
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import com.example.animequotes.R
import com.example.core.domain.model.Quote

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

//        setAppBarShowNavigation()

        val listQuoteAdapter = ListQuoteAdapter{ quote ->
            val bundle = bundleOf("quoteData" to quote)
            findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment)
        }

        // data dummy
        listQuoteAdapter.submitList(
            listOf(
                Quote(
                    id = 1,
                    quote = "tes 1",
                    anime = "narto",
                    character = "budi"
                ),
                Quote(
                    id = 2,
                    quote = "tes 2",
                    anime = "narto",
                    character = "budi"
                ),
                Quote(
                    id = 3,
                    quote = "tes 3",
                    anime = "narto",
                    character = "budi"
                ),
                Quote(
                    id = 4,
                    quote = "tes 4",
                    anime = "narto",
                    character = "budi"
                ),
                Quote(
                    id = 5,
                    quote = "tes 5",
                    anime = "narto",
                    character = "budi"
                ),
                Quote(
                    id = 6,
                    quote = "tes 6",
                    anime = "narto",
                    character = "budi"
                ),
                Quote(
                    id = 7,
                    quote = "tes 7",
                    anime = "narto",
                    character = "budi"
                ),
                Quote(
                    id = 8,
                    quote = "tes 8",
                    anime = "narto",
                    character = "budi"
                )
            )
        )

        binding?.loadingBar?.visibility = View.GONE
        binding?.rvQuotes?.visibility = View.VISIBLE

        with(binding?.rvQuotes) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = listQuoteAdapter
        }

    }

//    private fun setAppBarShowNavigation() {
//        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.VISIBLE
//
//        (activity as AppCompatActivity).supportActionBar?.apply {
//            title = getString(R.string.app_name)
//            setDisplayHomeAsUpEnabled(false)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
