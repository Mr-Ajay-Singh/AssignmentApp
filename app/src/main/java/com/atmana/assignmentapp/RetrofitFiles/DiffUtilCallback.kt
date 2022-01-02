package com.atmana.assignmentapp.RetrofitFiles

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }
}