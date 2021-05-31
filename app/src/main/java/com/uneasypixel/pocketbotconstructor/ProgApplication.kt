/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ КЛАСС ПРИЛОЖЕНИЯ. ДАННЫЙ КЛАСС ЗАПУСКАЕТСЯ ДО СОЗДАНИЯ
// КЛАССА ГЛАВНОЙ ACTIVITY И НЕ СОДЕРЖИТ ГРАФИЧЕСКОГО ПРЕДСТАВЛЕНИЯ.
// *********************************************************************
package com.uneasypixel.pocketbotconstructor

import android.app.Application

class ProgApplication : Application() {

    lateinit var dependencyFactory: DependencyFactory
        private set

    override fun onCreate() {
        super.onCreate()
        dependencyFactory = DependencyFactory()
    }
}