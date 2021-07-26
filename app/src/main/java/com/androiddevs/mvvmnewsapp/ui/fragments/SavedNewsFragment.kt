package com.androiddevs.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.adapters.NewsAdapter
import com.androiddevs.mvvmnewsapp.databinding.FragmentSavedNewsBinding
import com.androiddevs.mvvmnewsapp.databinding.FragmentSearchNewsBinding
import com.androiddevs.mvvmnewsapp.ui.NewsActivity
import com.androiddevs.mvvmnewsapp.ui.NewsViewModel

class SavedNewsFragment: Fragment(R.layout.fragment_saved_news) {

    lateinit var  viewModel: NewsViewModel
    private lateinit var binding: FragmentSavedNewsBinding
    lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSavedNewsBinding>(
            inflater, R.layout.fragment_saved_news, container, false
        )

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply{
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }

    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.rvSavedNews.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }
}