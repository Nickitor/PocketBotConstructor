package com.uneasypixel.pocketbotconstructor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.uneasypixel.pocketbotconstructor.domain.entities.DialogScript

class AddNewReactionToEventViewModel : ViewModel() {

    var event: DialogScript? = null
    var eventPos: Int? = null

}