package com.atmana.assignmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserData
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserDataDatabase
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserRepository
import com.atmana.assignmentapp.databinding.FragmentDialogBinding
import com.practice.roomdatabasepractice.DialogFragmentModel
import com.practice.roomdatabasepractice.DialogFragmentModelFactory
import com.practice.roomdatabasepractice.LocalDatabaseFragmentModel
import com.practice.roomdatabasepractice.LocalDatabaseFragmentModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentDialogBinding
    lateinit var viewModel: DialogFragmentModel
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog, container, false)
        val userDataDao = UserDataDatabase.getInstance(requireContext()).userDataDAO
        val repository = UserRepository(userDataDao)
        val factory = DialogFragmentModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DialogFragmentModel::class.java)
        val id = arguments?.getInt("id")
        val first_name = arguments?.getString("first_name")
        val last_name = arguments?.getString("last_name")
        val user_email = arguments?.getString("user_email")
        if(id!=null) {
            binding.firstNameEditText.setText(first_name)
            binding.lastNameEditText.setText(last_name)
            binding.emailEditText.setText(user_email)
        }
        binding.submitText.setOnClickListener {
            if (binding.firstNameEditText.text != null || binding.lastNameEditText.text != null || binding.emailEditText.text != null) {
                val firstName = binding.firstNameEditText.text.toString()
                val lastName = binding.lastNameEditText.text.toString()
                val email = binding.emailEditText.text.toString()
                if(id==null)
                    viewModel.insert(UserData(0, firstName, lastName, email))
                else
                    viewModel.update(UserData(id,firstName,lastName,email))
                it.findNavController().navigate(R.id.action_dialogFragment_to_localDatabase)
            }else{
                Toast.makeText(context,"Enter All Data First",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}