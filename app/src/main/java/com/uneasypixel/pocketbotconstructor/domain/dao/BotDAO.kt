package com.uneasypixel.pocketbotconstructor.domain.dao

interface BotDAO {
    var name: String?
    var imageResourceId: Int
    var isEnabled: Boolean
    var isRunning: Boolean
}