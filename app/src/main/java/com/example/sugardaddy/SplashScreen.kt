package com.example.sugardaddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.sugardaddy.Entity.User
import com.example.sugardaddy.Helper.MappingHelper
import com.example.sugardaddy.db.UserHelper
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        Handler().postDelayed({
                val intent = Intent(this@SplashScreen, BottomNavigation::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }, 3000)


        getAccount()

    }


    private fun getAccount(){
        lifecycleScope.launch {
            val noteHelper = UserHelper.getInstance(applicationContext)
            noteHelper.open()
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = noteHelper.queryAll()
                MappingHelper.mapUserCursorToArrayList(cursor)
            }
            val users = deferredNotes.await()
            if (users.size > 0) {
                Handler().postDelayed({
                    val intent = Intent(this@SplashScreen, BottomNavigation::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }, 3000)
            } else {
                Handler().postDelayed({
                    val intent = Intent(this@SplashScreen, SignInActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }, 3000)
            }
            noteHelper.close()
        }
    }
}
