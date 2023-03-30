package com.gsoft.yapechallenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class  Recipe(
    val id : String,
    val title : String,
    val description : String,
    val image: String,
    val lat: String,
    val long:String
):Parcelable
