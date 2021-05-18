package com.uneasypixel.pocketbotconstructor.Presentation.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.R
import java.util.*


class ListOfBotsItemAdapter(
    val dataset: MutableList<Bot>,
    private val clickListener: IRecyclerViewClickListener,
    private val dataListener: IRecyclerViewClickListener
) : RecyclerView.Adapter<ListOfBotsItemAdapter.ItemViewHolder>(),
    ItemTouchHelperAdapter{

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById(R.id.bot_name)
        val imageView: ImageView = view.findViewById(R.id.bot_image)

        fun onClick(clickListener: IRecyclerViewClickListener) {
            view.setOnClickListener {
                clickListener.recyclerViewListClicked(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_bots_item, parent, false)

        val itemViewHolder = ItemViewHolder(adapterLayout)
        itemViewHolder.onClick(clickListener)

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.name
        holder.imageView.setImageResource(item.imageResourceId)
    }

    override fun getItemCount() = dataset.size

    fun addItem(newBot : Bot) {
        dataset.add(dataset.size, newBot)
        notifyItemInserted(dataset.size)
        dataListener.recyclerViewListClicked(dataset.size)
    }

    override fun onItemDismiss(position: Int) {
        dataset.removeAt(position)
        notifyItemRemoved(position)
        dataListener.recyclerViewListClicked(position)
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
        dataListener.recyclerViewListClicked(fromPosition)
        return true
    }
}