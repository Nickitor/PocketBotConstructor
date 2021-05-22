package com.uneasypixel.pocketbotconstructor.domain.usecases

import android.content.Context
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetBotsGateway
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO

class GetBotsUseCase(private val getBotsGateway : IGetBotsGateway) {

    fun getBots(context: Context) : MutableList<BotDTO> = getBotsGateway.getBots(context)

    fun getBot(context: Context, name : String) : Bot = getBotsGateway.getBot(context, name)
}