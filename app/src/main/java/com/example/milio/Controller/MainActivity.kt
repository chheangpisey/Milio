package com.example.milio.Controller
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.milio.Model.*
import com.example.milio.Object.RetrofitClient
import com.example.milio.R
import com.example.milio.Util.UtilLibSessionMgr
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setting.*
import retrofit2.Response
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed_username.setText("puttheavy.tep@idealinkconsulting.com")
        ed_password.setText("Putheavy@$2020")
            btnSignIn.setOnClickListener {
                val email =ed_username.text.toString().trim()
                val password=ed_password.text.toString().trim()
                Log.d("Username:", email)
                Log.d("Password:", password)
                val data=LoginRequest(email,password)
                RetrofitClient.instance.userLogin(data).enqueue(object :retrofit2.Callback<LoginResponse> {
                    override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                        Log.d("LoginFail:", t.message)
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(call:retrofit2.Call<LoginResponse>, response: Response<LoginResponse>) {
                        Log.d("LoginResponse:", response.body().toString()+"")
                        if(response.body()?.httpCode == 200){
                            val mSession  = UtilLibSessionMgr(this@MainActivity)
                            mSession.userToken = response.body()!!.data.access_token
                            mSession.userId = response.body()!!.data._id
                            val d=mSession.userId
                            val intent=Intent(applicationContext,MessageBoardActivity::class.java)
                            intent.putExtra("email", email)
                            intent.putExtra("user",d)
                            startActivity(intent)
                        }else{
                           Toast.makeText(applicationContext, response.body()?.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                })


            }
        }
}
