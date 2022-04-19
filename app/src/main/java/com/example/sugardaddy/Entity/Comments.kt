package com.example.sugardaddy.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comments(
    var id: Int,
    var username:String,
    var email : String,
    var comment:String,
):Parcelable


