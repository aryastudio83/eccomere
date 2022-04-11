package com.aryastudio83.ecommerce.ui.auth

import android.view.View
import com.aryastudio83.ecommerce.EMAIL_ERROR_TEXT
import com.aryastudio83.ecommerce.MOB_ERROR_TEXT
import com.aryastudio83.ecommerce.databinding.FragmentSignupBinding
import com.aryastudio83.ecommerce.ui.SignUpViewErrors

class SignupFragment : AuthBaseFragment<FragmentSignupBinding>() {
    override fun setViewBinding(): FragmentSignupBinding {
        return FragmentSignupBinding.inflate(layoutInflater)
    }

    override fun observeView() {
        super.observeView()
        viewModel.errorStatus.observe(viewLifecycleOwner) {err -> modifyError(err)}
    }

    private fun modifyError(err: SignUpViewErrors) {
        when (err) {
            SignUpViewErrors.NONE -> setEditTextsError()
            SignUpViewErrors.ERR_EMAIL -> setEditTextsError(emailError = EMAIL_ERROR_TEXT)
            SignUpViewErrors.ERR_MOBILE -> setEditTextsError(mobError = MOB_ERROR_TEXT)
            SignUpViewErrors.ERR_EMAIL_MOBILE -> setEditTextsError(EMAIL_ERROR_TEXT, MOB_ERROR_TEXT)
            SignUpViewErrors.ERR_EMPTY -> setErrorText("Fill all details.")
            SignUpViewErrors.ERR_NOT_ACC -> setErrorText("Accept the Terms.")
            SignUpViewErrors.ERR_PWD12NS -> setErrorText("Both passwords are not same!")
        }

    }
    private fun setErrorText(errText: String?) {
        binding.signupErrorTextView.visibility = View.VISIBLE
        if (errText != null) {
            binding.signupErrorTextView.text = errText
        }
    }
    private fun setEditTextsError(emailError: String? = null, mobError: String? = null) {
        binding.signupEmailEditText.error = emailError
        binding.signupMobileEditText.error = mobError
        binding.signupErrorTextView.visibility = View.GONE
    }

    override fun setUpView() {
        super.setUpView()
        binding.signupErrorTextView.visibility = View.GONE

        binding.signupNameEditText.onFocusChangeListener = focusChangeListener
        binding.signupMobileEditText.onFocusChangeListener = focusChangeListener
        binding.signupEmailEditText.onFocusChangeListener = focusChangeListener
        binding.signupPasswordEditText.onFocusChangeListener = focusChangeListener
        binding.signupCnfPasswordEditText.onFocusChangeListener = focusChangeListener

        binding.signupSignupBtn.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                onSignUp()
            }
        })
    }

    private fun onSignUp() {
        val name = binding.signupNameEditText.text.toString()
        val mobile = binding.signupMobileEditText.text.toString()
        val email = binding.signupEmailEditText.text.toString()
        val password1 = binding.signupPasswordEditText.text.toString()
        val password2 = binding.signupCnfPasswordEditText.text.toString()
        val isAccepted = binding.signupPolicySwitch.isChecked
        val isSeller = binding.signupSellerSwitch.isChecked

        viewModel.signUpSubmitData(name, mobile, email, password1, password2, isAccepted, isSeller)
    }

}