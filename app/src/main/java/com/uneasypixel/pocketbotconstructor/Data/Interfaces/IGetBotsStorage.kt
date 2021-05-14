package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import org.json.JSONObject

interface IGetBotsStorage {

    fun getBots() : JSONObject?
}