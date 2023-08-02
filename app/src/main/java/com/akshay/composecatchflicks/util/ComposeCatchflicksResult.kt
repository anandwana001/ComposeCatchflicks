package com.akshay.composecatchflicks.util

/**
 * Created by anandwana001 on
 * 02, April, 2023
 **/
sealed class ComposeCatchflicksResult<out T> {
    data object Loading : ComposeCatchflicksResult<Nothing>()
    data object Error : ComposeCatchflicksResult<Nothing>()
    data class Success<T>(val data: T) : ComposeCatchflicksResult<T>()
}
