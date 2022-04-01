package com.revature.expiration_date.network.repository

import com.revature.expiration_date.network.ExpiredItem
import com.revature.expiration_date.network.Login
import com.revature.expiration_date.network.Product
import com.revature.expiration_date.network.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("authentication")
    suspend fun getLogin(@Body login: Login): Response<Token>

//    @POST("listOfAllProducts")
//    suspend fun getAllProducts(@Body): Response<Items>
//
//    @POST("remove")
//    suspend fun getLogin(@Body)
//
//    @POST("expired")
//    suspend fun getLogin(@Body): Response<ExpiredItem>
}