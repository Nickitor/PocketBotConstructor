package com.uneasypixel.pocketbotconstructor.data.api

import com.uneasypixel.pocketbotconstructor.data.interfaces.ISendRequestAPI
import org.json.JSONObject
import java.io.IOException
import java.net.*
import java.util.*

class SendRequestAPIImp : ISendRequestAPI {

    override suspend fun sendRequest(url: URL): JSONObject? {

        var urlConnection : HttpURLConnection? = null
        try {
            urlConnection = url.openConnection() as? HttpURLConnection?
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

        } catch (e : UnknownServiceException) {

        }
        finally {
            urlConnection?.disconnect()
        }

        return null
    }

}