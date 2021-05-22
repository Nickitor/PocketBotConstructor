package com.uneasypixel.pocketbotconstructor.domain.entities

import com.uneasypixel.pocketbotconstructor.domain.dao.BotDAO

class Bot(
    override var name: String?, override var imageResourceId: Int, override var isEnabled: Boolean = false,
    override var isRunning: Boolean = false
) : BotDAO {

    var reactionsToPhrases: MutableList<DialogScript> = mutableListOf()
    lateinit var groupID : String
    lateinit var token : String
}