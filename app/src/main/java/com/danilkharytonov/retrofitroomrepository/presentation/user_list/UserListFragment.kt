package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import androidx.fragment.app.Fragment
import com.danilkharytonov.retrofitroomrepository.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private var _binding : FragmentUserListBinding? = null
    private val binding
        get() = _binding!!



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}