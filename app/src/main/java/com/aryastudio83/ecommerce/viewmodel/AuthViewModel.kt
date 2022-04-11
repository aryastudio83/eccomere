package com.aryastudio83.ecommerce.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aryastudio83.ecommerce.*
import com.aryastudio83.ecommerce.isEmailValid
import com.aryastudio83.ecommerce.isPhoneValid
import com.aryastudio83.ecommerce.ui.SignUpViewErrors

class AuthViewModel(application: Application): AndroidViewModel(application) {
    private val _errorStatus = MutableLiveData<SignUpViewErrors>()
    val errorStatus: LiveData<SignUpViewErrors> get() = _errorStatus

    init {
        _errorStatus.value = SignUpViewErrors.NONE
    }

    fun signUpSubmitData(name: String, mobile: String, email: String, password1: String, password2: String, accepted: Boolean, seller: Boolean) {
        if (name.isBlank() || mobile.isBlank() || email.isBlank() || password1.isBlank() || password2.isBlank() ) {
            _errorStatus.value = SignUpViewErrors.ERR_EMPTY
        } else {
            if(password1 != password2) {
                _errorStatus.value = SignUpViewErrors.ERR_PWD12NS
            } else {
                if(!accepted) {
                    _errorStatus.value = SignUpViewErrors.ERR_NOT_ACC
                } else {
                    var err = ERR_INIT
                    if (!isEmailValid(email)) {
                        err += ERR_EMAIL
                    }
                    if (!isPhoneValid(mobile)) {
                        err += ERR_MOBILE
                    }
                    when(err) {
                        ERR_INIT -> SignUpViewErrors.NONE
                        (ERR_INIT + ERR_EMAIL) -> _errorStatus.value = SignUpViewErrors.ERR_EMAIL
                        (ERR_INIT + ERR_MOBILE) -> _errorStatus.value = SignUpViewErrors.ERR_MOBILE
                        (ERR_INIT + ERR_EMAIL + ERR_MOBILE) -> _errorStatus.value =
                            SignUpViewErrors.ERR_EMAIL_MOBILE
                    }

                }
            }
        }
    }

}