package com.danilkharytonov.retrofitroomrepository.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass


abstract class BaseFragmentVBVM<VB : ViewBinding, VM : ViewModel> : BaseFragmentVB<VB>() {

    protected val viewModel: VM by createViewModelLazy(
        viewModelClass = getViewModelClass(),
        storeProducer = { this.viewModelStore }
    )

    private fun getViewModelClass(): KClass<VM> {
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>
        return viewModelClass.kotlin
    }
}

abstract class BaseFragmentVB<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding
        get() = requireNotNull(_binding) { "need init binding after onCreateView and before onViewCreated" }

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