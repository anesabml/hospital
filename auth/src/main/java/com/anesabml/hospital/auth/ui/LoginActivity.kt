package com.anesabml.hospital.auth.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.anesabml.hospital.auth.R
import com.anesabml.hospital.auth.databinding.ActivityLoginBinding
import com.anesabml.hospital.auth.di.LoginComponent
import com.anesabml.hospital.auth.di.LoginComponentProvider
import com.anesabml.hospital.core.extensions.hide
import com.anesabml.hospital.core.extensions.show
import com.anesabml.hospital.core.extensions.showSnackBar
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.EventObserver
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.core.utils.Validator
import javax.inject.Inject

/**
 * Fragment representing the login screen for Hospital.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var loginComponent: LoginComponent

    private lateinit var mBinding: ActivityLoginBinding

    @Inject
    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        // Creation of the login graph using the application graph
        loginComponent =
            (application as LoginComponentProvider).provideLoginComponent()

        // Make Dagger instantiate @Inject fields in LoginFragment
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // Clear the error once more than 8 characters are typed for the username input.
        mBinding.usernameEditText.setOnKeyListener { _, _, _ ->
            if (!mBinding.usernameEditText.text.isNullOrEmpty()) {
                mBinding.usernameTextInput.error = null //Clear the error
            }
            false
        }

        // Clear the error once more than 8 characters are typed for the password input.
        mBinding.passwordEditText.setOnKeyListener { _, _, _ ->
            if (Validator.isPasswordValid(mBinding.passwordEditText.text.toString())) {
                mBinding.passwordTextInput.error = null //Clear the error
            }
            false
        }

        mBinding.apply {
            viewModel = mViewModel
        }

        mViewModel.usernameInputErrorEvent.observe(this, EventObserver {
            mBinding.usernameTextInput.error = getString(R.string.error_username)
        })

        mViewModel.passwordInputErrorEvent.observe(this, EventObserver {
            mBinding.passwordTextInput.error = getString(R.string.error_password)
        })

        mViewModel.result.observe(this, EventObserver {
            updateState(it)
        })
    }


    private fun updateState(result: Result<Patient?>) {
        when (result) {
            Result.Loading -> {
                mBinding.progressBar.show()
            }
            is Result.Success -> {
                mBinding.progressBar.hide()
                with(result.data) {
                    if (this != null) {
                        navigateToBackWithResult(this)
                    } else {
                        mBinding.root.showSnackBar(R.string.error_login_user_not_found)
                    }
                }
            }
            is Result.Error -> {
                mBinding.progressBar.hide()
                mBinding.root.showSnackBar(R.string.error_login)
            }
        }
    }

    private fun navigateToBackWithResult(patient: Patient) {
        val intent = Intent()
        intent.putExtra("patient", patient)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
