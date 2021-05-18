package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot

interface IGetBotsGateway {

    fun getBots(context: Context) : MutableList<Bot>
}