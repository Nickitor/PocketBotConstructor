package com.uneasypixel.pocketbotconstructor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.presentation.views.BotMenuButton

class BotMenuViewModel : ViewModel() {

    var bot: Bot? = null

    var buttons: List<BotMenuButton> = listOf(
        BotMenuButton("Наборы ответов", R.drawable.ic_set_of_phrases),

        BotMenuButton("Реакции", R.drawable.ic_dialog_script),
        BotMenuButton("События", R.drawable.ic_reactions_to_events),

        BotMenuButton("Диалоги", R.drawable.ic_dialogues),
        BotMenuButton("Статистика", R.drawable.ic_statistics),

        BotMenuButton("Рассылка", R.drawable.ic_sending)
    )

}