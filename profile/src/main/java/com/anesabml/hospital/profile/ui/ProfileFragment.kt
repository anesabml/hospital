package com.anesabml.hospital.profile.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.anesabml.hospital.core.extensions.hide
import com.anesabml.hospital.core.extensions.show
import com.anesabml.hospital.core.extensions.showSnackBar
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.profile.R
import com.anesabml.hospital.profile.databinding.FragmentProfileBinding
import com.anesabml.hospital.profile.di.ProfileComponentProvider
import javax.inject.Inject

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var _viewModel: ProfileViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val profileComponent =
            (context.applicationContext as ProfileComponentProvider).provideProfileComponent()

        profileComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: ProfileFragmentArgs by navArgs()
        _viewModel.setup(args)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        binding.fabActionSave.setOnClickListener {
            attemptSaveInformation()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel = _viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        _viewModel.state.observe(viewLifecycleOwner) {
            updateState(it)
        }

    }

    private fun updateState(state: Result<Patient>) {
        when (state) {
            Result.Loading -> {
                binding.progressView.show()
            }
            is Result.Success -> {
                binding.progressView.hide()
                binding.root.showSnackBar(R.string.success_saving_new_info)
            }
            is Result.Error -> {
                binding.progressView.hide()
                binding.root.showSnackBar(R.string.error_try_again)
            }
        }
    }

    private fun attemptSaveInformation() {
        val emailStr = binding.email.text.toString()
        val phoneStr = binding.phone.text.toString()

        var cancel = false
        var focusView: View? = null

        if (TextUtils.isEmpty(emailStr)) {
            binding.emailLayout.error = getString(R.string.error_field_required)
            focusView = binding.email
            cancel = true
        }

        if (TextUtils.isEmpty(phoneStr)) {
            binding.phoneLayout.error = getString(R.string.error_field_required)
            focusView = binding.phone
            cancel = true
        }

        if (cancel) {
            focusView?.requestFocus()
        } else {
            _viewModel.save()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_edit) {
            enableEdit()
            return false
        }
        return super.onOptionsItemSelected(item)
    }

    private fun enableEdit() {
        with(binding) {
            emailLayout.isEnabled = true
            phoneLayout.isEnabled = true
            fabActionSave.visibility = View.VISIBLE
        }
    }
}