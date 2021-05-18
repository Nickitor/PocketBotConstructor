package com.uneasypixel.pocketbotconstructor.Data.Gateway

import com.uneasypixel.pocketbotconstructor.Data.DTO.BotDTO
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetBotsStorage
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway
import com.uneasypixel.pocketbotconstructor.R

class GetBotsGatewayImp(private val getBotsStorage: IGetBotsStorage) : IGetBotsGateway{

    override fun getBots(): List<BotDTO> {
        return listOf(
            BotDTO("#1 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_smile, mapOf("Привет!" to mutableListOf("Я первый бот"))),
            BotDTO("#2 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_love_lovely, mapOf("Привет!" to mutableListOf("Я второй бот"))),
            BotDTO("#3 Bot", R.drawable.ic_android_robot_mobile_sleeping_tired, mapOf("Привет!" to mutableListOf("Я третий бот"))),
            BotDTO("#4 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_smile_happy_successful, mapOf()),
            BotDTO("#5 Bot", R.drawable.ic_android_robot_mobile_mood_emoji, mapOf()),
            BotDTO("#6 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_angry_upset, mapOf()),
            BotDTO("#7 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_crash_bug_dead, mapOf()),
            BotDTO("#8 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_joke_tounge, mapOf()),
            BotDTO("#9 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_smile_successful, mapOf()),
            BotDTO("#10 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sad, mapOf()),
            BotDTO("#11 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sad_crying, mapOf()),
            BotDTO("#12 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sad_tear, mapOf()),
            BotDTO("#13 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sick_ill_trouble, mapOf()),
            BotDTO("#14 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_joke_tounge, mapOf()),
            BotDTO("#15 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_love_lovely, mapOf()))
    }
}