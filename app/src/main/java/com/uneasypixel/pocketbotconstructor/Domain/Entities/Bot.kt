package com.uneasypixel.pocketbotconstructor.Domain.Entities

import com.uneasypixel.pocketbotconstructor.Domain.DAO.BotDAO

class Bot(
    override var name: String?, override var imageResourceId: Int, override var isEnabled: Boolean = false,
    override var isRunning: Boolean = false
) : BotDAO {

    var reactionsToPhrases: MutableList<Phrase> = mutableListOf()
    lateinit var groupID : String
    lateinit var token : String
}