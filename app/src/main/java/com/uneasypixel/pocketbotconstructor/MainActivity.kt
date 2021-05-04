package com.uneasypixel.pocketbotconstructor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val ENTRY_ACTIVITY_KEY = 0
    private val MAIN_SCREEN_ACTIVITY_KEY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val entryIntent = Intent(this@MainActivity, EntryActivity::class.java)
        startActivityForResult(entryIntent, ENTRY_ACTIVITY_KEY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ENTRY_ACTIVITY_KEY) {
            if (resultCode == RESULT_OK) {
                val mainScreenIntent = Intent(this@MainActivity, MainScreenActivity::class.java)
                startActivityForResult(mainScreenIntent, MAIN_SCREEN_ACTIVITY_KEY)
            } else {

            }
        }
    }
}

