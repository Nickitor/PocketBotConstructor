/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ СИНГЛТОН, КОТОРЫЙ ВКЛЮЧАЕТ В СЕБЯ МЕТОДЫ ДЛЯ ФОРМИРОВАНИЯ
// URL ЗАПРОСОВ ДЛЯ ОТПРАВКИ СЕРВЕРУ.
// *********************************************************************
package com.uneasypixel.pocketbotconstructor.data.api

import android.net.Uri
import com.uneasypixel.pocketbotconstructor.CONSTANTS.HTTPS_PROTOCOL
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_DOMEN_API
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHODS_METHOD
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHOD_GET_CONVERSATIONS
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHOD_GET_CONVERSATIONS_BY_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHOD_GET_HISTORY
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHOD_GET_LONG_POLL_SERVER
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHOD_GET_STATS
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHOD_GET_WALL
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHOD_MESSAGES_SEND
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_ACCESS_TOKEN
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_APP_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_ATTACHMENT
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_COUNT
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_EXTENDED
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_GROUP_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_INTERVAL
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_MESSAGE
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_OFFSET
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_OWNER_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_PEER_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_PEER_IDS
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_RANDOM_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_START_MESSAGE_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_USER_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_VERSION
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_VALUE_APP_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_VALUE_INTERVAL
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_VALUE_VERSION
import java.net.MalformedURLException
import java.net.URL

object URLBuilder {

    /**
     * Получение Uri метода VK API без указания параметров
     * protocol - протокол запросов к серверу (http/https)
     * domain - домен сервера VK
     * methodsName - название набора методов VK API
     * method - название метода VK API
     */
    private fun getURIBase(
        protocol: String = HTTPS_PROTOCOL,
        domain: String = VK_DOMEN_API,
        methodsName: String = VK_METHODS_METHOD,
        method: String
    ): Uri = Uri.parse(protocol + domain + methodsName + method)


    /**
     * Получение Uri запроса с параметрами токена и версии API
     * token - токен
     */
    private fun getURIWithVersionAndToken(
        protocol: String = HTTPS_PROTOCOL,
        domain: String = VK_DOMEN_API,
        methodsName: String = VK_METHODS_METHOD,
        method: String,
        token: String
    ): Uri = getURIBase(
        protocol,
        domain,
        methodsName,
        method
    )
        .buildUpon()
        .appendQueryParameter(VK_PARAM_VERSION, VK_VALUE_VERSION)
        .appendQueryParameter(VK_PARAM_ACCESS_TOKEN, token)
        .build()


