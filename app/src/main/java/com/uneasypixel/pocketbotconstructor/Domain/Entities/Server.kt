package com.uneasypixel.pocketbotconstructor.Domain.Entities

import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO

class Server(
    val groupID: String,
    var tokenGroup: String,

    override var server: String? = null,
    override var key: String? = null,
    override var ts: String? = null,
    override var waitTimeResponse: String?
) : ServerDAO {
}