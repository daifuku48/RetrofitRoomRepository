package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.danilkharytonov.retrofitroomrepository.R
import com.danilkharytonov.retrofitroomrepository.databinding.FragmentDetailUserBinding
import com.danilkharytonov.retrofitroomrepository.presentation.activity.MainActivity.Companion.USER_ID
import com.danilkharytonov.retrofitroomrepository.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentDetailUserBinding, UserDetailViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = arguments?.getString(USER_ID)
        if (userId != null) {
            viewModel.getUserById(userId)
        }
        initUI()
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initUI() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.user.collect { user ->
                    binding.userEmail.text = getString(R.string.email, user.email)
                    binding.userGender.text = getString(R.string.gender, user.gender)
                    binding.userFullName.text = getString(
                        R.string.full_name,
                        user.name.title,
                        user.name.firstName,
                        user.name.lastName
                    )
                    Glide.with(requireContext())
                        .load(user.picture.iconImage)
                        .into(binding.userIcon)
                }
            }
        }
    }
}