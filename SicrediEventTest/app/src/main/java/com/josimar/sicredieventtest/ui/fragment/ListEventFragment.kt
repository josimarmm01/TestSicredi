package com.josimar.sicredieventtest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.josimar.sicredieventtest.databinding.LyListEventBinding
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.ui.adapter.ListEventAdapter
import com.josimar.sicredieventtest.ui.viewmodel.ListEventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListEventFragment : Fragment() {

    private lateinit var viewBinding: LyListEventBinding
    private lateinit var listEventAdapter: ListEventAdapter

    private val viewModel: ListEventViewModel by viewModel()
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = LyListEventBinding.inflate(inflater, container, false)
        viewBinding.lifecycleOwner = requireActivity()
        viewBinding.viewModel = viewModel

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getListEvent()
    }

    private fun getListEvent() {
        viewModel.getListEvent.observe(requireActivity(), { resorceListEvent ->
            resorceListEvent?.let {
                viewModel.isLoading.value = false
                it.data?.let { data ->
                    if (data.isEmpty()) viewModel.isEmpty.value = true
                    else configRecyclerView(data)
                }
                it.error?.let { viewModel.isFail.value = true }
            }
        })
    }

    private fun configRecyclerView(listEvent: List<Event>) {
        listEventAdapter = ListEventAdapter(listEvent, ::goToEventDetails)
        viewBinding.listEventRecyclerview.adapter = listEventAdapter
    }

    private fun goToEventDetails(event: Event) {
        event.id?.let {
            ListEventFragmentDirections
                .actionListEventFragmentToDetailEventFragment(eventId = it).run {
                controller.navigate(this)
            }
        }
    }

}