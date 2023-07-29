package com.akshay.composecatchflicks.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by anandwana001 on
 * 02, January, 2023
 **/
@Parcelize
data class Genres(
    val id: Int? = null,
    val name: String? = null,
): Parcelable
