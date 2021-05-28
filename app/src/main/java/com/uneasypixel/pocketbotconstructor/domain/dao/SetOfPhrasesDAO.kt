package com.uneasypixel.pocketbotconstructor.domain.dao

interface SetOfPhrasesDAO {

    var name: String

    var groupSources: MutableList<String>
    var textSources : MutableList<String>

    var isTextResource : Boolean
}