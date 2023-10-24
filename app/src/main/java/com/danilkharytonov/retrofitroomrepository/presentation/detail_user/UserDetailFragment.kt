package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.danilkharytonov.retrofitroomrepository.R
import com.danilkharytonov.retrofitroomrepository.app.App
import com.danilkharytonov.retrofitroomrepository.databinding.FragmentDetailUserBinding
import com.danilkharytonov.retrofitroomrepository.presentation.activity.MainActivity.Companion.USER_ID
import com.danilkharytonov.retrofitroomrepository.presentation.base.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailFragment : BaseFragment<FragmentDetailUserBinding>() {

    @Inject
    lateinit var viewModelFactory: UserDetailViewModelFactory
    private lateinit var viewModel: UserDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as App).appComponent.injectUserDetailFragment(this)
        initVM()
        initUI()
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initVM() {
        viewModel = ViewModelProvider(this, viewModelFactory)[UserDetailViewModel::class.java]
        val userId = arguments?.getString(USER_ID) ?: "0"
        viewModel.getUserById(userId)
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