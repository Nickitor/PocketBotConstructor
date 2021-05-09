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
            // Входящее сообщение
            "message_new" -> {
            }
            // Новое исходящее сообщение
            "message_reply" -> {
            }
            // Редактирование сообщения
            "message_edit" -> {
            }
            // Подписка на сообщения от сообщества
            "message_allow" -> {
            }
            // Новый запрет сообщений от сообщества
            "message_deny" -> {
            }
            // Статус набора текста
            "message_typing_state" -> {
            }
            // Действие с сообщением. Используется для работы с Callback-кнопками
            "message_event" -> {
            }
            // Добавление фотографии
            "photo_new" -> {
            }
            // Добавление комментария к фотографии
            "photo_comment_new" -> {
            }
            // Редактирование комментария к фотографии
            "photo_comment_edit" -> {
            }
            // Восстановление комментария к фотографии
            "photo_comment_restore" -> {
            }
            // Удаление комментария к фотографии
            "photo_comment_delete" -> {
            }
            // Добавление аудио
            "audio_new" -> {
            }
            // Добавление видео
            "video_new" -> {
            }
            // Комментарий к видео
            "video_comment_new" -> {
            }
            // Редактирование комментария к видео
            "video_comment_edit" -> {
            }
            // Восстановление комментария к видео
            "video_comment_restore" -> {
            }
            // Удаление комментария к видео
            "video_comment_delete" -> {
            }
            // Запись на стене
            "wall_post_new" -> {
            }
            // Репост записи из сообщества
            "wall_repost" -> {
            }
            // Добавление комментария на стене
            "wall_reply_new" -> {
            }
            // Редактирование комментария на стене
            "wall_reply_edit" -> {
            }
            // Восстановление комментария на стене
            "wall_reply_restore" -> {
            }
            // Удаление комментария на стене
            "wall_reply_delete" -> {
            }
            // Событие о новой отметке "Мне нравится"
            "like_add" -> {
            }
            // Событие о снятии отметки "Мне нравится"
            "like_remove" -> {
            }
            // Создание комментария в обсуждении
            "board_post_new" -> {
            }
            // Редактирование комментария
            "board_post_edit" -> {
            }
            // Восстановление комментария
            "board_post_restore" -> {
            }
            // Удаление комментария в обсуждении
            "board_post_delete" -> {
            }
            // Новый комментарий к товару
            "market_comment_new" -> {
            }
            // Редактирование комментария к товару
            "market_comment_edit" -> {
            }
            // Восстановление комментария к товару
            "market_comment_restore" -> {
            }
            // Удаление комментария к товару
            "market_comment_delete" -> {
            }
            // Новый заказ
            "market_order_new" -> {
            }
            // Редактирование заказа
            "market_order_edit" -> {
            }
            // Удаление участника из сообщества
            "group_leave" -> {
            }
            // Добавление участника или заявки на вступление в сообщество
            "group_join" -> {
            }
            // Добавление пользователя в чёрный список
            "user_block" -> {
            }
            // Добавление голоса в публичном опросе
            "poll_vote_new" -> {
            }
            // Редактирование списка руководителей
            "group_officers_edit" -> {
            }
            // Изменение настроек сообщества
            "group_change_settings" -> {
            }
            // Изменение главного фото
            "group_change_photo" -> {
            }
            // Платёж через VK Pay
            "vkpay_transaction" -> {
            }
            // Событие в VK Mini Apps
            "app_payload" -> {
            }
            // Создание подписки
            "donut_subscription_create" -> {
            }
            // Продление подписки
            "donut_subscription_prolonged" -> {
            }
            // Подписка истекла
            "donut_subscription_expired" -> {
            }
            // Отмена подписки
            "donut_subscription_cancelled" -> {
            }
            // Изменение стоимости подписки
            "donut_subscription_price_changed" -> {
            }
            // Вывод денег
            "donut_money_withdraw" -> {
            }
            // Ошибка вывода денег
            "donut_money_withdraw_error" -> {
            }
        }
    }
}