package com.example.sugardaddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var username: EditText
    private lateinit var password: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        username = findViewById(R.id.et_username)
        password = findViewById(R.id.et_password)

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
                if(username.text.toString() == "Valentinus" && password.text.toString() == "12345"){
                    val intent = Intent(this, BottomNavigation::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
            R.id.btn_signup_insignin->{
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }
}