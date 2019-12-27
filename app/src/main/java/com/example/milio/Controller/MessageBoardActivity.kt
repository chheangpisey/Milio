package com.example.milio.Controller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.milio.Fragment.MessageFragment
import com.example.milio.R
import com.example.milio.Fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_message_board.*
class MessageBoardActivity : AppCompatActivity() {
//    private  var getEmail: String? = null
//    private var getUserID:String?=null
    private val mOnNavigationItemSelectedListener=BottomNavigationView.OnNavigationItemSelectedListener {item->
        when(item.itemId){
            R.id.navigation_message ->{

//                val intent =Intent(this,MessageBoardActivity::class.java)
//                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
//                startActivity(intent)
//                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right)
                replaceFragment(MessageFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting ->{

//                var bundle=Bundle()
//                bundle.putString("Email", getEmail)
//                val frangment = SettingFragment()
//                val fragment = SettingFragment.newInstance(getEmail!!,getUserID!!)
                replaceFragment(SettingFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
            false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_board)
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(MessageFragment())
//         getEmail = intent.getStringExtra("email")
//        Log.e("Hi",getEmail)
//         getUserID=intent.getStringExtra("user")
//        rvMessage.apply {
//            layoutManager= LinearLayoutManager(this@MessageBoardActivity)
//            adapter= MessageAdapter(message, applicationContext)
//
////            rvMessage.addItemDecoration(
////                DividerItemDecoration(
////                    context,
////                    LinearLayoutManager.VERTICAL
////                )
////            )
//        }

//        btnSwapeToSetting.setOnClickListener {
//            val setting = Intent(this, ActivitySetting::class.java)
//            startActivity(setting)
//        }
    }
    companion object{
        fun onReplace(context: Context, fragment: Fragment){
            (context as MessageBoardActivity).supportFragmentManager.beginTransaction().replace(R.id.main_container,fragment).commit()

        }
//        fun CallUserProfile(){
//            var data=ProfileRequest()
//        }
    }

    private fun replaceFragment(fragment:Fragment) {
        val fragmentTransaction=supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container,fragment)
        fragmentTransaction.commit()
    }


}
