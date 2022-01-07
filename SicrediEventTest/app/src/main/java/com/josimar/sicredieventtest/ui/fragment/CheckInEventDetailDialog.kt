package com.josimar.sicredieventtest.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.josimar.sicredieventtest.R
import com.josimar.sicredieventtest.databinding.LyCheckInDetailEventDialogBinding
import com.josimar.sicredieventtest.model.CheckIn
import com.josimar.sicredieventtest.model.ResponeCheckIn
import com.josimar.sicredieventtest.repository.Resource
import com.josimar.sicredieventtest.ui.viewmodel.CheckInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInEventDetailDialog: BottomSheetDialogFragment() {

    private lateinit var viewBinding: LyCheckInDetailEventDialogBinding

    private val viewModel: CheckInViewModel by viewModel()
    private val arguments by navArgs<CheckInEventDetailDialogArgs>()
    private val eventDetailId by lazy { arguments.eventDetailId }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextThemeWrapper = ContextThemeWrapper(requireContext(),
            R.style.ThemeOverlay_AppCompat_Dark)
        viewBinding = LyCheckInDetailEventDialogBinding
            .inflate(inflater.cloneInContext(contextThemeWrapper), container, false)

        viewBinding.lifecycleOwner = requireActivity()
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {

        viewBinding.btnConfirmCheckInDetailEvent.setOnClickListener {
            confirmCheckInEventDetail()
        }

        viewBinding.btnCancelCheckInDetailEvent.setOnClickListener {
            dismiss()
        }
    }

    private fun confirmCheckInEventDetail() {
        if (validateFields()) {
            viewModel.setCheckIn(CheckIn(
                    eventDetailId.toString(),
                    viewBinding.txtNameCheckInEvent.text.toString(),
                    viewBinding.txtEmailCheckInEvent.text.toString()
            )).observe(requireActivity(), ::responseCheckIn)
        }
    }

    private fun responseCheckIn(resource: Resource<ResponeCheckIn>?) {
        resource?.let {
            it.date?.let {
                viewModel.isSuccess.value = true
                Handler(Looper.getMainLooper()).postDelayed({
                    dismiss()
                }, 2000)
            }
            it.error?.let {
                Toast.makeText(requireContext(), "Erro ao salvar!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateFields(): Boolean {

        val name = viewBinding.txtNameCheckInEvent.text.toString()
        val email = viewBinding.txtEmailCheckInEvent.text.toString()

        if (name.isEmpty()) viewBinding.labelNameCheckInEvent.error = "Informe o nome!"
        if (email.isEmpty()) viewBinding.labelEmailCheckInEvent.error = "Informe o Email!"

        return name.isNotEmpty() && email.isNotEmpty()
    }

}