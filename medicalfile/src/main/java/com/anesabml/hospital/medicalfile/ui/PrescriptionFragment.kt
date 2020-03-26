package com.anesabml.hospital.medicalfile.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ListAdapter
import com.anesabml.hospital.core.extensions.hide
import com.anesabml.hospital.core.extensions.show
import com.anesabml.hospital.core.extensions.showSnackBar
import com.anesabml.hospital.core.extensions.verticalLayoutManager
import com.anesabml.hospital.core.model.Report
import com.anesabml.hospital.core.utils.BindingViewHolder
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.core.utils.listAdapterOf
import com.anesabml.hospital.core.utils.viewHolderFrom
import com.anesabml.hospital.medicalfile.R
import com.anesabml.hospital.medicalfile.databinding.FragmentPrescriptionsBinding
import com.anesabml.hospital.medicalfile.databinding.ItemPrescriptionBinding
import com.anesabml.hospital.medicalfile.di.MedicalFileComponentProvider
import javax.inject.Inject

class PrescriptionFragment : Fragment() {

    companion object {
        fun newInstance() = PrescriptionFragment()
    }

    private lateinit var binding: FragmentPrescriptionsBinding

    @Inject
    lateinit var viewModel: PrescriptionViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val medicalFileComponent =
            (context.applicationContext as MedicalFileComponentProvider).provideMedicalFileComponent()

        medicalFileComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Provide the arguments
        val args: PrescriptionFragmentArgs by navArgs()
        viewModel.setup(args)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrescriptionsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            updateState(it)
        }
    }

    private fun updateState(result: Result<Report>) {
        when (result) {
            Result.Loading -> {
                binding.progressBar.show()
                binding.prescriptionCard.hide()
            }
            is Result.Success -> {
                binding.progressBar.hide()
                binding.prescriptionCard.show()
                updateUi(result.data)
            }
            is Result.Error -> {
                binding.progressBar.hide()
                binding.prescriptionCard.show()
                binding.root.showSnackBar(R.string.error_loading_medical_file)
            }
        }
    }

    private fun updateUi(report: Report) {
        binding.report = report
        val drugsList = report.content.split("\n")
        val drugAdapter: ListAdapter<String, BindingViewHolder<ItemPrescriptionBinding>> =
            listAdapterOf(
                drugsList,
                viewHolderCreator = { parent, _ ->
                    parent.viewHolderFrom(
                        ItemPrescriptionBinding::inflate
                    )
                },
                viewHolderBinder = { holder, item, _ ->
                    holder.run { binding.drugName = item }
                })

        with(binding.drugsList) {
            layoutManager = verticalLayoutManager()
            adapter = drugAdapter
        }
    }
}
