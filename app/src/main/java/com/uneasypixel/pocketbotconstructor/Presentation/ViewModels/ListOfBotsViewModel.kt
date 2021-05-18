package com.uneasypixel.pocketbotconstructor.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.ListOfBotsItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.SimpleItemTouchHelperCallback
import com.uneasypixel.pocketbotconstructor.Presentation.UI.ListOfBotsMenuFragment


class ListOfBotsViewModel() : ViewModel(), IRecyclerViewClickListener {

    private lateinit var dependencyFactory: DependencyFactory
    private lateinit var callback: ItemTouchHelper.Callback
    private lateinit var owner: ListOfBotsMenuFragment

    private lateinit var _adapter: ListOfBotsItemAdapter
    val adapter get() = _adapter

    private lateinit var _touchHelper : ItemTouchHelper
    val touchHelper get() = _touchHelper

    val listOfBots get() = adapter.dataset

    fun initial(DependencyFactory: DependencyFactory, clickListener: IRecyclerViewClickListener, Owner: ListOfBotsMenuFragment) {

        owner = Owner
        dependencyFactory = DependencyFactory
        val _listOfBots = dependencyFactory.provideGetBotsUseCase().getBots(owner!!.requireContext())

        _adapter = ListOfBotsItemAdapter(
            _listOfBots,
            clickListener,
            this
        )

        callback = SimpleItemTouchHelperCallback(adapter)
        _touchHelper = ItemTouchHelper(callback)
    }

    fun addItem(newBot : Bot) {
        _adapter.addItem(newBot)
    }

    override fun onCleared() {
        super.onCleared()
        println("View Model is cleare")
    }

    override fun recyclerViewListClicked(position: Int) {
        dependencyFactory.provideSaveBotsUseCase().saveBots(owner!!.requireContext(), adapter.dataset)
    }
}