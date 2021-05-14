package com.uneasypixel.pocketbotconstructor.Data.Gateway

import com.uneasypixel.pocketbotconstructor.Data.DTO.BotDTO
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetBotsStorage
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway
import com.uneasypixel.pocketbotconstructor.R

class GetBotsGatewayImp(private val getBotsStorage: IGetBotsStorage) : IGetBotsGateway{

    override fun getBots(): List<BotDTO> {
        return listOf(
            BotDTO("#1 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_smile),
            BotDTO("#2 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_love_lovely),
            BotDTO("#3 Bot", R.drawable.ic_android_robot_mobile_sleeping_tired),
            BotDTO("#4 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_smile_happy_successful),
            BotDTO("#5 Bot", R.drawable.ic_android_robot_mobile_mood_emoji),
            BotDTO("#6 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_angry_upset),
            BotDTO("#7 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_crash_bug_dead),
            BotDTO("#8 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_joke_tounge),
            BotDTO("#9 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_smile_successful),
            BotDTO("#10 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sad),
            BotDTO("#11 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sad_crying),
            BotDTO("#12 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sad_tear),
            BotDTO("#13 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sick_ill_trouble),
            BotDTO("#14 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_joke_tounge),
            BotDTO("#15 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_love_lovely))
    }
}