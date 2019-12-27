package com.example.milio.Fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milio.Adapter.MessageAdapter
import com.example.milio.Controller.MessageBoardActivity.Companion.onReplace
import com.example.milio.Model.*
import com.example.milio.Object.RetrofitClient
import com.example.milio.R
import com.example.milio.Util.UtilLibSessionMgr
import kotlinx.android.synthetic.main.fragment_message.*
import retrofit2.Response
/**
 * A simple [Fragment] subclass.
 */
class MessageFragment : Fragment(), View.OnClickListener {
//    private val recyclerView:RecyclerView?=null
    private var chatlistrespone:ArrayList<Record>?=null
//    private val search_input:EditText?=null
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val message= arrayListOf<MessageModel>()
//        message.add(MessageModel("Kang Seulgi",R.drawable.get, "Hi", "2 mn ago"))
//        message.add(MessageModel("Bae Irene", R.drawable.get, "Bello", "1 mn ago"))
//        message.add(MessageModel("Kang Seulgi", R.drawable.get,"Hi", "2 mn ago"))
//        message.add(MessageModel("Bae Irene", R.drawable.get, "Bello","1 mn ago"))
//        message.add(MessageModel("Kang Seulgi", R.drawable.get, "Hi", "2 mn ago"))
//        message.add(MessageModel("Bae Irene", R.drawable.get, "Bello","1 mn ago"))
//        message.add(MessageModel("Kang Seulgi",R.drawable.get, "Hi", "2 mn ago"))
//        message.add(MessageModel("Bae Irene", R.drawable.get, "Bello", "1 mn ago"))
//        message.add(MessageModel("Kang Seulgi", R.drawable.get, "Hi", "2 mn ago"))
//        rvMessage.apply {
//            layoutManager= LinearLayoutManager(context)
//            adapter= MessageAdapter(message,context)
//        }

        val img: ImageView = view!!.findViewById(R.id.btnSwapeToSetting)

        img.setOnClickListener {
            //            Toast.makeText(context,"mm",Toast.LENGTH_SHORT).show()
//            R.id.navigation_setting
//            bottom_navigation.menu.getItem(1).isChecked


            onReplace(context!!, SettingFragment())
        }
         val searchinput:EditText=view!!.findViewById(R.id.search_input)
        searchinput.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
               filter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        chatJsonReload()

    }
    fun filter(text:String){
        val filteredList = ArrayList<Record>()
        for (item in chatlistrespone!!.iterator()) {
            if (item.message.user.firstname!!.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        rvMessage.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= MessageAdapter(filteredList!!,context)
        }
    }
    fun chatJsonReload(){
        var mSessionMgr = UtilLibSessionMgr(context)
        var data =ChatRequest(owner_id = mSessionMgr.userId)
//        var auth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZDViZjhjMTcyNDQ1NjNkNWZmNGY5NmMiLCJhdXRoS2V5IjoiMDI1Yjc2Nzk0YTUzZTcyM2JmZjU1OWU0YTdiYzZlMWI1Y2Y0YjFjMjUzYmU2NTRlMzVlNmY1ZmRmYWJlMDkzZGM1Mjc4YWM1Mjk5YmEzMDFmNzIwMWU0ZTZjM2U2YzBhYTBlYWZiNzkxYjJiMjI1ZjM1OWRmZDM4NmNhZGQyODgiLCJmaXJzdG5hbWUiOiJUaGVhdnkiLCJsYXN0bmFtZSI6IlRlcCIsInVzZXJuYW1lIjoicHV0dGhlYXZ5X3RlcCIsImVtYWlsIjoicHV0dGhlYXZ5LnRlcEBpZGVhbGlua2NvbnN1bHRpbmcuY29tIiwic3RhdHVzIjoiYWN0aXZhdGVkIiwiYWNjb3VudFZlcmlmeSI6dHJ1ZSwicm9sZSI6Im1lbWJlciIsImp0aSI6InRydWU6OWhTZzdTb0dvIiwiaWF0IjoxNTc0OTk3MDMzLCJleHAiOjE1NzUyNTYyMzN9.V2RI-GIVgRpIUXsSqnG7UqYHEB1akiV5GbYiHVRBux4"
        val auth="Bearer " + mSessionMgr.userToken
        RetrofitClient.instance.chatlistResponse(auth, data).enqueue(object :retrofit2.Callback<ChatListResponse> {
            override fun onFailure(call: retrofit2.Call<ChatListResponse>, t: Throwable) {
                Log.d("ChatResponseFailed:", t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call:retrofit2.Call<ChatListResponse>, response: Response<ChatListResponse>) {
                Log.d("ChatResponse:", response.body().toString()+"")
                if(response.body()!!.httpCode == 200) {
                    chatlistrespone = response.body()!!.payload.records
                    rvMessage.apply {
                                    layoutManager= LinearLayoutManager(context)
                                    adapter= MessageAdapter(chatlistrespone!!,context)
                    }
                }
            }

        })
    }


}
