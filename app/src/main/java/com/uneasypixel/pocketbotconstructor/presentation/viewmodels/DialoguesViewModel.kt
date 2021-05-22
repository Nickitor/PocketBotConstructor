package com.uneasypixel.pocketbotconstructor.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.domain.entities.Conversation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DialoguesViewModel : ViewModel() {
    var bot: Bot? = null

    var conversations = MutableLiveData<MutableList<Conversation>>()

    var isUpdating = false

    init {
        conversations.value = mutableListOf()
    }

    fun getConversations(dependencyFactory: DependencyFactory) {

        isUpdating = true

        GlobalScope.launch {

            while (isUpdating) {
                val conversationsIds = async {
                    dependencyFactory.provideGetConversationsUseCase()
                        .getConversationsIds(bot!!.token)
                }

                val response = async {
                    dependencyFactory.provideGetConversationsUseCase()
                        .getConversationsById(conversationsIds.await(), bot!!.token)
                }

                var result = viewModelScope.async {
                    conversations.value = response.await()
                }

                result.await()
            }
        }
    }
}