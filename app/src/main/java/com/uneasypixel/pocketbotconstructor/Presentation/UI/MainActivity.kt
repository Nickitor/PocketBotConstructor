/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ КЛАСС ГЛАВНОГО ACTIVITY, КОТОРЫЙ ЗАНИМАЕТСЯ КОМПОНОВКОЙ
// ФРАГМЕНТОВ НА ЭКРАНЕ. ПРАВИЛА ПЕРЕХОДОВ МЕЖДУ ФРАГМЕНТАМИ ОПИСАНЫ
// С ПОМОЩЬЮ НАВИГАЦИОННОГО ГРАФА В ФАЙЛЕ res/navigation/nav_graph.xml
// *********************************************************************
package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.ActivityMainBinding
import com.uneasypixel.pocketbotconstructor.Data.Gateway.Server
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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


        //https://oauth.vk.com/authorize?client_id=7793844&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.130&state=123456

        GlobalScope.launch {

            val groupID = "193525063"
            val tokenUser = "e8fda682b660d9f970df4d960fe68099ee385b6c55cdae63cf03181793090a5a5f2ffac02acb22f38c237"
            val tokenGroup = "88a1d21f807fe5a534ebd62721612411fe5e2fcdd6d15a65fb879b84754f91d60d25f68b0cc116b9c3f78"

            val server = Server(groupID, tokenUser, tokenGroup, "90")

            server.start()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}