package com.atmana.assignmentapp.NetworkData

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.atmana.assignmentapp.R
import com.atmana.assignmentapp.databinding.StateFooterBinding

class MyLoadStateAdapter (private val retry:()->Unit) : LoadStateAdapter<FooterViewHolder>() {
    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        val binding = DataBindingUtil.inflate<StateFooterBinding>(LayoutInflater.from(parent.context),
            R.layout.state_footer,parent,false)
        return FooterViewHolder(binding,retry)
    }
}