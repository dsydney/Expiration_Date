package com.revature.expiration_date.network

import com.google.gson.annotations.SerializedName

data class ExpiredItem(
    @SerializedName("name") val name: String,
    @SerializedName("days_expired") val days_expired: String,
)
