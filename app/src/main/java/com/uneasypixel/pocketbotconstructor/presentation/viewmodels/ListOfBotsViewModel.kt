package com.uneasypixel.pocketbotconstructor.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO

class ListOfBotsViewModel : ViewModel() {

    var listOfBots: MutableList<Bot> = mutableListOf()
    var listOfBotsDTO: MutableList<BotDTO> = mutableListOf()

    fun getBots(dependencyFactory: DependencyFactory, context: Context) {
        listOfBotsDTO = dependencyFactory.provideGetBotsUseCase().getBots(context)

        for (bot in listOfBotsDTO) {
            listOfBots.add(dependencyFactory.provideGetBotsUseCase().getBot(context, bot.name!!))
        }
    }

    fun saveBots(dependencyFactory: DependencyFactory, context: Context) {
        dependencyFactory.provideSaveBotsUseCase().saveBots(context, listOfBotsDTO)

        for (bot in listOfBots) {
            dependencyFactory.provideSaveBotsUseCase().saveBot(context, bot)
        }
    }
}