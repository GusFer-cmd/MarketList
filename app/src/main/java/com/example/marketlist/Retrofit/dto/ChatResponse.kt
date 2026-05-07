package com.example.marketlist.Retrofit.dto

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: MessageResponse
)

data class MessageResponse(
    val content: String
)