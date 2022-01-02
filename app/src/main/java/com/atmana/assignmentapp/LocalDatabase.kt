package com.atmana.assignmentapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atmana.assignmentapp.LocalRoomDatabase.LocalDatabaseAdapter
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserDataDatabase
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserRepository
import com.atmana.assignmentapp.databinding.FragmentLocalDatabaseBinding
import com.practice.roomdatabasepractice.LocalDatabaseFragmentModel
import com.practice.roomdatabasepractice.LocalDatabaseFragmentModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LocalDatabase.newInstance] factory method to
 * create an instance of this fragment.
 */
class LocalDatabase : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding : FragmentLocalDatabaseBinding
     lateinit var viewModel : LocalDatabaseFragmentModel
     lateinit var mAdapter : LocalDatabaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_local_database,container,false)
        val userDataDao = UserDataDatabase.getInstance(requireContext()).userDataDAO
        val repository = UserRepository(userDataDao)
        val factory = LocalDatabaseFragmentModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(LocalDatabaseFragmentModel::class.java)
        mAdapter = LocalDatabaseAdapter {
            viewModel.delete(it)
        }
        binding.localRecyclerview.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = mAdapter
        }
        viewModel.userData.observe(viewLifecycleOwner, Observer {
            mAdapter.setList(it)
            mAdapter.notifyDataSetChanged()
        })

        binding.floatingAddButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_localDatabase_to_dialogFragment)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LocalDatabase().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}