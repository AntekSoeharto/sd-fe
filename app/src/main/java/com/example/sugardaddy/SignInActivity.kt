package com.example.sugardaddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sugardaddy.Entity.Film
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var failedSignIn: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()

        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        failedSignIn = findViewById(R.id.tv_failed_signin)

        val btnSignIn: Button = findViewById(R.id.btn_signin)
        btnSignIn.setOnClickListener(this)
        val btnSignUp: Button = findViewById(R.id.btn_signup_insignin)
        btnSignUp.setOnClickListener(this)
//        val btnSignIn: Button = findViewById(R.id.btn_signin)
//        btnSignIn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_signin ->{
                verifyAccount()
            }
            R.id.btn_signup_insignin->{
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun verifyAccount() {
        val client = AsyncHttpClient()
        var param: String = "email=" + email.text.toString() + "&password=" + password.text.toString()
        val url = "http://10.0.2.2:9090/login?" + param
        client.get(url, object : AsyncHttpResponseHandler(){
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
                        val intent = Intent(this@SignInActivity, BottomNavigation::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }else{
                        failedSignIn.text = "Email Atau Password Salah, coba login kembali"
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