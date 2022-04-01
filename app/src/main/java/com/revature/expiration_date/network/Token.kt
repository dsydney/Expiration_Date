package com.revature.expiration_date.network

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("accessToken") val accessToken: String
)