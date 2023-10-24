package com.danilkharytonov.retrofitroomrepository.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import java.lang.reflect.ParameterizedType


abstract class BaseFragment<VB : ViewBinding, VM: ViewModel>(
) : Fragment() {
    private var _binding: VB? = null
    protected val binding
        get() = requireNotNull(_binding) { "need init binding after onCreateView and before onViewCreated" }

    protected val viewModel: VM by lazy {
        getMyViewModel()
    }

    private fun getMyViewModel(): VM {
        val viewModelClass = getViewModelClass()
        return ViewModelProvider(
            owner = this,
            factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return getKoin().get(viewModelClass.kotlin, qualifier = null, parameters = arguments?.let {
                        {
                            parametersOf(it)
                        }
                    }) as T
                }
            }
        )[viewModelClass]
    }

    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindingClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VB>
        val inflate = bindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        _binding = inflate.invoke(null, inflater, container, false) as VB
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}