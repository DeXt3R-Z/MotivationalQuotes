package com.example.motivational_quotes

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET ("quotes/random")
    suspend fun getQuotes() : Response<Quotes>

}










