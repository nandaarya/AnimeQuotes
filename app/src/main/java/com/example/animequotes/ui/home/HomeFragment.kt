package com.example.animequotes.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.R
import com.example.animequotes.databinding.FragmentHomeBinding
import com.example.core.data.remote.network.ApiResponse
import com.example.core.ui.ListQuoteAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBar()
        setContent()
    }

    private fun setContent() {
        if(activity != null) {
            val listQuoteAdapter = ListQuoteAdapter{ quote ->
                val bundle = bundleOf("quoteData" to quote)
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
            homeViewModel.searchResult.observe(viewLifecycleOwner) { quotes ->
                if (quotes != null) {
                    when (quotes) {
                        is ApiResponse.Loading -> binding?.loadingBar?.visibility = View.VISIBLE
                        is ApiResponse.Empty -> {
                            binding?.loadingBar?.visibility = View.GONE
                            binding?.rvQuotes?.visibility = View.GONE
                            binding?.viewEmpty?.root?.visibility = View.VISIBLE
                        }
                        is ApiResponse.Success -> {
                            binding?.loadingBar?.visibility = View.GONE
                            binding?.rvQuotes?.visibility = View.VISIBLE
                            binding?.viewError?.root?.visibility = View.GONE
                            binding?.viewEmpty?.root?.visibility = View.GONE
                            listQuoteAdapter.submitList(quotes.data)
                        }

                        is ApiResponse.Error -> {
                            binding?.loadingBar?.visibility = View.GONE
                            binding?.rvQuotes?.visibility = View.GONE
                            binding?.viewError?.root?.visibility = View.VISIBLE
                        }
                    }
                }
            }

            with(binding?.rvQuotes) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = listQuoteAdapter
            }
        }
    }

    private fun searchBar() {
        binding?.search?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                lifecycleScope.launch {
                    homeViewModel.query.value = s.toString()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}