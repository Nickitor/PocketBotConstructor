/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ СИНГЛТОН, КОТОРЫЙ ВКЛЮЧАЕТ В СЕБЯ МЕТОДЫ ДЛЯ ФОРМИРОВАНИЯ
// URL ЗАПРОСОВ ДЛЯ ОТПРАВКИ СЕРВЕРУ.
// *********************************************************************
package com.uneasypixel.pocketbotconstructor.Data.API

import android.net.Uri
import com.uneasypixel.pocketbotconstructor.CONSTANTS.HTTPS_PROTOCOL
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_DOMEN_API
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHODS_METHOD
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHOD_GET_LONG_POLL_SERVER
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_METHOD_MESSAGES_SEND
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_ACCESS_TOKEN
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_GROUP_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_MESSAGE
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_RANDOM_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_USER_ID
import com.uneasypixel.pocketbotconstructor.CONSTANTS.VK_PARAM_VERSION
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
            method)
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
     * versionAPI - версия VK API
     * token - токен
     */
    fun getUrlSendMessageToID(
            message: String,
            userID: String,
            token: String
    ): URL? = getURLFromUri(
            getURIWithVersionAndToken(method = VK_METHOD_MESSAGES_SEND, token = token)
                    .buildUpon()
                    .appendQueryParameter(VK_PARAM_MESSAGE, message)
                    .appendQueryParameter(VK_PARAM_USER_ID, userID)
                    .appendQueryParameter(VK_PARAM_RANDOM_ID, getRandomID())
                    .build()
    )


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