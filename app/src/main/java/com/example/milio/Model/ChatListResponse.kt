package com.example.milio.Model

data class ChatListResponse(val httpCode:Int?=0,val message:MessageUser,val payload:PayLoad)
data class ChatRequest(var owner_id: String? = "")