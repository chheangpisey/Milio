package com.example.milio.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.milio.Controller.MainActivity
import com.example.milio.Model.ProfileRequest
import com.example.milio.Model.ProfileResponse
import com.example.milio.Object.RetrofitClient
import com.example.milio.R
import com.example.milio.Util.UtilLibSessionMgr
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_setting.signout
import kotlinx.android.synthetic.main.fragment_setting.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

//    private var mShimmerViewContainer: ShimmerFrameLayout? = null
//    companion object {
//        const val ARG_NAME = "email"
//        const val ARG_NAME1 = "user"
//        fun newInstance(email: String,user:String): SettingFragment {
//            val fragment = SettingFragment()
//            val bundle = Bundle().apply {
//                putString(ARG_NAME, email)
//                putString(ARG_NAME1, user)
//
//            }
//            fragment.arguments = bundle
//            return fragment
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getProfile()
//        val email = arguments?.getString(ARG_NAME)
//        textemail.text=email
//        val userID=arguments?.getString(ARG_NAME1)
//        textUserID.text=userID

        signout.setOnClickListener {
            val intent=Intent(context,MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun getProfile(){
        var mSessionMgr = UtilLibSessionMgr(context)
        val profile= ProfileRequest(userId = mSessionMgr.userId)
        val auth="Bearer " + mSessionMgr.userToken
        RetrofitClient.instanceLifeStyle.getUserProfile(auth,profile).enqueue(object : Callback<ProfileResponse> {
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.d("Get Profile Failed:", t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                Log.d("ProfileResponse:",response.body().toString()+"")

                if(response.body()?.httpCode == 200){
                    var  fname=response.body()!!.data.firstname
                    var lname=response.body()!!.data.lastname
                    val url =response.body()!!.data.profilePic
                    var useridentity=response.body()!!.data._id
                    var userMail=response.body()!!.data.email
                    Picasso.get().load(url).into(profile_image)
                    txtsettingusername.text="${fname} ${lname}"
                    textUserID.text=useridentity
                    textemail.text=userMail
                    // Log.d("Firstname:",url)
                }else{
                    Toast.makeText(context, response.body()?.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }



}
