package com.example.sugardaddy.Helper

import com.example.sugardaddy.Entity.User

object UserSingleton {
//    init
//    {
//        println("Singleton class invoked.")
//    }

    var user = User()
    fun getUserid(): Int{
        return user.id
    }
}