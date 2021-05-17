package com.uneasypixel.pocketbotconstructor.Presentation.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.Presentation.Views.BotMenuButton
import com.uneasypixel.pocketbotconstructor.R

class BotMenuItemAdapter(
    private val dataset: List<BotMenuButton>
) : RecyclerView.Adapter<BotMenuItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.findViewById(R.id.bot_menu_item_title)
        val imageView: ImageView = view.findViewById(R.id.bot_menu_item_image_view)
        val notificationView: TextView = view.findViewById(R.id.bot_menu_item_notification_textView)

        init {
            notificationView.isVisible = false
        }

        fun setOnClickListener(position: Int) {

            when (position) {
                0 -> {
                    imageView.setOnClickListener(
                        Navigation.createNavigateOnClickListener(R.id.action_botMenuFragment_to_createMenuMenuFragment)
                    )
                }

                1 -> {
                    imageView.setOnClickListener(
                        Navigation.createNavigateOnClickListener(R.id.action_botMenuFragment_to_setOfPhrasesMenuFragment)
                    )
                }

                2 -> {
                    imageView.setOnClickListener(
                        Navigation.createNavigateOnClickListener(R.id.action_botMenuFragment_to_reactionsToPhrasesMenuFragment)
                    )
                }

                3 -> {
                    imageView.setOnClickListener(
                        Navigation.createNavigateOnClickListener(R.id.action_botMenuFragment_to_reactionsToEventsMenuFragment)
                    )
                }

                4 -> {
                    notificationView.isVisible = true
                    imageView.setOnClickListener(
                        Navigation.createNavigateOnClickListener(R.id.action_botMenuFragment_to_dialoguesMenuFragment)
                    )
                }

                5 -> {
                    imageView.setOnClickListener(
                        Navigation.createNavigateOnClickListener(R.id.action_botMenuFragment_to_statisticsMenuFragment)
                    )
                }

                6 -> {
                    imageView.setOnClickListener(
                        Navigation.createNavigateOnClickListener(R.id.action_botMenuFragment_to_sendingMenuFragment)
                    )
                }

                7 -> {
                    imageView.setOnClickListener(
                        Navigation.createNavigateOnClickListener(R.id.action_botMenuFragment_to_variablesMenuFragment)
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.bot_menu_button_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.title
        holder.imageView.setImageResource(item.imageResourceId)

        holder.setOnClickListener(position)
    }

    override fun getItemCount() = dataset.size
}