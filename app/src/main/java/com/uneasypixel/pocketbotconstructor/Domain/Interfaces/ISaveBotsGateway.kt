package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot

interface ISaveBotsGateway {

    fun saveBots(context: Context, bots : List<Bot>)
}