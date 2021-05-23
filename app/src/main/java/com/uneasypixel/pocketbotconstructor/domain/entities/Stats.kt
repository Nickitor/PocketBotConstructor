package com.uneasypixel.pocketbotconstructor.domain.entities

import com.uneasypixel.pocketbotconstructor.domain.dao.StatsDAO

class Stats(
) : StatsDAO {
    override var numberOfDialogues: Int = 0
    override var numberOfInMessages: Int = 0
    override var numberOfOutMessages: Int = 0
    override var numberOfMessages: Int = 0
}