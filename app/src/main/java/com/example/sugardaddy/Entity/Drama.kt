package com.example.sugardaddy.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drama(
    var name: String,
    var description: String,
    var photo: String
) : Parcelable
