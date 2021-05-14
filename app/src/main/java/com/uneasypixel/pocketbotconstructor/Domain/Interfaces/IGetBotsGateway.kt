package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

import com.uneasypixel.pocketbotconstructor.Data.DTO.BotDTO

interface IGetBotsGateway {

    fun getBots() : List<BotDTO>
}