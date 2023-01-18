package com.networklib

import org.json.JSONObject

public abstract interface NetworkInterface {
    fun fetchCustomUI(url: String): JSONObject?
    fun fetchLogo(url: String): ByteArray?
}