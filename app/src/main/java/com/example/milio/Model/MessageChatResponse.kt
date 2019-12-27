package com.example.milio.Model

data class RecordRoomID(val _id: String?="",val room_id:String?="",val text: String?="",val content:String?="",val user: Users,val type: String?="", var privacy:String?="",var created_at: Long)
data class PayLoadRoomID(val records:ArrayList<RecordRoomID>,val total_items:Int?=0,val total_num_page:Int?=0,val page_size:Int?=0,
                         val page:Int?=0,val limit:Int?=0,val skip:Int?=0)
data class MessageChatRequest(val room_id: String?="",val user_id:String?="")
data class MessageChatResponse(val httpCode: Int? = 0, val message:Message, val payload: PayLoadRoomID)