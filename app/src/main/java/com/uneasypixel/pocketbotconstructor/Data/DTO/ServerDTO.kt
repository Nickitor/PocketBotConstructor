package com.uneasypixel.pocketbotconstructor.Data.DTO

import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO

class ServerDTO(override var server: String?, override var key: String?, override var ts: String?,
                override var waitTimeResponse: String?
) : ServerDAO {
}