package com.example.milio.Model

data class PayLoad ( val records:ArrayList<Record>,val total_items:Int?=0,val total_num_page:Int?=0,val page_size:Int?=0,
                     val page:Int?=0,val limit:Int?=0,val skip:Int?=0
                     )
data class Users(val firstname:String?="",val lastname:String?="",val profilePic:String?="",val officialAccount:Boolean,val role:String?="",val nickname:String?="")
data class Record(val _id:String?="",val user:Users,val message:MessageUser,val cover:String?="",val privacy:String?="",val created_at:Long,val name:String?="")
data class MessageUser (var _id: String? ="",
                        var text: String?="",
                        var type: String?="",
                        var status:String?="",
                        var created_at: Long,
                        var user: Users)

