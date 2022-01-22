package com.josimar.sicredieventtest.di

import com.josimar.sicredieventtest.repository.EventRepository
import com.josimar.sicredieventtest.ui.viewmodel.CheckInViewModel
import com.josimar.sicredieventtest.ui.viewmodel.DetailEventViewModel
import com.josimar.sicredieventtest.ui.viewmodel.ListEventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single<EventRepository> {
        EventRepository()
    }

    viewModel<ListEventViewModel> {
        ListEventViewModel(get())
    }

    viewModel<DetailEventViewModel> {
        DetailEventViewModel(get())
    }

    viewModel<CheckInViewModel> {
        CheckInViewModel(get())
    }
}