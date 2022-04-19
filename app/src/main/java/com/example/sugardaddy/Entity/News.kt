package com.example.sugardaddy.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    var title: String,
    var image: String,
    var description: String,
    var publishedDate: String,
    var link: String,
    var rank: Int,
    var country: String
):Parcelable
