package com.uneasypixel.pocketbotconstructor.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Phrase
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.ReactionsToPhrasesMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.SimpleItemTouchHelperCallback
import com.uneasypixel.pocketbotconstructor.Presentation.UI.ReactionsToPhrasesMenuFragment

class ReactionsToPhrasesViewModel() : ViewModel(), IRecyclerViewClickListener {

    private lateinit var dependencyFactory: DependencyFactory
    private lateinit var callback: ItemTouchHelper.Callback
    private lateinit var owner: ReactionsToPhrasesMenuFragment

    private lateinit var _adapter: ReactionsToPhrasesMenuItemAdapter
    val adapter get() = _adapter

    private lateinit var _touchHelper: ItemTouchHelper
    val touchHelper get() = _touchHelper

    var botName: String = ""
    var bot: Bot? = null

    fun initial(
        DependencyFactory: DependencyFactory,
        clickListener: IRecyclerViewClickListener,
        Owner: ReactionsToPhrasesMenuFragment,
        BotName: String
    ) {

        owner = Owner
        dependencyFactory = DependencyFactory

        botName = BotName

        bot = dependencyFactory.provideGetBotsUseCase().getBot(owner.requireContext(), botName!!)

        _adapter = ReactionsToPhrasesMenuItemAdapter(
            bot!!.reactionsToPhrases,
            clickListener,
            this
        )

        callback = SimpleItemTouchHelperCallback(adapter)
        _touchHelper = ItemTouchHelper(callback)
    }

    fun addItem(newPhrase: Phrase) {
        _adapter.addItem(newPhrase)
    }

    override fun recyclerViewListClicked(position: Int) {
        bot!!.reactionsToPhrases = adapter.dataset
        saveBot()
    }

    fun saveBot() {
        dependencyFactory.provideSaveBotsUseCase().saveBot(owner.requireContext(), bot!!)
    }
}