package com.uneasypixel.pocketbotconstructor.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.domain.entities.SetOfPhrases
import java.util.*

class AddNewSetOfPhrasesMenuItemAdapter(
    val dataset: SetOfPhrases
) : RecyclerView.Adapter<AddNewSetOfPhrasesMenuItemAdapter.ItemViewHolder>(),
    ItemTouchHelperAdapter {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val itemValue: EditText = view.findViewById(R.id.add_new_set_of_phrases_item_value)
        val itemIcon: TextView = view.findViewById(R.id.add_new_set_of_phrases_item_icon)

        fun setTextChangedListener(listener: ItemTouchHelperAdapter) {
            itemValue.addTextChangedListener {
                listener.onItemChanged(adapterPosition, itemValue.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_new_set_of_phrases_item, parent, false)

        val itemViewHolder = ItemViewHolder(adapterLayout)
        itemViewHolder.setTextChangedListener(this)

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item =
            if (dataset.isTextResource) dataset.textSources[position] else dataset.groupSources[position]

        holder.itemValue.setText(item)

        holder.itemValue.inputType =
            if (dataset.isTextResource) EditorInfo.TYPE_CLASS_TEXT else EditorInfo.TYPE_CLASS_NUMBER

        holder.itemIcon.text = if (dataset.isTextResource) ">>" else "id"
    }


    override fun getItemCount(): Int {
        return if (dataset.isTextResource) dataset.textSources.size else dataset.groupSources.size
    }


    fun addItem(newStr: String) {
        val items =
            if (dataset.isTextResource) dataset.textSources else dataset.groupSources
        val position = items.size
        items.add(position, newStr)
        notifyItemInserted(position)
    }

    override fun onItemDismiss(position: Int) {
        val items =
            if (dataset.isTextResource) dataset.textSources else dataset.groupSources
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemChanged(position: Int, text: String) {
        val items =
            if (dataset.isTextResource) dataset.textSources else dataset.groupSources
        items[position] = text
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val items =
            if (dataset.isTextResource) dataset.textSources else dataset.groupSources
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }
}