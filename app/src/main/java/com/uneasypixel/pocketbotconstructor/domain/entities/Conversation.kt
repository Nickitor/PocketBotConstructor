package com.uneasypixel.pocketbotconstructor.domain.entities

import com.uneasypixel.pocketbotconstructor.domain.dao.ConversationDAO

class Conversation(
    override var id: String? = null,
    override var type: String? = null,
    override var unreadCount: Int = 0,
    override var lastMessageDate: Int? = null,
    override var lastMessageText: String? = null, override var isOnline: Boolean? = null,
    override var firstName: String? = null,
    override var lastName: String? = null,
    override var title: String? = null,
    override var photo: String? = null
) : ConversationDAO {
}