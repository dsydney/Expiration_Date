package com.revature.expiration_date.network

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("item") val item: String,
    @SerializedName("category") val category: String,
    @SerializedName("location") val location: String,
    @SerializedName("expiration") val expiration: String,
) {
    override fun toString(): String {
        return "\n$item ($category) from $location"
    }
}

data class RequestProduct(
    @SerializedName("location") val location: String,
    @SerializedName("choices") val choices: List<String>
)

data class Items(val items: List<Product>)