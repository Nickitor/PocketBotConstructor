package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot

interface IGetBotsGateway {

    fun getBots() : List<Bot>
}