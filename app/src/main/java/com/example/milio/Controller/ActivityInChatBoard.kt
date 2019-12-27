package com.example.milio.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milio.Adapter.BaseViewHolder
import com.example.milio.Adapter.MessageAdapter
import com.example.milio.Adapter.MessageListAdapter
import com.example.milio.Model.*
import com.example.milio.Object.RetrofitClient
import com.example.milio.R
import com.example.milio.Util.UtilLibSessionMgr
import kotlinx.android.synthetic.main.activity_in_chat_board.*
import kotlinx.android.synthetic.main.fragment_message.*
import retrofit2.Call
import retrofit2.Response

class ActivityInChatBoard : AppCompatActivity() {
private var messagechatresponse:ArrayList<RecordRoomID>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_chat_board)
//        val message= arrayListOf<MessageListModel>()
//        message.add(MessageListModel("Hi",R.drawable.get,type = "rec"))
//        message.add(MessageListModel("Hello",type = "send"))

//        rvChatting.apply {
//            layoutManager = LinearLayoutManager(this@ActivityInChatBoard)
//            adapter= MessageListAdapter(message,context)
//        }
        var edittext=findViewById<EditText>(R.id.InputMsg)
        var editsmg=edittext.text
        btnBacktoMsgBoard.setOnClickListener {
            val intent = Intent(this,MessageBoardActivity::class.java)
            startActivity(intent)
        }
        btnSendMsg.setOnClickListener {
            val inputsmg:String =editsmg.toString()
//            if (inputsmg == null || inputsmg.trim()==""){
//                Toast.makeText(this,"please input data, edit text cannot be blank",Toast.LENGTH_LONG).show()
//            }else{}
            MessageListModel(inputsmg)
        }
        var mSessionMgr = UtilLibSessionMgr(this)
        val data=MessageChatRequest(room_id = "5dc3b793d6e792596d6a26f8",user_id = "5d5bf8c17244563d5ff4f96c")
        val auth="Bearer " + mSessionMgr.userToken
        RetrofitClient.instance.getMessageChat(auth,data).enqueue(object :retrofit2.Callback<MessageChatResponse> {
            override fun onFailure(call: Call<MessageChatResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MessageChatResponse>,response: Response<MessageChatResponse>) {
                Log.d("Message:", response.body().toString()+"")
                if(response.body()!!.httpCode == 200) {
                    messagechatresponse = response.body()!!.payload.records
                    rvChatting.apply {
                        layoutManager= LinearLayoutManager(context)
                        adapter= MessageListAdapter(messagechatresponse!!,context)
                    }
                }
            }
        })
    }
}
