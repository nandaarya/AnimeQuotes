package com.example.animequotes.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.animequotes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

//    private val homeViewModel: HomeViewModel by viewModel()

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
//        searchBar()
//        setContent()
    }

//    private fun setContent() {
//        if(activity != null) {
//            val gamesAdapter = GamesAdapter{ game ->
//                val bundle = bundleOf("gameId" to game.id)
//                findNavController().navigate(R.id.action_homeFragment_to_detailsFragments, bundle)
//            }
//            homeViewModel.searchResult.observe(viewLifecycleOwner) { games ->
//                if (games != null) {
//                    when (games) {
//                        is ApiResponse.Empty -> {
//                            binding?.rvGames?.visibility = View.GONE
//                            binding?.viewError?.root?.visibility = View.VISIBLE
//                            binding?.viewError?.tvError?.text = getString(R.string.no_data)
//                        }
//                        is ApiResponse.Success -> {
//                            binding?.loadingBar?.visibility = View.GONE
//                            binding?.rvGames?.visibility = View.VISIBLE
//                            binding?.viewError?.root?.visibility = View.GONE
//                            gamesAdapter.submitList(games.data)
//                        }
//
//                        is ApiResponse.Error -> {
//                            binding?.loadingBar?.visibility = View.GONE
//                            binding?.viewError?.root?.visibility = View.VISIBLE
//                            binding?.viewError?.tvError?.text =
//                                games.errorMessage
//                        }
//                    }
//                }
//            }
//
//            with(binding?.rvGames) {
//                this?.layoutManager = LinearLayoutManager(context)
//                this?.setHasFixedSize(true)
//                this?.adapter = gamesAdapter
//            }
//        }
//    }
//
//    private fun searchBar() {
//        binding?.search?.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                lifecycleScope.launch {
//                    homeViewModel.query.value = s.toString()
//                }
//            }
//        })
//    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}