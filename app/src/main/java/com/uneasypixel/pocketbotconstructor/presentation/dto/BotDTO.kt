package com.uneasypixel.pocketbotconstructor.presentation.dto

import com.uneasypixel.pocketbotconstructor.domain.dao.BotDAO

open class BotDTO(
    override var name: String?, override var imageResourceId: Int,
    override var isEnabled: Boolean = false, override var isRunning: Boolean = false
) : BotDAO {

    fun copy(): BotDTO =
        BotDTO(name, imageResourceId, isEnabled, isRunning)
}