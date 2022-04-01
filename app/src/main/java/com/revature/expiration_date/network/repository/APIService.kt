package com.revature.expiration_date.network.repository

import com.revature.expiration_date.network.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("authentication")
    suspend fun getLogin(@Body login: Login): Response<Token>

    @POST("listOfAllProducts")
    suspend fun getAllProducts(@Body request: RequestProduct): Response<Items>

    @POST("remove")
    suspend fun removeItem(@Body item: Product): Response<Token>

    @POST("expired")
    suspend fun getExpiredProducts(): Response<ExpiredItems>
}