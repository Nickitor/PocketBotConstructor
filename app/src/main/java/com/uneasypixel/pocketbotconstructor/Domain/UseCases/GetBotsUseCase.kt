package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway

class GetBotsUseCase(private val getBotsGateway : IGetBotsGateway) {

    fun getBots() : List<Bot> = getBotsGateway.getBots()
}