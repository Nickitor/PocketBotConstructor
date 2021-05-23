package com.uneasypixel.pocketbotconstructor.presentation.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO
import java.util.*


class ListOfBotsItemAdapter(
    val dataset: MutableList<BotDTO>,
    private val clickListener: IRecyclerViewClickListener
) : RecyclerView.Adapter<ListOfBotsItemAdapter.ItemViewHolder>(),
    ItemTouchHelperAdapter{

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById(R.id.list_of_bots_item_name)
        val imageView: ImageView = view.findViewById(R.id.list_of_bots_item_bot_image)
        val powerText: TextView = view.findViewById(R.id.list_of_bots_item_power_title)
        val powerView: ImageView = view.findViewById(R.id.list_of_bots_item_power)

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

        val limit = 10
        val title = cutString(item.name, limit)
        holder.textView.text = title
        holder.imageView.setImageResource(item.imageResourceId)

        if (dataset[position].isEnabled) {
            holder.powerText.text = "Включен"
            ImageViewCompat.setImageTintList(holder.powerView, ColorStateList.valueOf(Color.parseColor("#B5FFA8")))

        }
        else  {
            holder.powerText.text = "Выключен"
            ImageViewCompat.setImageTintList(holder.powerView, ColorStateList.valueOf(Color.parseColor("#FF6382")))

        }
    }

    override fun getItemCount() = dataset.size

    fun addItem(newBot : BotDTO) {
        val position = dataset.size
        dataset.add(position, newBot)
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
}