package com.example.milio.Interface

import com.example.milio.Model.*
import retrofit2.Call
import retrofit2.http.*

interface API {
   @POST("chat-service/api/v1/chat/user/list")
   fun chatlistResponse( @Header("Authorization") Authorization: String,
                         @Body request: ChatRequest):Call<ChatListResponse>

   @POST("service-core-milio/api/v1/user/login")
   fun userLogin(
       @Body data: LoginRequest
       ): Call<LoginResponse>
    @POST("api/v1/user/profile")
    fun getUserProfile(
        @Header("Authorization") Authorization: String,
        @Body request: ProfileRequest): Call<ProfileResponse>
    @POST("chat-service/api/v1/chat/room/find-by-id")
    fun getMessageChat(
        @Header("Authorization") Authorization: String,
        @Body request: MessageChatRequest): Call<MessageChatResponse>
}