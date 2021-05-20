package com.uneasypixel.pocketbotconstructor.Presentation.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.Domain.Entities.DialogScript
import com.uneasypixel.pocketbotconstructor.R
import java.util.*


class DialogScriptMenuItemAdapter(
    val dataset: MutableList<DialogScript>,
    private val clickListener: IRecyclerViewClickListener
) : RecyclerView.Adapter<DialogScriptMenuItemAdapter.ItemViewHolder>(),
    ItemTouchHelperAdapter{

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textPhrase: TextView = view.findViewById(R.id.reactions_to_phrases_item_phrase)
        val textResponse: TextView = view.findViewById(R.id.reactions_to_phrases_item_response)

        fun onClick(clickListener: IRecyclerViewClickListener) {
            view.setOnClickListener {
                clickListener.recyclerViewListClicked(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.reactions_to_dialog_script_item, parent, false)

        val itemViewHolder = ItemViewHolder(adapterLayout)
        itemViewHolder.onClick(clickListener)

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        val limit = 8
        var text = cutString(item.phrase, limit)
        holder.textPhrase.text = text

        text = cutString(item.response[0], limit)
        holder.textResponse.text = text
    }

    private fun cutString(str : String?, limit : Int) : String {

        var result : String = ""

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

    fun addItem(newPhrase : DialogScript) {
        val position = dataset.size
        dataset.add(position, newPhrase)
        notifyItemInserted(position)
        clickListener.recyclerViewListAdd(position)
    }

    override fun onItemDismiss(position: Int) {
        dataset.removeAt(position)
        notifyItemRemoved(position)
        clickListener.recyclerViewListDelete(position)
    }

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