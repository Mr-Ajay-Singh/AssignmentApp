package com.atmana.assignmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.atmana.assignmentapp.NetworkData.*
import com.atmana.assignmentapp.RetrofitFiles.DataService
import com.atmana.assignmentapp.databinding.FragmentRemoteBinding
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RemoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RemoteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentRemoteBinding
    lateinit var viewModel: RemoteViewModel

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_remote, container, false)
        val services = DataService()
        val repository = Repository(services)
        val factory = RemoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(RemoteViewModel::class.java)
        val mAdapter = RemoteRecyclerViewAdapter()
        val footer = MyLoadStateAdapter{mAdapter.retry()}

        binding.remoteRecyclerview.apply {
            adapter = mAdapter.withLoadStateFooter(footer)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }

        lifecycleScope.launch{
            viewModel.getAllData().collect{
                mAdapter.submitData(it)
            }
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RemoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RemoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}