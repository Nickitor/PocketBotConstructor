package com.uneasypixel.pocketbotconstructor.Domain.UseCase

import com.uneasypixel.pocketbotconstructor.Data.DTO.BotDTO
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway

class GetBotsUseCase(private val getBotsGateway : IGetBotsGateway) {

    fun getBots() : List<BotDTO> = getBotsGateway.getBots()
}