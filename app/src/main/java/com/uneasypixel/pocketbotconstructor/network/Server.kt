/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ КЛАСС СЕРВЕРА. СЕРВЕР ОТПРАВЛЯЕТ ЗАПРОСЫ VK API И
// ОБРАБАТЫВАЕТ ОТВЕТЫ. URL ЗАПРОСЫ ФОРМИРУЮТСЯ В КЛАССЕ Requests.
// *********************************************************************
package com.uneasypixel.pocketbotconstructor.network

import android.util.Log
import com.uneasypixel.pocketbotconstructor.BuildConfig
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.*
import java.util.*

/**
 * Класс сервера
 * server - адрес сервера
 * key - секретный ключ сессии
 * ts - номер последнего события, начиная с которого нужно получать данные
 * groupID - ID сообщества чат-бота
 * tokenUser - токен пользователя
 * tokenGroup - токен сообщества
 * waitTimeResponse - время ожидания ответа
 */
class Server(
        private val groupID: String,
        private val tokenUser: String,
        private var tokenGroup: String,
        private var waitTimeResponse: String) {

    private val TAG = this.javaClass.simpleName

    private var server: String? = null
    private var key: String? = null
    private var ts: String? = null

    // Инициализация сервера
    init {
        val response: JSONObject? = getLongPollServer()?.getJSONObject("response")

        server = response?.getString("server")
        key = response?.getString("key")
        ts = response?.getString("ts")

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "Инициализация сервера. Ответ от Long Poll Server: ${response.toString()}")
        }
    }


    /**
     * Отправка запроса и возврат ответа от сервера по URL
     * url = URL запроса к серверу
     */
    private fun getResponse(url: URL): JSONObject? {
        val urlConnection = url.openConnection() as? HttpURLConnection

        try {
            val input = urlConnection?.inputStream

            val scanner = Scanner(input)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()

            return if (hasInput) {
                JSONObject(scanner.next())
            } else
                null

        } catch (e: UnknownHostException) {

        } catch (e: SocketTimeoutException) {

        } catch (e: NullPointerException) {

        } catch (e: IOException) {

        } finally {
            urlConnection?.disconnect()
        }

        return null
    }


    /**
     * Получение данных Long Poll сервера
     */
    private fun getLongPollServer(): JSONObject? {
        val url = Requests.getURLGetLongPollServer(groupID, tokenGroup)
        var response: JSONObject? = null

        if (url != null)
            response = getResponse(url)

        return response
    }


    /**
     * Отправка запроса Long Poll серверу
     */
    private fun getResponseLongPollServer(): String? {
        try {
            val url = Requests.getURLLongPollServerRequest(
                    server!!,
                    key!!,
                    ts!!,
                    waitTimeResponse
            )

            var inputLine: String?
            var result: String? = ""
            val urlConnection: URLConnection = url!!.openConnection()
            urlConnection.doOutput = true

            val reader = BufferedReader(InputStreamReader(urlConnection.getInputStream(), "UTF8"))

            while (reader.readLine().also { inputLine = it } != null) {
                result += inputLine
            }

            reader.close()

            return result

        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * Функция непрерывного обращения к Long Poll серверу для
     * получения обновлений о событиях бота
     */
    fun start() {
        var json: JSONObject
        var rec: JSONObject
        var updates: JSONArray

        while (true) {

            json = JSONObject(getResponseLongPollServer()!!)

            if (json.has("failed")) {
                println("Ответ от сервера: $json")
                return
            }

            updates = json.getJSONArray("updates")

            if (updates.length() != 0) {

                ts = json.getString("ts")

                for (i in 0 until updates.length()) {
                    rec = updates.getJSONObject(i)
                    responseToEvents(rec.getJSONObject("object"), rec.getString("type"))
                }
            }
        }
    }


    /**
     * Отправка сообщения пользователю по ID
     * message - текст сообщения
     * userID - ID пользователя
     */
    fun sendMessageToID(
            message: String,
            userID: String): JSONObject? {
        val url = Requests.getUrlSendMessageToID(message, userID, tokenGroup)

        var response: JSONObject? = null

        if (url != null)
            response = getResponse(url)

        return response
    }


    /**
     * Реакции на события группы
     * response - ответ Long Poll сервера
     * type
     */
    private fun responseToEvents(response: JSONObject, type: String?) {
        when (type) {
            "message_typing_state" -> {
            }
            "message_new" -> {
            }
            "message_reply" -> {
            }
            "message_edit" -> {
            }
            "message_allow" -> {
            }
            "message_deny" -> {
            }
            "photo_new" -> {
            }
            "photo_comment_new" -> {
            }
            "photo_comment_edit" -> {
            }
            "photo_comment_restore" -> {
            }
            "photo_comment_delete" -> {
            }
            "audio_new" -> {
            }
            "video_new" -> {
            }
            "video_comment_new" -> {
            }
            "video_comment_edit" -> {
            }
            "video_comment_restore" -> {
            }
            "video_comment_delete" -> {
            }
            "wall_post_new" -> {
            }
            "wall_repost" -> {
            }
            "wall_reply_new" -> {
            }
            "wall_reply_edit" -> {
            }
            "wall_reply_restore" -> {
            }
            "wall_reply_delete" -> {
            }
            "board_post_new" -> {
            }
            "board_post_edit" -> {
            }
            "board_post_restore" -> {
            }
            "board_post_delete" -> {
            }
            "market_comment_new" -> {
            }
            "market_comment_edit" -> {
            }
            "market_comment_restore" -> {
            }
            "market_comment_delete" -> {
            }
            "group_leave" -> {
            }
            "group_join" -> {
            }
            "user_block" -> {
            }
            "user_unblock" -> {
            }
            "poll_vote_new" -> {
            }
            "group_officers_edit" -> {
            }
            "group_change_settings" -> {
            }
            "group_change_photo" -> {
            }
        }
    }
}