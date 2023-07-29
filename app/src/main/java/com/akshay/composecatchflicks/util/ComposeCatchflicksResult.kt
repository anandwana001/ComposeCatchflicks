package com.akshay.composecatchflicks.util

/**
 * Created by anandwana001 on
 * 02, April, 2023
 **/
sealed class ComposeCatchflicksResult<out T> {
    object Loading : ComposeCatchflicksResult<Nothing>()
    data class Error(val errorCode: Int) : ComposeCatchflicksResult<Nothing>()
    data class Success<out T>(val data: T) : ComposeCatchflicksResult<T>()
}