    /**
     * Получение URL по Uri
     * uri - uri запроса
     */
    private fun getURLFromUri(uri: Uri): URL? {
        var url: URL? = null
        try {
            url = URL(uri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return url
    }


    /**
     * Получение URL запроса на отправку сообщения пользователю по ID
     * message - текст сообщения
     * userID - ID пользователя
     * attachment - список вложений
     * token - токен
     */
    fun getUrlSendMessageToID(
        message: String,
        userID: String,
        token: String,
        attachment: String = ""
    ): URL? = getURLFromUri(
        getURIWithVersionAndToken(method = VK_METHOD_MESSAGES_SEND, token = token)
            .buildUpon()
            .appendQueryParameter(VK_PARAM_MESSAGE, message)
            .appendQueryParameter(VK_PARAM_USER_ID, userID)
            .appendQueryParameter(VK_PARAM_ATTACHMENT, attachment)
            .appendQueryParameter(VK_PARAM_RANDOM_ID, getRandomID())
            .build()
    )


    /**
     * Получение URL запроса на получение бесед пользователя или группы
     * token - токен
     */
    fun getUrlGetConversations(
        token: String
    ): URL? = getURLFromUri(
        getURIWithVersionAndToken(method = VK_METHOD_GET_CONVERSATIONS, token = token)
            .buildUpon()
            .build()
    )


    /**
     * Получение URL запроса на получение статистики группы
     * groupID - ID группы
     * token - токен
     */
    fun getUrlGetStats(
        groupID: String,
        token: String
    ): URL? = getURLFromUri(
        getURIWithVersionAndToken(method = VK_METHOD_GET_STATS, token = token)
            .buildUpon()
            .appendQueryParameter(VK_PARAM_GROUP_ID, groupID)
            .appendQueryParameter(VK_PARAM_INTERVAL, VK_VALUE_INTERVAL)
            .appendQueryParameter(VK_PARAM_EXTENDED, "1")
            .appendQueryParameter(VK_PARAM_APP_ID, VK_VALUE_APP_ID)
            .build()
    )


    /**
     * Получение URL запроса на получение записей со стены
     * группы или пользователя
     * token - токен
     */
    fun getUrlGetWall(
        groupID: String,
        offset: String,
        count: String,
        token: String
    ): URL? = getURLFromUri(
        getURIWithVersionAndToken(method = VK_METHOD_GET_WALL, token = token)
            .buildUpon()
            .appendQueryParameter(VK_PARAM_OWNER_ID, groupID)
            .appendQueryParameter(VK_PARAM_OFFSET, offset)
            .appendQueryParameter(VK_PARAM_COUNT, count)
            .build()
    )


    /**
     * Получение URL запроса на получение истории сообщений диалога
     * token - токен
     */
    fun getUrlGetHistory(
        peerId: String,
        startMessageId: String,
        offset: String,
        token: String
    ): URL? {

        var vk_param_start_message_id = VK_PARAM_START_MESSAGE_ID
        if (startMessageId == "")
            vk_param_start_message_id = ""

        return getURLFromUri(
            getURIWithVersionAndToken(method = VK_METHOD_GET_HISTORY, token = token)
                .buildUpon()
                .appendQueryParameter(VK_PARAM_OFFSET, offset)
                .appendQueryParameter(vk_param_start_message_id, startMessageId)
                .appendQueryParameter(VK_PARAM_PEER_ID, peerId)
                .appendQueryParameter(VK_PARAM_COUNT, "200")
                .build()
        )
    }


    /**
     * Получение URL запроса на получение бесед пользователя или группы
     * по id с информацией о пользователе или группе
     * token - токен
     */
    fun getUrlGetConversationsById(
        peerIds: List<String>,
        token: String
    ): URL? {
        var peerIdsUri: String = ""
        for ((index, peerId) in peerIds.withIndex()) {
            peerIdsUri += peerId
            if (index < peerIds.size - 1)
                peerIdsUri += ","
        }
        val uri =
            getURIWithVersionAndToken(method = VK_METHOD_GET_CONVERSATIONS_BY_ID, token = token)
                .buildUpon()
                .appendQueryParameter(VK_PARAM_PEER_IDS, peerIdsUri)
                .appendQueryParameter(VK_PARAM_EXTENDED, "1")
                .build()

        return getURLFromUri(uri)
    }


    /**
     * Получение URL запроса для настройки Long Poll сервера
     * groupID - ID пользователя
     * token - токен
     */
    fun getURLGetLongPollServer(
        groupID: String,
        token: String
    ): URL? = getURLFromUri(
        getURIWithVersionAndToken(method = VK_METHOD_GET_LONG_POLL_SERVER, token = token)
            .buildUpon()
            .appendQueryParameter(VK_PARAM_GROUP_ID, groupID)
            .build()
    )


    /**
     * Получение URL для отправки запроса Long Poll серверу
     * server - адрес сервера
     * key - секретный ключ сессии
     * ts - номер последнего события, начиная с которого нужно получать данные
     * wait - время ожидания (сек). Максимальное значение - 90
     */
    fun getURLLongPollServerRequest(
        server: String,
        key: String,
        ts: String,
        wait: String
    ): URL? = URL("$server?act=a_check&key=$key&ts=$ts&wait=$wait")


    // Получение случайного ID
    private fun getRandomID() = (0..Long.MAX_VALUE).random().toString()
}