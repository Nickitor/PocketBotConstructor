package com.uneasypixel.pocketbotconstructor.Presentation.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.Presentation.Views.BotMenuButton
import com.uneasypixel.pocketbotconstructor.R

class BotMenuItemAdapter(
    private val dataset: List<BotMenuButton>,
    private val clickListener: IRecyclerViewClickListener
) : RecyclerView.Adapter<BotMenuItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById(R.id.bot_menu_item_title)
        val imageView: ImageView = view.findViewById(R.id.bot_menu_item_image_view)
        val notificationView: TextView = view.findViewById(R.id.bot_menu_item_notification_textView)

        fun onClick(clickListener: IRecyclerViewClickListener) {
            imageView.setOnClickListener {
                clickListener.recyclerViewListClicked(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.bot_menu_button_item, parent, false)

        val itemViewHolder = BotMenuItemAdapter.ItemViewHolder(adapterLayout)
        itemViewHolder.onClick(clickListener)

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.title
        holder.imageView.setImageResource(item.imageResourceId)
    }

    override fun getItemCount() = dataset.size
}