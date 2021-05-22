package com.uneasypixel.pocketbotconstructor

import com.uneasypixel.pocketbotconstructor.data.api.*
import com.uneasypixel.pocketbotconstructor.data.gateway.*
import com.uneasypixel.pocketbotconstructor.data.interfaces.*
import com.uneasypixel.pocketbotconstructor.data.storage.GetBotsStorageImp
import com.uneasypixel.pocketbotconstructor.data.storage.SaveBotsStorageImp
import com.uneasypixel.pocketbotconstructor.domain.interfaces.*
import com.uneasypixel.pocketbotconstructor.domain.usecases.*

class DependencyFactory {

    // SendMessageToUserUseCase
    fun provideSendMessageToUserUseCase() : SendMessageToUserUseCase {
        return SendMessageToUserUseCase(provideSendMessageGateWay())
    }

    private fun provideSendMessageGateWay() : ISendMessageGateway {
        return SendMessageGatewayImp(provideSendMessageApi())
    }

    private fun provideSendMessageApi() : ISendMessageAPI {
        return SendMessageAPIImp(provideSendRequestApiIml())
    }

    private fun provideSendRequestApiIml() : ISendRequestAPI {
        return SendRequestAPIImp()
    }


    // GetConversationsUseCase
    fun provideGetConversationsUseCase() : GetConversationsUseCase {
        return GetConversationsUseCase(provideGetConversationsGateWay())
    }

    private fun provideGetConversationsGateWay() : IGetConversationsGateway {
        return GetConversationsGatewayImp(provideGetConversationsApi())
    }

    private fun provideGetConversationsApi() : IGetConversationsAPI {
        return GetConversationsAPIImp(provideGetConversationsApiIml())
    }

    private fun provideGetConversationsApiIml() : ISendRequestAPI {
        return SendRequestAPIImp()
    }

    // SendMessageToConversations
    fun provideSendMessageToConversationsUseCase() : SendMessageToConversationsUseCase {
        return SendMessageToConversationsUseCase(
            provideGetConversationsUseCase(),
            provideSendMessageToUserUseCase()
        )
    }


    // GetLongPollServer
    fun provideGetLongPollServerUseCase() : GetLongPollServerUseCase {
        return GetLongPollServerUseCase(provideGetLongPollServerGateWay())
    }

    private fun provideGetLongPollServerGateWay() : IGetLongPollServerGateway {
        return GetLongPollServerGatewayImp(provideGetLongPollServerApi())
    }

    private fun provideGetLongPollServerApi() : IGetLongPollServerAPI {
        return GetLongPollServerAPIImp(provideGetLongPollServerApiIml())
    }

    private fun provideGetLongPollServerApiIml() : ISendRequestAPI {
        return SendRequestAPIImp()
    }


    // GetResponseLongPollServer
    fun provideGetResponseLongPollServerUseCase() : GetResponseLongPollServerUseCase {
        return GetResponseLongPollServerUseCase(provideGetResponseLongPollServerGateWay())
    }

    private fun provideGetResponseLongPollServerGateWay() : IGetResponseLongPollServerGateway {
        return GetResponseLongPollServerGatewayImp(provideGetResponseLongPollServerApi())
    }

    private fun provideGetResponseLongPollServerApi() : IGetResponseLongPollServerAPI {
        return GetResponseLongPollServerAPIImp()
    }


    // GetBotsUseCase
    fun provideGetBotsUseCase() : GetBotsUseCase {
        return GetBotsUseCase(provideGetBotsGateWay())
    }

    private fun provideGetBotsGateWay() : IGetBotsGateway {
        return GetBotsGatewayImp(provideGetBotsStorage())
    }

    private fun provideGetBotsStorage() : IGetBotsStorage {
        return GetBotsStorageImp()
    }


    // SaveBotsUseCase
    fun provideSaveBotsUseCase() : SaveBotsUseCase {
        return SaveBotsUseCase(provideSaveBotsGateWay())
    }

    private fun provideSaveBotsGateWay() : ISaveBotsGateway {
        return SaveBotsGatewayImp(provideSaveBotsStorage())
    }

    private fun provideSaveBotsStorage() : ISaveBotsStorage {
        return SaveBotsStorageImp()
    }
}