package com.example.animequotes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.animequotes.R
import com.example.animequotes.databinding.FragmentDetailBinding
import com.example.core.domain.model.Quote
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()

    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quote: Quote? = arguments?.getParcelable("quoteData")

        setContent(quote)
    }

    private fun setContent(quote: Quote?) {
        quote?.let {
            val quoteText = context?.getString(com.example.core.R.string.quote, it.quote)
            binding?.tvQuote?.text = quoteText
            binding?.tvCharacterName?.text = it.character
            binding?.tvAnimeName?.text = it.anime

            // Logika untuk mengatur gambar favorit
            if (it.isFavorite) {
                binding?.btnFavorite?.apply {
                    setImageResource(R.drawable.ic_favorite_filled_24)
                }
            } else {
                binding?.btnFavorite?.apply {
                    setImageResource(R.drawable.ic_favorite_border_24)
                }
            }

            // Aksi klik pada tombol favorit
            binding?.btnFavorite?.setOnClickListener {
                // Implementasikan logika untuk mengubah status favorit
                // Anda dapat memanggil fungsi pada ViewModel untuk melakukan ini
            }

            // Aksi klik pada tombol bagikan
            binding?.btnShare?.setOnClickListener {
                // Implementasikan logika untuk berbagi kutipan
            }
        }
    }
}