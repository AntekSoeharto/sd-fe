package com.example.sugardaddy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    var name: String,
    var description: String,
    var photo: Int
) : Parcelable
