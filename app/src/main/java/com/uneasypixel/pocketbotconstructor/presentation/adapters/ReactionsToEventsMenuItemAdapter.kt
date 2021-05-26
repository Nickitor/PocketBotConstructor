package com.uneasypixel.pocketbotconstructor.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.domain.entities.DialogScript

class ReactionsToEventsMenuItemAdapter(
    val dataset: MutableList<DialogScript>,
    private val clickListener: IRecyclerViewClickListener
) : RecyclerView.Adapter<ReactionsToEventsMenuItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textPhrase: TextView = view.findViewById(R.id.reactions_to_events_item_phrase)
        val textResponse: TextView = view.findViewById(R.id.reactions_to_events_item_response)

        fun onClick(clickListener: IRecyclerViewClickListener) {
            view.setOnClickListener {
                clickListener.recyclerViewListClicked(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.reactions_to_events_item, parent, false)

        val itemViewHolder = ItemViewHolder(adapterLayout)
        itemViewHolder.onClick(clickListener)

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        val limit = 40
        var text = cutString(item.phrase, limit)
        holder.textPhrase.text = text

        if (item.response.isNotEmpty()) {
            text = cutString(item.response[0], limit)
            holder.textResponse.text = text
        }
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
}