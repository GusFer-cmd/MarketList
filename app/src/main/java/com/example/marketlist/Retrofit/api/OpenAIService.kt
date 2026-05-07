package com.example.marketlist.Retrofit.api

import com.example.marketlist.Retrofit.dto.ChatRequest
import com.example.marketlist.Retrofit.dto.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIService {
    @POST("v1/chat/completions")
    suspend fun generateItems(
        @Header("Authorization") token: String,
        @Body request: ChatRequest
    ): ChatResponse
}