package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.danilkharytonov.retrofitroomrepository.R
import com.danilkharytonov.retrofitroomrepository.databinding.FragmentDetailUserBinding
import com.danilkharytonov.retrofitroomrepository.presentation.activity.MainActivity.Companion.USER_ID
import com.danilkharytonov.retrofitroomrepository.presentation.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class UserDetailFragment : BaseFragment<FragmentDetailUserBinding>() {

    private val viewModel: UserDetailViewModel by viewModel {
        parametersOf(
            arguments?.getString(
                USER_ID
            ) ?: "0"
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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