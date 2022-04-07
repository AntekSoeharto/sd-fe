package com.example.sugardaddy.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    var ID: Int,
    var judul: String,
    var rating: Double,
    var tanggalTerbit: String,
    var actor: String,
    var sinopsis: String,
    var filmType: String,
    var releaseType: String,
    var duration: Int,
    var image: String,
    var imgBackground: String
) : Parcelable
