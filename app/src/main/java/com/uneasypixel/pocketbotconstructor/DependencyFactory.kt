package com.uneasypixel.pocketbotconstructor

import com.uneasypixel.pocketbotconstructor.Data.API.SendMessageAPIImp
import com.uneasypixel.pocketbotconstructor.Data.API.SendRequestAPIImp
import com.uneasypixel.pocketbotconstructor.Data.Gateway.SendMessageGatewayImp
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISendMessageAPI
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISendRequestAPI
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.ISendMessageGateway
import com.uneasypixel.pocketbotconstructor.Domain.UseCase.SendMessageToUserUseCase

class DependencyFactory {
    fun provideSendMessageToUserUseCase() : SendMessageToUserUseCase {
        return SendMessageToUserUseCase(provideSendMessageGateWay())
    }

    fun provideSendMessageGateWay() : ISendMessageGateway {
        return SendMessageGatewayImp(provideSendMessageApi())
    }

    fun provideSendMessageApi() : ISendMessageAPI {
        return SendMessageAPIImp(provideSendRequestApiIml())
    }

    fun provideSendRequestApiIml() : ISendRequestAPI {
        return SendRequestAPIImp()
    }
}