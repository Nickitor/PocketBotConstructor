package com.uneasypixel.pocketbotconstructor

import com.uneasypixel.pocketbotconstructor.Data.API.SendMessageAPIImp
import com.uneasypixel.pocketbotconstructor.Data.API.SendRequestAPIImp
import com.uneasypixel.pocketbotconstructor.Data.Gateway.GetBotsGatewayImp
import com.uneasypixel.pocketbotconstructor.Data.Gateway.SendMessageGatewayImp
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetBotsStorage
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISendMessageAPI
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISendRequestAPI
import com.uneasypixel.pocketbotconstructor.Data.Storage.GetBotsStorageImp
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.ISendMessageGateway
import com.uneasypixel.pocketbotconstructor.Domain.UseCase.GetBotsUseCase
import com.uneasypixel.pocketbotconstructor.Domain.UseCase.SendMessageToUserUseCase

class DependencyFactory {
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


    fun provideGetBotsUseCase() : GetBotsUseCase {
        return GetBotsUseCase(provideGetBotsGateWay())
    }

    private fun provideGetBotsGateWay() : IGetBotsGateway {
        return GetBotsGatewayImp(provideGetBotsStorage())
    }

    private fun provideGetBotsStorage() : IGetBotsStorage {
        return GetBotsStorageImp()
    }
}