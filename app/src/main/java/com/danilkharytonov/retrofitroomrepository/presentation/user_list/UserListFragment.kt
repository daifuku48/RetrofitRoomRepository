package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danilkharytonov.retrofitroomrepository.R
import com.danilkharytonov.retrofitroomrepository.databinding.FragmentUserListBinding
import com.danilkharytonov.retrofitroomrepository.presentation.activity.MainActivity.Companion.USER_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = UserListAdapter { user ->
            Log.d("uuid", user.login.uuid)
            findNavController().navigate(
                R.id.action_userListFragment_to_userDetailFragment,
                bundleOf(USER_ID to user.login.uuid)
            )
        }
        initList(adapter)
        binding.userList.adapter = adapter
        binding.userList.layoutManager = layoutManager
    }

    private fun initList(adapter: UserListAdapter) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.userList.collect { userList ->
                    adapter.submitList(userList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}