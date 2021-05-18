package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot

interface ISaveBotsStorage {

    fun saveBots(context: Context, bots : List<Bot>)
}