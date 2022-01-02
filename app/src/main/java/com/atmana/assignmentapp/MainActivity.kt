package com.atmana.assignmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.atmana.assignmentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.buttonDatabase.setOnClickListener{
            binding.buttonDatabase.isClickable = false
            binding.buttonRemote.isClickable = true

            this.findNavController(R.id.main_fragment_container).navigate(R.id.action_remoteFragment_to_localDatabase)
        }
        binding.buttonRemote.setOnClickListener{
            if(!binding.buttonDatabase.isClickable)
            this.findNavController(R.id.main_fragment_container).navigate(R.id.action_localDatabase_to_remoteFragment)
            binding.buttonDatabase.isClickable = true
            binding.buttonRemote.isClickable = false
        }
    }
}