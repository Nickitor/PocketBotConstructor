package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway

class GetBotsUseCase(private val getBotsGateway : IGetBotsGateway) {

    fun getBots(context: Context) : MutableList<Bot> = getBotsGateway.getBots(context)
}