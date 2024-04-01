package com.example.a30daysofgodlove.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Versus(
    @StringRes val title: Int,
    @StringRes val description:Int,
    @DrawableRes val img: Int
)
