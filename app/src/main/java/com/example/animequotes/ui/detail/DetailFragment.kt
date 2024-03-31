package com.example.animequotes.ui.detail

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.R
import com.example.animequotes.databinding.FragmentDetailBinding
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.Quote
import com.example.core.ui.ListQuoteAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class DetailFragment : Fragment(), TextToSpeech.OnInitListener {

    private val detailViewModel: DetailViewModel by viewModel()

    private var _binding: FragmentDetailBinding? = null
    private var tts: TextToSpeech? = null

    private val binding get() = _binding

    companion object {
        const val QUOTE_DATA_KEY = "quoteData"
    }

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

        val quote: Quote? = arguments?.getParcelable(QUOTE_DATA_KEY)
        setQuoteData(quote)
        setButton(quote)
        setRecyclerView(quote)
    }

    private fun setQuoteData(quote: Quote?) {
        quote?.let {
            val quoteText = context?.getString(com.example.core.R.string.quote, it.quote)
            val characterName = it.character
            val formattedString = getString(R.string.another_quotes, characterName)
            binding?.apply {
                tvQuote.text = quoteText
                tvCharacterName.text = it.character
                tvAnimeName.text = it.anime
                tvAnotherQuotesLabel.text = formattedString
            }
        }
    }

    private fun setButton(quote: Quote?) {
        quote?.let {
            detailViewModel.checkFavoriteStatus(it.id).observe(viewLifecycleOwner) { favorite ->
                setStatusFavorite(favorite)
                binding?.btnFavorite?.setOnClickListener {
                    if (!favorite) detailViewModel.saveFavoriteQuote(quote)
                    else detailViewModel.deleteFavoriteQuote(quote)
                }
            }

            binding?.btnShare?.setOnClickListener {
                val quoteText = quote.quote
                val characterName = quote.character
                val animeTitle = quote.anime
                val shareText = "\"$quoteText\"\n\n~ $characterName | $animeTitle"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT, shareText
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            binding?.btnTextToSpeech?.isEnabled = false
            tts = TextToSpeech(requireContext().applicationContext, this)
            binding?.btnTextToSpeech?.setOnClickListener {
                val text = quote.quote
                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
            } else {
                binding?.btnTextToSpeech?.isEnabled = true
            }
        }
    }

    private fun setRecyclerView(quote: Quote?) {
        val listQuoteAdapter = ListQuoteAdapter { quoteData ->
            val bundle = bundleOf("quoteData" to quoteData)
            findNavController().navigate(R.id.action_detailFragment_self, bundle)
        }

        quote?.let {
            detailViewModel.getQuotesByCharacter(it.character)
                .observe(viewLifecycleOwner) { quotes ->
                    if (quotes != null) {
                        when (quotes) {
                            is ApiResponse.Loading -> binding?.loadingBar?.visibility = View.VISIBLE
                            is ApiResponse.Empty -> {
                                binding?.apply {
                                    loadingBar.visibility = View.GONE
                                    rvQuotes.visibility = View.GONE
                                }
                            }

                            is ApiResponse.Success -> {
                                binding?.apply {
                                    loadingBar.visibility = View.GONE
                                    rvQuotes.visibility = View.VISIBLE
                                }
                                listQuoteAdapter.submitList(quotes.data)
                            }

                            is ApiResponse.Error -> {
                                binding?.apply {
                                    loadingBar.visibility = View.GONE
                                    rvQuotes.visibility = View.GONE
                                }
                            }
                        }
                    }
                }
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        with(binding?.rvQuotes) {
            this?.layoutManager = layoutManager
            this?.adapter = listQuoteAdapter
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding?.btnFavorite?.setIconResource(R.drawable.ic_favorite_filled_24)
        } else {
            binding?.btnFavorite?.setIconResource(R.drawable.ic_favorite_border_24)
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

    override fun onStop() {
        super.onStop()
        if (tts != null) {
            tts!!.stop()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        _binding = null
    }
}