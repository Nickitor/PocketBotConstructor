package com.uneasypixel.pocketbotconstructor.domain.dao

interface StatsDAO {

    var numberOfDialogues: Int
    var numberOfInMessages: Int
    var numberOfOutMessages: Int
    var numberOfMessages: Int
}