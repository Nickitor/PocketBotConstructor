package com.uneasypixel.pocketbotconstructor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError


class EntryActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        findViewById<Button>(R.id.button_entry_enter).setOnClickListener {
            VKSdk.login(this, VKScope.WALL)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        if(!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                    override fun onResult(res: VKAccessToken?) {
                        setResult(RESULT_OK, null)
                        finish()
                    }

                    override fun onError(error: VKError?) {
                        findViewById<TextView>(R.id.textView_entry_prompt).text = resources.getString(R.string.account_login_error)
                    }

                })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {

    }
}