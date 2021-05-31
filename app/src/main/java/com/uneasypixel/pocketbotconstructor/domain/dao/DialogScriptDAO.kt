package com.uneasypixel.pocketbotconstructor.domain.dao

import com.uneasypixel.pocketbotconstructor.domain.entities.SetOfPhrases

interface DialogScriptDAO {
    var phrase: String?
    var response: String
    var setOfPhrases: SetOfPhrases
}