package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danilkharytonov.retrofitroomrepository.R
import com.danilkharytonov.retrofitroomrepository.databinding.FragmentUserListBinding
import com.danilkharytonov.retrofitroomrepository.presentation.activity.MainActivity.Companion.USER_ID
import com.danilkharytonov.retrofitroomrepository.presentation.base.BaseFragmentVBVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : BaseFragmentVBVM<FragmentUserListBinding, UserListViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = UserListAdapter({ user ->
            findNavController().navigate(
                R.id.action_userListFragment_to_userDetailFragment,
                bundleOf(USER_ID to user.login.uuid)
            )
        }, onListEnd = {
            viewModel.fetchUsersToEnd()
        }, onListStart = {
            viewModel.fetchUserToStart()
        })
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
}