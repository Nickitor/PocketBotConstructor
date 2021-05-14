package com.uneasypixel.pocketbotconstructor.Domain.Entities

import com.uneasypixel.pocketbotconstructor.Domain.DAO.BotDAO

class Bot(override var name: String?, override val imageResourceId: Int) : BotDAO {
}