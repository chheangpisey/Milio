package com.example.milio.Model


data class LoginResponse(val httpCode: Int? = 0, val message:Message, val data: User)
data class LoginRequest(var email: String, var password: String)
data class Message(val code:Int?=0,val desc:Description)
data class Description(val kh:String?="",val en:String?="")

