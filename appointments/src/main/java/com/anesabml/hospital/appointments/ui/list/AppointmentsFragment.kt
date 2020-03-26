package com.anesabml.hospital.appointments.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ListAdapter
import com.anesabml.hospital.appointments.R
import com.anesabml.hospital.appointments.databinding.FragmentAppointmentsBinding
import com.anesabml.hospital.appointments.databinding.ItemAppointmentsBinding
import com.anesabml.hospital.appointments.di.AppointmentsComponentProvider
import com.anesabml.hospital.core.extensions.hide
import com.anesabml.hospital.core.extensions.show
import com.anesabml.hospital.core.extensions.verticalLayoutManager
import com.anesabml.hospital.core.model.Appointment
import com.anesabml.hospital.core.utils.BindingViewHolder
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.core.utils.listAdapterOf
import com.anesabml.hospital.core.utils.viewHolderFrom
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class AppointmentsFragment : Fragment() {

    companion object {
        fun newInstance() =
            AppointmentsFragment()
    }

    private lateinit var binding: FragmentAppointmentsBinding
    private lateinit var mAdapter: ListAdapter<Appointment, BindingViewHolder<ItemAppointmentsBinding>>
    private var BindingViewHolder<ItemAppointmentsBinding>.appointment by BindingViewHolder.Prop<Appointment>()

    @Inject
    lateinit var viewModel: AppointmentsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val appointmentsComponent =
            (context.applicationContext as AppointmentsComponentProvider).provideAppointmentsComponent()

        appointmentsComponent.inject(this)

        // Provide the arguments
        val args: AppointmentsFragmentArgs by navArgs()
        viewModel.setup(args)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppointmentsBinding.inflate(inflater, container, false)

        mAdapter = listAdapterOf(
            initialItems = listOf(),
            viewHolderCreator = { parent, _ ->
                parent.viewHolderFrom(ItemAppointmentsBinding::inflate).apply {
                    binding.root.setOnClickListener {
                        val extras = FragmentNavigatorExtras(itemView to appointment.id.toString())
                        val action =
                            AppointmentsFragmentDirections.appointmentsFragmentToDetailsFragment(
                                appointment
                            )
                        findNavController().navigate(action, extras)
                    }
                }
            },
            viewHolderBinder = { holder, item, position ->
                holder.run {
                    this.appointment = item
                    with(holder.binding) {
                        appointment = item
                        if (position % 2 == 0) {
                            leftBar.rotation = 180f
                        }
                        root.transitionName = item.id.toString()
                    }
                }
            }
        )


        with (binding.recyclerView) {
            layoutManager = verticalLayoutManager()
            adapter = mAdapter
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.appointmentsItems.observe(viewLifecycleOwner) {
            when (it) {
                Result.Loading -> {
                    binding.progressBar.show()
                }
                is Result.Success -> {
                    binding.progressBar.hide()
                    // Update data
                    mAdapter.submitList(it.data)
                }
                is Result.Error -> {
                    binding.progressBar.hide()
                    Snackbar.make(
                        binding.root,
                        R.string.error_loading_appointments,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
