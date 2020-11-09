package com.practice.mynews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.mynews.R
import com.practice.mynews.databinding.ItemNewsBinding
import com.practice.mynews.model.News

class MyNewsAdapter() : RecyclerView.Adapter<MyNewsAdapter.MyNewsViewHolder>() {
    var results = emptyList<News>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNewsViewHolder {
        return MyNewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyNewsViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount() = results.size


    class MyNewsViewHolder private constructor(val newsBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(newsBinding.root) {
        companion object {

            private val LAYOUT = R.layout.item_news

            fun from(parent: ViewGroup): MyNewsViewHolder {
                return MyNewsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        LAYOUT,
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(news: News) {
            newsBinding.results = news

        }
    }
}