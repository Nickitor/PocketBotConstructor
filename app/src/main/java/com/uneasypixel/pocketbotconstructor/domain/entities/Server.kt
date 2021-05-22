package com.uneasypixel.pocketbotconstructor.domain.entities

import com.uneasypixel.pocketbotconstructor.domain.dao.ServerDAO

class Server(
    override var server: String? = null,
    override var key: String? = null,
    override var ts: String? = null,
    override var waitTimeResponse: String?
) : ServerDAO {
}