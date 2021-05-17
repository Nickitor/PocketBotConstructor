package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import com.uneasypixel.pocketbotconstructor.Domain.DAO.BotDAO
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway

class GetBotsUseCase(private val getBotsGateway : IGetBotsGateway) {

    fun getBots() : List<BotDAO> = getBotsGateway.getBots()
}