package com.uneasypixel.pocketbotconstructor.presentation.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.domain.entities.Conversation

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: MutableList<Conversation>) {
    val adapter = recyclerView.adapter as DialoguesMenuItemAdapter
    adapter.dataset = data
    adapter.notifyDataSetChanged()
}