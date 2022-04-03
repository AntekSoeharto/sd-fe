package com.example.sugardaddy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drama(
    var name: String,
    var description: String,
    var photo: Int
) : Parcelable
