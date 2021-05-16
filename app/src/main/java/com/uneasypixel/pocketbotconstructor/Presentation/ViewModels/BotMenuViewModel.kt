package com.uneasypixel.pocketbotconstructor.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Server
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class BotMenuViewModel(

) : ViewModel() {

    private var server: Server = Server(
        "193525063",
        "88a1d21f807fe5a534ebd62721612411fe5e2fcdd6d15a65fb879b84754f91d60d25f68b0cc116b9c3f78",
        waitTimeResponse = "25"
    )
    private var isRunning: Boolean = false
    private lateinit var dependencyFactory: DependencyFactory

    fun switchLongPollServer(DependencyFactory: DependencyFactory): Boolean {
        dependencyFactory = DependencyFactory

        if (isRunning)
            stopLongPollServer()
        else
            startLongPollServer()

        return isRunning
    }

    private fun startLongPollServer() {
        isRunning = true

        GlobalScope.launch {

            val getLongPollServerUseCase = dependencyFactory.provideGetLongPollServerUseCase()
            val getResponseLongPollServerUseCase = dependencyFactory.provideGetResponseLongPollServerUseCase()

            val longPollServer = getLongPollServerUseCase.getLongPollServer(server.groupID, server.tokenGroup)

            server.key = longPollServer.key
            server.server = longPollServer.server
            server.ts = longPollServer.ts

            while (isRunning) {

                var response = getResponseLongPollServerUseCase.getResponseLongPollServer(server)
                var event: JSONObject
                var updates: JSONArray

                if (!response!!.has("failed")) {

                    updates = response.getJSONArray("updates")

                    if (updates.length() != 0) {

                        server.ts = response.getString("ts")

                        for (i in 0 until updates.length()) {
                            response = updates.getJSONObject(i)
                            responseToEvents(response.getJSONObject("object"), response.getString("type"))
                        }
                    }
                }
            }
        }
    }


    private fun stopLongPollServer() {
        isRunning = false
    }


    /**
     * Реакции на события группы
     * response - ответ Long Poll сервера
     * type
     */
    private suspend fun responseToEvents(response: JSONObject, type: String?) {
        when (type) {
            // Входящее сообщение
            "message_new" -> {
                val sendMessageToUserUseCase = dependencyFactory.provideSendMessageToUserUseCase()

                sendMessageToUserUseCase.sendMessageToUser("Hello!",
                response.getJSONObject("message").getString("from_id"),
                server.tokenGroup)
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