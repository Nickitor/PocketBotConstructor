package com.uneasypixel.pocketbotconstructor.Data.API

import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISendRequestAPI
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import java.net.UnknownHostException
import java.util.*

class SendRequestAPIImp : ISendRequestAPI {

    override suspend fun sendRequest(url: URL): JSONObject? {

        val urlConnection = url.openConnection() as? HttpURLConnection

        try {
            val input = urlConnection?.inputStream

            val scanner = Scanner(input)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()

            return if (hasInput) {
                JSONObject(scanner.next())
            } else
                null

        } catch (e: UnknownHostException) {

        } catch (e: SocketTimeoutException) {

        } catch (e: NullPointerException) {

        } catch (e: IOException) {

        } finally {
            urlConnection?.disconnect()
        }

        return null
    }

}