package com.example.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.databinding.ItemListQuotesBinding
import com.example.core.domain.model.Quote

class ListQuoteAdapter(private val onClickDetails: (Quote) -> Unit) :
    ListAdapter<Quote, ListQuoteAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListQuotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListQuotesBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Quote) {
            val quoteText = context.getString(R.string.quote, data.quote)
            with(binding) {
                tvQuote.text = quoteText
                tvAnimeName.text = data.anime
                tvCharacterName.text = data.character

                itemView.setOnClickListener {
                    onClickDetails(data)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
                return oldItem == newItem
            }
        }
    }
}