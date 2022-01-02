package com.atmana.assignmentapp.NetworkData

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atmana.assignmentapp.R
import com.atmana.assignmentapp.RetrofitFiles.Data
import com.atmana.assignmentapp.RetrofitFiles.DiffUtilCallback
import com.atmana.assignmentapp.databinding.ListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RemoteRecyclerViewAdapter(): PagingDataAdapter<Data, RemoteRecyclerViewAdapter.MyViewHolder>(
    DiffUtilCallback()
) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        Log.i("MYTAG",position.toString())
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemBinding>(inflator,
            R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Data){
            //Log.i("MYTAG",data.email+" "+data.first_name)
            binding.imageLayout.visibility = View.VISIBLE
            Glide.with(binding.root.context).load(data.avatar).apply(RequestOptions.circleCropTransform()).into(binding.imageLayout)
            binding.textEmail.text = data.email
            binding.textName.text = data.first_name + " " + data.last_name
        }
    }
}