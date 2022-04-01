package com.revature.expiration_date.network

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("item") val item: String,
    @SerializedName("category") val category: String,
    @SerializedName("location") val location: String,
    @SerializedName("expiration") val expiration: String,
)

data class RequestProduct(
    @SerializedName("location") val location: String,
    @SerializedName("choices") val choices: List<String>
)

//object request: RequestProduct(
//    location = "Which location do you want to look at?",
//    choices = listOf("Fridge", "Freezer", "Pantry", "All")
//)

data class Items(val items: List<Product>)