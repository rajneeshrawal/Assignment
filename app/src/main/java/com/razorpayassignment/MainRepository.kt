package com.razorpayassignment

import com.networklib.NetworkInterface
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.BufferedInputStream
import java.net.URL


class MainRepository : NetworkInterface {

    override fun fetchCustomUI(url: String): JSONObject {
        val request = Request.Builder()
            .url(url)
            .build()
        val response = OkHttpClient().newCall(request).execute()
        return if (response.body != null) {
            JSONObject(response.body!!.string())
        } else {
            JSONObject()
        }
    }

    override fun fetchLogo(url: String): ByteArray {
        val imageUrl = URL(url)
        val connection = imageUrl.openConnection()
        connection.connect()
        val inputStream = connection.getInputStream()
        val bufferedInputStream = BufferedInputStream(inputStream)
        return bufferedInputStream.readBytes()
    }
}