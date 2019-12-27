package com.example.milio.Model

data class ProfileResponse(val data:Data,val message:Message,val httpCode:Int?=0)
data class Data(val _id:String?="",
                val pre_mongified_id:Int?=0,
                val firstname:String?="",
                val lastname:String?="",
                val gender:String?="",
                val dob:String?="",
                val email:String?="",
                val username:String?="",
                val createdAt:Long,
                val profilePic:String?="",
                val accountType:String?="",
                val bio:String?="",
                val coverPic:String?="",
                val country:String?="",
                val studyAt:String?="",
                val liveAt:String?="",
                val marital:String?="",
                val workAt:String?="",
                val nickName:String?="",
                val officialAccount:Boolean,
                val status:String?="",
                val notifRead:Boolean,
                val role:String?="",
                val old_milio_user:Boolean,
                val blockchain_account:Block,
                val appUpdate:Boolean,
                val reference:String?="",
                val totalFollower:Int?=0,
                val totalFriend:Int?=0,
                val myReferralCode:MyRefCode
)

data class Block(val status:Boolean,val address:String?="")
data class MyRefCode(val code:String?="")
data class ProfileRequest(var userId:String? = "")
