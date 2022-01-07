package com.josimar.sicredieventtest.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.josimar.sicredieventtest.databinding.LyDetailEventBinding
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.ui.adapter.ListPeopleDetailEventAdapter
import com.josimar.sicredieventtest.ui.viewmodel.DetailEventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailEventFragment : Fragment() {

    private lateinit var viewBinding: LyDetailEventBinding
    private lateinit var listPeopleEventDetailAdapter: ListPeopleDetailEventAdapter

    private val arguments by navArgs<DetailEventFragmentArgs>()
    private val viewModel: DetailEventViewModel by viewModel()
    private val controller by lazy { findNavController() }
    private val eventId by lazy { arguments.eventId }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = LyDetailEventBinding.inflate(inflater, container, false)

        viewBinding.lyDetailEvent.mapView.onCreate(savedInstanceState)
        viewBinding.lifecycleOwner = requireActivity()
        viewBinding.viewModel = viewModel

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getEventId()
        setupObserver()
        setBtnCheckInDetailEvent()
    }

    private fun getEventId() {
        viewModel.getDetailEvent(eventId = eventId).observe(requireActivity(), { resorceDetailEvent ->
            resorceDetailEvent?.let { it ->
                viewModel.isLoading.value = false
                it.date?.let { data -> viewModel.eventDetail.value = data }
                it.error?.let { viewModel.isFail.value = true }
            }
        })
    }

    private fun setupObserver() {
        viewModel.eventDetail.observe(requireActivity(), { eventDetail ->
            eventDetail?.let {
                setMapView(eventDetail)
                setShareEvent(eventDetail)
                setAdapterPeoples(eventDetail)
            }
        })
    }

    private fun setMapView(eventDetail: Event) {
        viewBinding.lyDetailEvent.mapView.getMapAsync { googleMap ->

            val location = LatLng(eventDetail.latitude?: 0.0,
                eventDetail.longitude?: 0.0)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                location,
                10F
            ))
            googleMap.addMarker(MarkerOptions()
                .position(location).title(eventDetail.title?:""))
        }
    }

    private fun setShareEvent(eventDetail: Event) {
        viewBinding.lyDetailEvent.fabShareDetailEvent.setOnClickListener {
            val contentShare = "${eventDetail.title ?: ""} \n ${eventDetail.description ?: ""}"
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, contentShare)
            startActivity(Intent.createChooser(shareIntent, null))
        }
    }

    private fun setAdapterPeoples(eventDetail: Event) {
        eventDetail.people?.let {
            listPeopleEventDetailAdapter = ListPeopleDetailEventAdapter(it)
            viewBinding.lyDetailEvent.listCheckInDetailEventRecyclerview.adapter =
                listPeopleEventDetailAdapter
        }
    }

    private fun setBtnCheckInDetailEvent() {
        viewBinding.lyDetailEvent.btnCheckInDetailEvent.setOnClickListener {
            DetailEventFragmentDirections.actionDetailEventFragmentToCheckInEventDetailDialog(
                eventDetailId = eventId).run {
                controller.navigate(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.lyDetailEvent.mapView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.lyDetailEvent.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        viewBinding.lyDetailEvent.mapView.onLowMemory()
    }

}