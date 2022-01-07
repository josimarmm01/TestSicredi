package com.josimar.sicredieventtest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.josimar.sicredieventtest.databinding.AdapterEventBinding
import com.josimar.sicredieventtest.model.Event

class ListEventAdapter(private val listEvent:List<Event>,
                       val onItemClickListener: (selectedEvent: Event) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterEventBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun getItemCount(): Int = listEvent.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            holder.bind(listEvent[position])
    }

    abstract inner class ItemViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: Event)
    }

    inner class EventViewHolder(private val binding: AdapterEventBinding) : ItemViewHolder(binding) {
        override fun bind(item: Event) {

            binding.cardContainerAdapterEvent.setOnClickListener { onItemClickListener.invoke(item) }
            binding.event = item
        }
    }
}