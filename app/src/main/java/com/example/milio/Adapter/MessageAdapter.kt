package com.example.milio.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.milio.Controller.ActivityInChatBoard
import com.example.milio.Controller.MessageBoardActivity.Companion.onReplace
import com.example.milio.Fragment.SettingFragment
import com.example.milio.Interface.ItemClickListener
import com.example.milio.Model.Record
import com.example.milio.R
import com.example.milio.Util.TimeAgo
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.collections.ArrayList



class MessageAdapter(private var messages:ArrayList<Record>, context: Context):RecyclerView.Adapter<MessageAdapter.ViewHolder>()
{

    var mContext=context
//    private var mfilter:MutableList<Record>?=null
//    init {
//        mfilter=contactFilter
//    }
//    fun setfilter(listitem: MutableList<Record>): MutableList<Record>? {
//        mfilter!!.clear()
//        /*mFilteredList = ArrayList()*/
//        mfilter!!.addAll(listitem)
//        notifyDataSetChanged()
//        return mfilter
//    }
//
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.activity_message,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=messages.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mm:  Record =messages[position]
        holder.name.text = "${messages[position].message.user.firstname} ${messages[position].message.user.lastname}"
        holder.smg.text="${messages[position].message.text}"
        val pic=holder.image
        val url="${mm.message.user.profilePic}"
//        Log.d("ChatResponse:",mm.message.user.profilePic)
//         Log.d("ImageURL:",url)
         Picasso.get().load(url).into(pic)
        val day=mm.created_at
//        val dayAgo=TimeUnit.DAYS.toDays(day)
        val timeAgo = TimeAgo.getTimeAgo(day)
//        val dayAgo=((((day / 1000) / 60) / 60) / 24)  % 24
        holder.date.text="${timeAgo}"
//        holder.image.setImageResource(mm.photo)
//        holder.smg.text=messages[position].msg
//        holder.date.text=messages[position].date
        holder.setOnItemClickListenner(object : ItemClickListener {
            override fun onClickItem(view: View, pos: Int) {
//                Toast.makeText(mContext,"Name : " + messages[position].name,Toast.LENGTH_SHORT).show()
//                val i = Intent(mContext, ActivityInChatBoard::class.java)
//                mContext.startActivity(i)
                onReplace(view.context!!, SettingFragment())

            }
        })
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        var itemClickListenner: ItemClickListener?=null
        fun setOnItemClickListenner(itemClickListenner: ItemClickListener)
        {
            this.itemClickListenner=itemClickListenner
        }
        override fun onClick(p0: View?) {
            this.itemClickListenner!!.onClickItem(p0!!,adapterPosition)
        }

        val image:CircleImageView=itemView.findViewById(R.id.profile_image)
        val name: TextView =itemView.findViewById(R.id.txtname)
        val smg: TextView =itemView.findViewById(R.id.txtMessage)
        val date:TextView=itemView.findViewById(R.id.txtDate)

    }

     fun filterList(filteredList: ArrayList<Record>) {
        messages = filteredList
        notifyDataSetChanged()
    }

}