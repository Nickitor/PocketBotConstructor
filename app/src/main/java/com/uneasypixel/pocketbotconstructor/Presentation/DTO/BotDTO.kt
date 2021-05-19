package com.uneasypixel.pocketbotconstructor.Presentation.DTO

import com.uneasypixel.pocketbotconstructor.Domain.DAO.BotDAO

open class BotDTO(override var name: String?, override var imageResourceId: Int,
                  override var isEnabled: Boolean = false, override var isRunning: Boolean = false
) : BotDAO {
}