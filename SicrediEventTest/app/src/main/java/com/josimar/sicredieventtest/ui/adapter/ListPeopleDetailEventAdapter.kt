package com.josimar.sicredieventtest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.josimar.sicredieventtest.databinding.AdapterPeopleBinding

class ListPeopleDetailEventAdapter(private val listPeople:List<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterPeopleBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun getItemCount(): Int = listPeople.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            holder.bind(listPeople[position])
    }

    abstract inner class ItemViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: String)
    }

    inner class EventViewHolder(private val binding: AdapterPeopleBinding) : ItemViewHolder(binding) {
        override fun bind(item: String) {

            binding.people = item
        }
    }
}