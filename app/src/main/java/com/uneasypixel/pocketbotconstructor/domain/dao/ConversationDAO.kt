package com.uneasypixel.pocketbotconstructor.domain.dao

interface ConversationDAO {

    var id : String?
    var type : String?
    var firstName : String?
    var lastName : String?
    var title : String?

    var photo : String?

    var unreadCount : Int?

    var isOnline : Boolean?

    var lastMessageDate : Int?
    var lastMessageText : String?
}