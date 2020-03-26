package com.anesabml.hospital.appointments.ui.details

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.anesabml.hospital.appointments.R
import com.anesabml.hospital.appointments.databinding.FragmentAppointmentDetailsBinding
import com.anesabml.hospital.appointments.di.AppointmentsComponentProvider
import com.anesabml.hospital.core.extensions.hide
import com.anesabml.hospital.core.extensions.show
import com.anesabml.hospital.core.utils.Result
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform
import javax.inject.Inject

class AppointmentDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AppointmentDetailsFragment()
    }

    private lateinit var binding: FragmentAppointmentDetailsBinding

    @Inject
    lateinit var viewModel: AppointmentDetailsViewModel
    private val args: AppointmentDetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val appointmentsComponent = (context.applicationContext as AppointmentsComponentProvider)
            .provideAppointmentsComponent()

        appointmentsComponent.inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform(requireContext()).apply {
            duration = resources.getInteger(R.integer.animation_duration).toLong()
        }

        // Provide the arguments
        viewModel.setup(args)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppointmentDetailsBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardView.transitionName = args.appointment.id.toString()

        viewModel.appointment().observe(viewLifecycleOwner) {
            binding.appointment = it
        }

        viewModel.serverResponse().observe(viewLifecycleOwner) {
            when (it) {
                Result.Loading -> {
                    binding.progressBar.show()
                }
                is Result.Success -> {
                    binding.progressBar.hide()
                    Snackbar.make(
                        binding.root,
                        R.string.success_canceling_appointment,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Result.Error -> {
                    binding.progressBar.hide()
                    Snackbar.make(
                        binding.root,
                        R.string.error_canceling_appointment,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_appointment_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cancel_appointment -> {
                viewModel.cancelAppointment()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
