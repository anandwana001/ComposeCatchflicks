package com.akshay.composecatchflicks.util

/**
 * Created by anandwana001 on
 * 02, April, 2023
 **/
sealed class ComposeCatchflicksNetworkResult<out T> {
    data object Failure : ComposeCatchflicksNetworkResult<Nothing>()
    data class Success<out T>(val data: T) : ComposeCatchflicksNetworkResult<T>()
}
