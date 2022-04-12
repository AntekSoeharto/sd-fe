package com.example.sugardaddy.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    var id: Int = 0,
    var nama: String? = null,
    var username: String? = null,
    var email: String? = null,
    var gender: String? = null,
    var birthDay: String? = null,
    var password: String? = null
): Parcelable
