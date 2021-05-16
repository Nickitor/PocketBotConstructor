package com.uneasypixel.pocketbotconstructor.Data.API

import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetResponseLongPollServerAPI
import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.MalformedURLException
import java.net.URLConnection

class GetResponseLongPollServerAPIImp() : IGetResponseLongPollServerAPI {

    override suspend fun getResponseLongPollServer(server: ServerDAO): JSONObject? {

        try {
            val url = URLBuilder.getURLLongPollServerRequest(
                server?.server!!,
                server?.key!!,
                server?.ts!!,
                server?.waitTimeResponse!!
            )

            var inputLine: String?
            var result: String? = ""
            val urlConnection: URLConnection = url!!.openConnection()
            urlConnection.doOutput = true

            val reader = BufferedReader(InputStreamReader(urlConnection.getInputStream(), "UTF8"))

            while (reader.readLine().also { inputLine = it } != null) {
                result += inputLine
            }

            reader.close()

            return JSONObject(result)

        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }
}