package com.uneasypixel.pocketbotconstructor.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.domain.entities.Stats
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StatisticsMenuViewModel : ViewModel() {

    var bot: Bot? = null

    var stats = MutableLiveData<Stats>()

    var isUpdating = false

    init {
        stats.value = Stats()
    }

    fun getStats(dependencyFactory: DependencyFactory) {

        isUpdating = true

        GlobalScope.launch {

            while (isUpdating) {
                val response = async {
                    dependencyFactory.provideGetStatsUseCase()
                        .getStats(bot!!.groupID, bot!!.token)
                }

                var result = viewModelScope.async {
                    stats.value = response.await()
                }

                result.await()
            }
        }
    }
}