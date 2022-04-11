package com.aryastudio83.ecommerce.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.aryastudio83.ecommerce.ui.MyOnFocusChangeListener
import com.aryastudio83.ecommerce.viewmodel.AuthViewModel

abstract class AuthBaseFragment<VBinding: ViewBinding>: Fragment() {
    protected val viewModel: AuthViewModel by activityViewModels()
    protected lateinit var binding: VBinding
    protected abstract fun setViewBinding(): VBinding
    protected val focusChangeListener = MyOnFocusChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpView()
        observeView()
        return binding.root
    }

    open fun observeView() {}

    open fun setUpView() {}

    private fun init() {
        binding = setViewBinding()

    }



    interface OnClickListener : View.OnClickListener
}