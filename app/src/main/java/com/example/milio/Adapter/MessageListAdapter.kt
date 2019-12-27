package com.example.milio.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.milio.Model.MessageListModel
import com.example.milio.Model.PayLoadRoomID
import com.example.milio.Model.RecordRoomID
import com.example.milio.R
import kotlinx.android.synthetic.main.chat_received.view.*
import kotlinx.android.synthetic.main.chat_sent.view.*
import java.lang.IllegalArgumentException

class MessageListAdapter(private val chatmessage:ArrayList<RecordRoomID>, context:Context):RecyclerView.Adapter<BaseViewHolder<RecordRoomID>>() {

    var data: ArrayList<RecordRoomID> = chatmessage

    override fun onBindViewHolder(holder: BaseViewHolder<RecordRoomID>, position: Int) {
        holder.bind(data[position])
    }
    var mContext=context
    companion object{
        const val RECEIVE_TYPE=1
        const val SEND_TYPE=2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BaseViewHolder<RecordRoomID> {
//        val view= LayoutInflater.from(parent.context).inflate(R.layout.chat_received,parent,false)
//        return ViewHolder(view)
       return when(viewType) {
           RECEIVE_TYPE -> {
               val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_received, parent, false)
               ReceivedViewHolder(view)
           }
           SEND_TYPE -> {
               val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_sent, parent, false)
               SendViewHolder(view)
           }
           else -> throw IllegalArgumentException("No View type")
       }

    }

    override fun getItemCount()=chatmessage.size



    override fun getItemViewType(position: Int): Int {
        //return super.getItemViewType(position)

        val item = chatmessage[position]
        return if(item.type =="send") {SEND_TYPE}else{ RECEIVE_TYPE}

    }
    inner class SendViewHolder(itemView: View):BaseViewHolder<RecordRoomID>(itemView){
        private val smg:TextView=itemView.findViewById(R.id.txtChatSent)
        override fun bind(item: RecordRoomID) {
            smg.text=item.text
        }
    }

    inner class ReceivedViewHolder(itemView: View):BaseViewHolder<RecordRoomID>(itemView){

        private val smg: TextView =itemView.findViewById(R.id.txtChatReceived)
        override fun bind(item: RecordRoomID) {
            smg.text = item.text
//            item.userpic?.let { itemView.profilepic.setImageResource(it) }
        }
    }
}