package com.uneasypixel.pocketbotconstructor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.domain.entities.Server
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ServerViewModel() : ViewModel() {

    private lateinit var listOfBotsViewModel: ListOfBotsViewModel
    private lateinit var dependencyFactory: DependencyFactory

    fun initServer(viewModel: ListOfBotsViewModel, DependencyFactory: DependencyFactory) {
        listOfBotsViewModel = viewModel
        dependencyFactory = DependencyFactory
    }

    fun start(Bot: Bot) {

        if (!Bot.isRunning) {
            Bot.isEnabled = true
            Bot.isRunning = true

            val botDTO = getBotDTO(Bot)
            if (botDTO != null) {
                botDTO.isEnabled = true
                botDTO.isRunning = true
            }

            GlobalScope.launch {
                startLongPollServer(Bot)
            }
        }
    }

    fun getBotDTO(Bot: Bot): BotDTO? {
        for (bot in listOfBotsViewModel.listOfBotsDTO)
            if (bot.name == Bot.name)
                return bot

        return null
    }


    fun stop(Bot: Bot) {
        Bot.isEnabled = false
        Bot.isRunning = false

        val botDTO = getBotDTO(Bot)
        if (botDTO != null) {
            botDTO.isEnabled = false
            botDTO.isRunning = false
        }
    }

    private suspend fun startLongPollServer(Bot: Bot) {

        val server: Server = Server(waitTimeResponse = "40")

        val longPollServer = dependencyFactory.provideGetLongPollServerUseCase()
            .getLongPollServer(Bot.groupID, Bot.token)

        server.key = longPollServer.key
        server.server = longPollServer.server
        server.ts = longPollServer.ts

        val getResponseLongPollServerUseCase =
            dependencyFactory.provideGetResponseLongPollServerUseCase()

        while (Bot.isEnabled) {

            var response = getResponseLongPollServerUseCase.getResponseLongPollServer(server)
            var updates: JSONArray

            if (!response!!.has("failed")) {

                updates = response.getJSONArray("updates")

                if (updates.length() != 0) {

                    server.ts = response.getString("ts")

                    for (i in 0 until updates.length()) {
                        response = updates.getJSONObject(i)
                        responseToEvents(
                            Bot,
                            response.getJSONObject("object"),
                            response.getString("type")
                        )
                    }
                }
            }

            if (getBotDTO(Bot) == null)
                Bot.isEnabled = false
        }
    }


    private suspend fun responseToEvents(Bot: Bot, response: JSONObject, type: String?) {
        when (type) {
            // Входящее сообщение
            "message_new" -> {
                val sendMessageToUserUseCase = dependencyFactory.provideSendMessageToUserUseCase()

                try {
                    val message = response.getJSONObject("message").getString("text")
                    for (phrase in Bot.reactionsToPhrases) {
                        if (message == phrase.phrase) {
                            val fromId = response.getJSONObject("message").getString("from_id")
                            val answerList = phrase.response
                            val ind = (0 until answerList.size).random()
                            val answer = phrase.response[ind]
                            sendMessageToUserUseCase.sendMessageToUser(
                                answer,
                                fromId,
                                Bot.token
                            )
                        }
                    }
                } catch (e: JSONException) {

                }
            }
            // Новое исходящее сообщение
            "message_reply" -> {
            }
            // Редактирование сообщения
            "message_edit" -> {
            }
            // Подписка на сообщения от сообщества
            "message_allow" -> {
                try {
                    if (Bot.reactionsToEvents[0].response.isNotEmpty()) {

                        val sendMessageToUserUseCase =
                            dependencyFactory.provideSendMessageToUserUseCase()
                        val fromId = response.getString("user_id")

                        val answerList = Bot.reactionsToEvents[0].response

                        val ind = (0 until answerList.size).random()

                        val answer = answerList[ind]

                        sendMessageToUserUseCase.sendMessageToUser(
                            answer,
                            fromId,
                            Bot.token
                        )
                    }
                } catch (e: JSONException) {

                }
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
                try {
                    if (Bot.reactionsToEvents[1].response.isNotEmpty()) {

                        val sendMessageToUserUseCase =
                            dependencyFactory.provideSendMessageToUserUseCase()
                        val fromId = response.getString("from_id")

                        val answerList = Bot.reactionsToEvents[1].response

                        val ind = (0 until answerList.size).random()

                        val answer = answerList[ind]

                        sendMessageToUserUseCase.sendMessageToUser(
                            answer,
                            fromId,
                            Bot.token
                        )
                    }
                } catch (e: JSONException) {

                }
            }
            // Добавление комментария на стене
            "wall_reply_new" -> {
                try {
                    if (Bot.reactionsToEvents[2].response.isNotEmpty()) {

                        val sendMessageToUserUseCase =
                            dependencyFactory.provideSendMessageToUserUseCase()
                        val fromId = response.getString("from_id")

                        val answerList = Bot.reactionsToEvents[2].response

                        val ind = (0 until answerList.size).random()

                        val answer = answerList[ind]

                        sendMessageToUserUseCase.sendMessageToUser(
                            answer,
                            fromId,
                            Bot.token
                        )
                    }
                } catch (e: JSONException) {

                }
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
                try {
                    if (Bot.reactionsToEvents[3].response.isNotEmpty()) {

                        val sendMessageToUserUseCase =
                            dependencyFactory.provideSendMessageToUserUseCase()
                        val fromId = response.getString("liker_id")

                        val answerList = Bot.reactionsToEvents[3].response

                        val ind = (0 until answerList.size).random()

                        val answer = answerList[ind]

                        sendMessageToUserUseCase.sendMessageToUser(
                            answer,
                            fromId,
                            Bot.token
                        )
                    }
                } catch (e: JSONException) {

                }
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
