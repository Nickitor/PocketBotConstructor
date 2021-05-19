package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO

interface IGetBotsGateway {

    fun getBots(context: Context) : MutableList<BotDTO>

    fun getBot(context: Context, name : String) : Bot
}