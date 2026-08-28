package com.example.ktcounter

//package com.example.ktcounter

import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): DogCEOImage
}