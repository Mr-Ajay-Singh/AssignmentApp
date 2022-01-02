package com.atmana.assignmentapp.LocalRoomDatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserData
import com.atmana.assignmentapp.R
import com.atmana.assignmentapp.databinding.ListItemBinding

class LocalDatabaseAdapter(private val clickListener: (UserData) -> Unit) : RecyclerView.Adapter<MyViewHolder>()  {
    var userList = ArrayList<UserData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent, false)
//        return MyViewHolder(view)
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemBinding>(layoutInflater,
            R.layout.list_item,parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return userList .size
    }

    fun setList(list : List<UserData>){
        userList .clear()
        userList .addAll(list)
    }

}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(userData : UserData,clickListener: (UserData) -> Unit){
        binding.textName.text = userData.firstName + " " + userData.lastName
        binding.textEmail.text = userData.userEmail
        binding.buttonLayout.visibility = View.VISIBLE
        binding.editButton.setOnClickListener {
            val bundle : Bundle? = bundleOf("id" to userData.id,"first_name" to userData.firstName,"last_name" to userData.lastName, "user_email" to userData.userEmail)
            it.findNavController().navigate(R.id.action_localDatabase_to_dialogFragment,bundle)
        }
        binding.removeButton.setOnClickListener {
            clickListener(userData)
        }
    }
}