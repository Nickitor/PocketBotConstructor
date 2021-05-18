package com.uneasypixel.pocketbotconstructor.Data.Gateway

import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetBotsStorage
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway
import com.uneasypixel.pocketbotconstructor.R

class GetBotsGatewayImp(private val getBotsStorage: IGetBotsStorage) : IGetBotsGateway{

    override fun getBots(): List<Bot> {
        return listOf(
            Bot("#1 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_smile),
            Bot("#2 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_love_lovely),
            Bot("#3 Bot", R.drawable.ic_android_robot_mobile_sleeping_tired),
            Bot("#4 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_smile_happy_successful),
            Bot("#5 Bot", R.drawable.ic_android_robot_mobile_mood_emoji),
            Bot("#6 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_angry_upset),
            Bot("#7 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_crash_bug_dead),
            Bot("#8 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_joke_tounge),
            Bot("#9 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_smile_successful),
            Bot("#10 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sad),
            Bot("#11 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sad_crying),
            Bot("#12 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sad_tear),
            Bot("#13 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_sick_ill_trouble),
            Bot("#14 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_happy_joke_tounge),
            Bot("#15 Bot", R.drawable.ic_android_robot_mobile_mood_emoji_love_lovely)
        )
    }
}