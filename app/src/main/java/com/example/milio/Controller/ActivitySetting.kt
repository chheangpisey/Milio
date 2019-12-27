package com.example.milio.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.milio.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_setting.bottom_navigation

class ActivitySetting : AppCompatActivity() {
//    private val mOnNavigationItemSelectedListener=BottomNavigationView.OnNavigationItemSelectedListener {item->
//        when(item.itemId){
//            R.id.navigation_message ->{
//
//                val intent =Intent(this, MessageBoardActivity::class.java)
//                startActivity(intent)
//                overridePendingTransition(
//                    R.anim.right_to_left,
//                    R.anim.left_to_right
//                )
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_setting ->{
//                val intent =Intent(this, ActivitySetting::class.java)
//                startActivity(intent)
//                overridePendingTransition(
//                    R.anim.right_to_left,
//                    R.anim.left_to_right
//                )
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
//        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}
