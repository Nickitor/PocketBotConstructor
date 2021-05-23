/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ ЗНАЧЕНИЯ КОНСТАНТ ПРИЛОЖЕНИЯ ДЛЯ VK API.
// *********************************************************************
package com.uneasypixel.pocketbotconstructor

object CONSTANTS {

    // ПРОТОКОЛЫ ЗАПРОСОВ
    const val HTTPS_PROTOCOL = "https://"


    // ДОМЕНЫ СЕРВЕРОВ VK
    const val VK_DOMEN_API = "api.vk.com"


    // НАЗВАНИЯ ПАРМЕТРОВ VK API
    const val VK_PARAM_VERSION = "v"
    const val VK_PARAM_ACCESS_TOKEN = "access_token"
    const val VK_PARAM_MESSAGE = "message"
    const val VK_PARAM_USER_ID = "user_id"
    const val VK_PARAM_RANDOM_ID = "random_id"
    const val VK_PARAM_GROUP_ID = "group_id"
    const val VK_PARAM_PEER_IDS = "peer_ids"
    const val VK_PARAM_PEER_ID = "peer_id"
    const val VK_PARAM_EXTENDED = "extended"
    const val VK_PARAM_INTERVAL = "interval"
    const val VK_PARAM_OFFSET = "offset"
    const val VK_PARAM_COUNT = "count"
    const val VK_PARAM_START_MESSAGE_ID = "start_message_id"
    const val VK_PARAM_APP_ID = "app_id"


    // НАЗВАНИЯ НАБОРОВ МЕТОДОВ VK API
    const val VK_METHODS_METHOD = "/method"


    // НАЗВАНИЯ МЕТОДОВ VK API
    const val VK_METHOD_MESSAGES_SEND = "/messages.send"
    const val VK_METHOD_GET_CONVERSATIONS = "/messages.getConversations"
    const val VK_METHOD_GET_STATS = "/stats.get"
    const val VK_METHOD_GET_HISTORY = "/messages.getHistory"
    const val VK_METHOD_GET_CONVERSATIONS_BY_ID = "/messages.getConversationsById"
    const val VK_METHOD_GET_LONG_POLL_SERVER = "/groups.getLongPollServer"


    // ЗНАЧЕНИЯ ПАРАМЕТРОВ VK API
    const val VK_VALUE_VERSION = "5.130"
    const val VK_VALUE_INTERVAL = "all"
    const val VK_VALUE_APP_ID = "7793844"
}