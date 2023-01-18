package com.razorpayassignment

import com.google.gson.annotations.SerializedName

data class CustomUI(
    @SerializedName("logo-url") var logo_url: String? = null,
    @SerializedName("heading-text") var heading_text: String? = null,
    @SerializedName("uidata") var uidata: ArrayList<Uidata> = arrayListOf()
)

data class Uidata(
    @SerializedName("uitype") var uitype: String? = null,
    @SerializedName("value") var value: String? = null,
    @SerializedName("key") var key: String? = null,
    @SerializedName("hint") var hint: String? = null
)
