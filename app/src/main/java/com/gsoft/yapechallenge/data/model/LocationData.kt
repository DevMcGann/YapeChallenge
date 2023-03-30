package com.gsoft.yapechallenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationData(
    val lat : String,
    val long : String
):Parcelable
