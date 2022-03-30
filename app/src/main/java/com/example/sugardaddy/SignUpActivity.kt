package com.example.sugardaddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var name: EditText
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassowrd: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        name = findViewById(R.id.edt_name)
        userName = findViewById(R.id.edt_username)
        password = findViewById(R.id.edt_password)
        confirmPassowrd = findViewById(R.id.edt_confirm_password)

        val signUp: Button = findViewById(R.id.btn_signup)
        signUp.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_signup ->{
                if(name.text.toString() != "Valentinus" && password.text.toString() == confirmPassowrd.text.toString()){
                    Toast.makeText(applicationContext, "SignUp Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }else{
                    var text = "Sign Up Gagal, username mungkin sudah digunakan atau confirm password salah"
                    Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}