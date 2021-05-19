package com.uneasypixel.pocketbotconstructor.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.uneasypixel.pocketbotconstructor.Presentation.Views.BotMenuButton
import com.uneasypixel.pocketbotconstructor.R

class BotMenuViewModel : ViewModel() {

    var botName: String? = null

    var buttons: List<BotMenuButton> = listOf(
        BotMenuButton("Создание меню", R.drawable.ic_menu),
        BotMenuButton("Наборы ответов", R.drawable.ic_set_of_phrases),

        BotMenuButton("Реакции", R.drawable.ic_reactions_to_phrases),
        BotMenuButton("События", R.drawable.ic_reactions_to_events),

        BotMenuButton("Диалоги", R.drawable.ic_dialogues),
        BotMenuButton("Статистика", R.drawable.ic_statistics),

        BotMenuButton("Рассылка", R.drawable.ic_sending),
        BotMenuButton("Переменные", R.drawable.ic_variables)
    )

}