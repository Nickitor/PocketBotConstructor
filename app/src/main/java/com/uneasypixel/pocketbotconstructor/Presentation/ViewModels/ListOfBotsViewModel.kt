package com.uneasypixel.pocketbotconstructor.Presentation.ViewModels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.ListOfBotsItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.SimpleItemTouchHelperCallback
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO
import com.uneasypixel.pocketbotconstructor.R


class ListOfBotsViewModel() : ViewModel() {

    private lateinit var dependencyFactory: DependencyFactory
    private lateinit var callback: ItemTouchHelper.Callback

    private lateinit var _adapter: ListOfBotsItemAdapter
    val adapter get() = _adapter

    private lateinit var _touchHelper : ItemTouchHelper
    val touchHelper get() = _touchHelper

    private var _listOfBots : List<Bot> = mutableListOf()
    val listOfBots get() = _listOfBots

    fun initial(DependencyFactory: DependencyFactory, clickListener: IRecyclerViewClickListener, owner: LifecycleOwner) {

        dependencyFactory = DependencyFactory
        _listOfBots = dependencyFactory.provideGetBotsUseCase().getBots()

        val listOfBotsDTO : MutableList<BotDTO> = mutableListOf()

        for (bot in _listOfBots)
            listOfBotsDTO.add(BotDTO(bot.name, bot.imageResourceId))

        _adapter = ListOfBotsItemAdapter(
            listOfBotsDTO,
            clickListener
        )

        callback = SimpleItemTouchHelperCallback(adapter)
        _touchHelper = ItemTouchHelper(callback)
    }

    fun addItem() {
        _adapter.addItem(BotDTO("#1 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_smile))

        println(listOfBots.size)
    }

    override fun onCleared() {
        super.onCleared()
        println("View Model is cleare")
    }
}