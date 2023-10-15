package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.danilkharytonov.retrofitroomrepository.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private var _binding : FragmentUserListBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUsers()
        Log.d("negr", "negr1")
        binding.userList.layoutManager = LinearLayoutManager(requireContext())

        val adapter = UserListAdapter{

        }

        viewModel.state.observe(viewLifecycleOwner){userState ->
            Log.d("negr", "negr")
            adapter.submitList(userState.users.user)
        }

        binding.userList.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}