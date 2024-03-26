package com.example.animequotes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.R
import com.example.animequotes.databinding.FragmentDetailBinding
import com.example.core.domain.model.Quote
import com.example.core.ui.ListQuoteAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()

    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ScrollView? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.quote_details)
            setDisplayHomeAsUpEnabled(true)
        }

        val quote: Quote? = arguments?.getParcelable("quoteData")
        setContent(quote)
    }

    private fun setContent(quote: Quote?) {
        quote?.let {
            val quoteText = context?.getString(com.example.core.R.string.quote, it.quote)
            binding?.tvQuote?.text = quoteText
            binding?.tvCharacterName?.text = it.character
            binding?.tvAnimeName?.text = it.anime

            val characterName = it.character
            val formattedString = getString(R.string.another_quotes, characterName)
            binding?.tvAnotherQuotesLabel?.text = formattedString

            if (it.isFavorite) {
                binding?.btnFavorite?.apply {
                    setImageResource(R.drawable.ic_favorite_filled_24)
                }
            } else {
                binding?.btnFavorite?.apply {
                    setImageResource(R.drawable.ic_favorite_border_24)
                }
            }

            binding?.btnFavorite?.setOnClickListener {

            }


            binding?.btnShare?.setOnClickListener {

            }
        }

        val listQuoteAdapter = ListQuoteAdapter{ quote ->
            val bundle = bundleOf("quoteData" to quote)
            findNavController().navigate(R.id.action_detailFragment_self, bundle)
        }

//        listQuoteAdapter.submitList(quotes.data)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        with(binding?.rvQuotes) {
            this?.layoutManager = layoutManager
            this?.setHasFixedSize(true)
            this?.adapter = listQuoteAdapter
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(false)
        }
    }
}