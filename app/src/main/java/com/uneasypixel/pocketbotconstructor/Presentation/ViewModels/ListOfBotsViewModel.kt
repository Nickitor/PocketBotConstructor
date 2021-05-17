package com.uneasypixel.pocketbotconstructor.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot

class ListOfBotsViewModel : ViewModel() {

    private lateinit var dependencyFactory: DependencyFactory

    private var _listOfBots : MutableList<Bot> = mutableListOf()
    val listOfBots get() = _listOfBots

    fun getBots(DependencyFactory: DependencyFactory){
        dependencyFactory = DependencyFactory
        val listOfBotsDAO = dependencyFactory.provideGetBotsUseCase().getBots()
        _listOfBots.clear()

        for (botDAO in listOfBotsDAO){

            _listOfBots.add(Bot(botDAO.name, botDAO.imageResourceId))
        }
    }
}