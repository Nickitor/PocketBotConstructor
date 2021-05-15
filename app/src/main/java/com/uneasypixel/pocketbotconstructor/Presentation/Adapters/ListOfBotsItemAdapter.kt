package com.uneasypixel.pocketbotconstructor.Presentation.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.uneasypixel.pocketbotconstructor.Domain.DAO.BotDAO
import com.uneasypixel.pocketbotconstructor.R


class ListOfBotsItemAdapter(
    private val dataset: List<BotDAO>
) : RecyclerView.Adapter<ListOfBotsItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_listOfBotsFragment_to_botMenuFragment)
            )
        }

        val textView: TextView = view.findViewById(R.id.bot_name)
        val imageView: ImageView = view.findViewById(R.id.bot_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_bots_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.name
        holder.imageView.setImageResource(item.imageResourceId)
    }

    override fun getItemCount() = dataset.size
}