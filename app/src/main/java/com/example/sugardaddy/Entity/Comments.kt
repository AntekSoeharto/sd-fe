package com.example.sugardaddy.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comments(
    var username:String,
    var comments:String,
):Parcelable

