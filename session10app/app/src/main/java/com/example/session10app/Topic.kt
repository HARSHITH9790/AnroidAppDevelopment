package com.example.session10app

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val nameRes: Int,
    val courseCount: Int,
    @DrawableRes val imageRes: Int
)