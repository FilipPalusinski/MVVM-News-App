package com.androiddevs.mvvmnewsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.ItemArticlePreviewBinding
import com.androiddevs.mvvmnewsapp.models.Article
import com.bumptech.glide.Glide

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {



    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticlePreviewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        with(holder) {
            with(differ.currentList[position]) {
                Glide.with(holder.itemView.context).load(urlToImage).into(binding.ivArticleImage)
                binding.tvSource.text = source.name
                binding.tvTitle.text = title
                binding.tvDescription.text = description
                binding.tvPublishedAt.text = publishedAt
                onItemClickListener?.let{ it(this) } }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

}