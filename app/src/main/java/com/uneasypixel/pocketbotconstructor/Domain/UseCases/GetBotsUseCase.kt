package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO

class GetBotsUseCase(private val getBotsGateway : IGetBotsGateway) {

    fun getBots(context: Context) : MutableList<BotDTO> = getBotsGateway.getBots(context)

    fun getBot(context: Context, name : String) : Bot = getBotsGateway.getBot(context, name)
}