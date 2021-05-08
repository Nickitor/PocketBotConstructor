/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ КЛАСС ГЛАВНОГО ACTIVITY, КОТОРЫЙ ЗАНИМАЕТСЯ КОМПОНОВКОЙ
// ФРАГМЕНТОВ НА ЭКРАНЕ. ПРАВИЛА ПЕРЕХОДОВ МЕЖДУ ФРАГМЕНТАМИ ОПИСАНЫ
// С ПОМОЩЬЮ НАВИГАЦИОННОГО ГРАФА В ФАЙЛЕ res/navigation/nav_graph.xml
// *********************************************************************
package com.uneasypixel.pocketbotconstructor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.uneasypixel.pocketbotconstructor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Объект навигационного контроллера для перехода между фрагментами
    private lateinit var navController: NavController

    // Точка входа в Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Объект привязки для получения объектов интерфейса
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // Установка layout
        setContentView(binding.root)

        // Установка стартового фрагмента
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}