package com.uneasypixel.pocketbotconstructor.domain.entities

import com.uneasypixel.pocketbotconstructor.domain.dao.BotDAO

class Bot(
    override var name: String?,
    override var imageResourceId: Int,
    override var isEnabled: Boolean = false,
    override var isRunning: Boolean = false
) : BotDAO {

    var reactionsToPhrases: MutableList<DialogScript> = mutableListOf()
    var reactionsToEvents: MutableList<DialogScript> = mutableListOf(
        DialogScript("Подписка на сообщения от сообщества"),
        DialogScript("Репост записи из сообщества"),
        DialogScript("Добавление комментария на стене"),
        DialogScript("Событие о новой отметке \"Мне нравится\"")
    )
    var setsOfPhrases: MutableList<SetOfPhrases> = mutableListOf()
    lateinit var groupID: String
    lateinit var token: String
}