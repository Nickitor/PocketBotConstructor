package com.uneasypixel.pocketbotconstructor.Data.DTO

import com.uneasypixel.pocketbotconstructor.Domain.DAO.BotDAO

class BotDTO(override var name: String?, override var imageResourceId: Int,
             override var reactionsToPhrases: Map<String, MutableList<String>>
) : BotDAO