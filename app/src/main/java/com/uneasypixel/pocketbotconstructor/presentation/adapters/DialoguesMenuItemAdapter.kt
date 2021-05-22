package com.uneasypixel.pocketbotconstructor.presentation.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uneasypixel.pocketbotconstructor.domain.entities.Conversation
import com.uneasypixel.pocketbotconstructor.R

class DialoguesMenuItemAdapter(
    private val clickListener: IRecyclerViewClickListener
) : RecyclerView.Adapter<DialoguesMenuItemAdapter.ItemViewHolder>() {

    var dataset: MutableList<Conversation> = mutableListOf()

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textTitle: TextView = view.findViewById(R.id.dialogues_item_title)
        val photo: ImageView = view.findViewById(R.id.dialogues_item_user_image)
        val isOnlineIndicator: ImageView = view.findViewById(R.id.dialogues_item_online)
        val isOnlineText: TextView = view.findViewById(R.id.dialogues_item_online_title)

        fun onClick(clickListener: IRecyclerViewClickListener) {
            view.setOnClickListener {
                clickListener.recyclerViewListClicked(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.dialogues_item, parent, false)

        val itemViewHolder = ItemViewHolder(adapterLayout)
        itemViewHolder.onClick(clickListener)

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        val limit = 25
        val title = if (item.type == "user") cutString(
            item.firstName + " " + item.lastName,
            limit
        ) else cutString(item.title, limit)
        holder.textTitle.text = title

        Picasso.get().load(item.photo).into(holder.photo)

        when (item.type) {
            "user" -> {
                if (item.isOnline!!) {
                    holder.isOnlineText.text = "Онлайн"
                    ImageViewCompat.setImageTintList(holder.isOnlineIndicator, ColorStateList.valueOf(Color.parseColor("#B5FFA8")))
                }
                else  {
                    holder.isOnlineText.text = "Оффлайн"
                    ImageViewCompat.setImageTintList(holder.isOnlineIndicator, ColorStateList.valueOf(Color.parseColor("#FF6382")))
                }
            }
            "chat" -> {
                holder.isOnlineText.isVisible = false
                holder.isOnlineIndicator.isVisible = false
            }
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