package com.example.sugardaddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import org.w3c.dom.Text
import java.lang.Exception

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtName: EditText
    private lateinit var edtUserName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtGender: EditText
    private lateinit var edtBirthDay: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtConfirmPassowrd: EditText
    private lateinit var tvFailedSignOut: TextView
    private lateinit var btnClose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        edtName = findViewById(R.id.edt_name)
        edtUserName = findViewById(R.id.edt_username)
        edtEmail = findViewById(R.id.edt_email)
        Log.e("emgail ", "${edtEmail.text.toString()}")
        edtGender = findViewById(R.id.edt_gender)
        edtBirthDay = findViewById(R.id.edt_birth_day)
        edtPassword = findViewById(R.id.edt_password)
        edtConfirmPassowrd = findViewById(R.id.edt_confirm_password)
        tvFailedSignOut = findViewById(R.id.tv_failed_signup)
        btnClose = findViewById(R.id.btn_close)

        val signUp: Button = findViewById(R.id.btn_signup)
        signUp.setOnClickListener(this)
        btnClose.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_signup ->{
                checkPassword()
            }
            R.id.btn_close ->{
                onBackPressed()
            }
        }
    }

    private fun checkPassword(){
        if(edtPassword.text.toString() == edtConfirmPassowrd.text.toString()){
            verifyAccount()
        }else{
            tvFailedSignOut.text = "Password Not Same With Confirm, Please Retype Password"
        }
    }

    private fun verifyAccount() {
        val client = AsyncHttpClient()
        val params = RequestParams()
//        params.put("nama", edtName.text.toString())
//        Log.e("Nama ", "${edtName.text.toString()}")
//        params.put("username", edtUserName.text.toString())
//        params.put("email", edtEmail.text.toString())
//        params.put("gender", edtGender.text.toString())
//        params.put("birthday", edtBirthDay.text.toString())
//        params.put("password", edtPassword.text.toString())
        var param = "nama="+edtName.text.toString()+"&username="+edtUserName.text.toString()+"&email="+edtEmail.text.toString()
        param += "&gender="+edtGender.text.toString()+"&birthday="+edtBirthDay.text.toString()+"&password="+edtPassword.text.toString()

        val url = "https://sd-heroku.herokuapp.com/signup?"+param
        client.post(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)

                try {
                    val responseObject = JSONObject(result)
                    val status = responseObject.getInt("status")

                    if (status == 200){
                        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                        Toast.makeText(this@SignUpActivity, "SignUp Success, Please Login", Toast.LENGTH_SHORT).show()
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    }else{
                        tvFailedSignOut.text = "Email Has Been Used"
                    }

                } catch (e: Exception){
//                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
//                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
//                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}