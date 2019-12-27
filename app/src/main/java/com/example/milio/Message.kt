package com.example.milio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class Message : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        setRecylerView()
    }
    private fun setRecylerView()
    {

//        val layoutManager= LinearLayoutManager(this)
//        layoutManager.orientation= LinearLayoutManager.VERTICAL
//        recyclerView.layoutManager=layoutManager
//        val adapter= MessageAdapter(ArrayList(),applicationContext)
//        recyclerView.adapter=adapter
    }
}
