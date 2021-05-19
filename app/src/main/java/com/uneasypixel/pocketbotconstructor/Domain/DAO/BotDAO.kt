package com.uneasypixel.pocketbotconstructor.Domain.DAO

interface BotDAO {
    var name : String?
    var imageResourceId: Int
    var isEnabled : Boolean
    var isRunning : Boolean
}