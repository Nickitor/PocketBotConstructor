package com.uneasypixel.pocketbotconstructor.domain.dao

interface DialogScriptDAO {
    var phrase : String?
    var response : MutableList<String>
}