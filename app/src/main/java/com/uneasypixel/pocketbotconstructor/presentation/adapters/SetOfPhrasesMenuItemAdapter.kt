package com.uneasypixel.pocketbotconstructor.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.domain.entities.SetOfPhrases
import java.util.*

class SetOfPhrasesMenuItemAdapter(
    val dataset: MutableList<SetOfPhrases>,
    private val clickListener: IRecyclerViewClickListener
) : RecyclerView.Adapter<SetOfPhrasesMenuItemAdapter.ItemViewHolder>(),
    ItemTouchHelperAdapter {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val setName: TextView = view.findViewById(R.id.set_of_phrases_set_name)

        fun onClick(clickListener: IRecyclerViewClickListener) {
            view.setOnClickListener {
                clickListener.recyclerViewListClicked(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.set_of_phrases_item, parent, false)

        val itemViewHolder = ItemViewHolder(adapterLayout)
        itemViewHolder.onClick(clickListener)

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        val limit = 40
        val text = cutString(item.name, limit)
        holder.setName.text = text
    }

    private fun cutString(str: String?, limit: Int): String {

        var result: String = ""

        if (str != null) {

            if (str.length <= limit)
                return str

            var i = 0
            for (ch in str) {
                result += ch
                ++i
                if (i == limit)
                    return "$result.."
            }
        }

        return result
    }

    override fun getItemCount() = dataset.size

    fun addItem(newSet: SetOfPhrases) {
        val position = dataset.size
        dataset.add(position, newSet)
        notifyItemInserted(position)
        clickListener.recyclerViewListAdd(position)
    }

    override fun onItemDismiss(position: Int) {
        dataset.removeAt(position)
        notifyItemRemoved(position)
        clickListener.recyclerViewListDelete(position)
    }

    override fun onItemChanged(position: Int, text: String) {}

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(dataset, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(dataset, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        clickListener.recyclerViewListMove(fromPosition, toPosition)
        return true
    }
}